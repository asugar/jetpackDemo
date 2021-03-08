package com.yi.jetpackDemo.retrofit.manager

import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.MyApplication
import com.yi.jetpackDemo.retrofit.RETROFIT_TAG
import okhttp3.Headers
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 解决多baseUrl请求
 * 失败
 * 1）从https到http的时候port有变化：443->80，这时候会报错：
 * network interceptor com.yi.jetpackDemo.retrofit.manager.MultBaseUrlInterceptor@baec1d4 must retain the same host and port
 * 2）绕过问题1）发现请求的baseUrl没有替换，发现header里有host
 * 3）替换完header的host后，报错：
 *  okhttp3.internal.http2.StreamResetException: stream was reset: INTERNAL_ERROR
 *
 *  原因：
 *  不应该添加到networkInterceptor中
 */
const val HOST_KEY = "hostKey"

class MultBaseUrlInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return intercept2(chain)
    }

    private fun intercept2(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val headers = request.headers()
        val url: HttpUrl = request.url()
        val myHost = headers.get(HOST_KEY)
        Logger.t(RETROFIT_TAG).d("MultBaseUrlInterceptor oldUrl= $url myHost= $myHost")
        if (myHost.isNullOrEmpty().not() && RetrofitManager.getHosts().contains(myHost)) {
            // 有这个host，下面替换
            var builder = request.newBuilder()
            builder.removeHeader(HOST_KEY)

            val newUrl = url.toString()
                .replace(RetrofitManager.getRetrofit().baseUrl().toString(), myHost!!, true)
            Logger.t(RETROFIT_TAG).d("MultBaseUrlInterceptor newUrl= $newUrl")
            val newBaseUrl = HttpUrl.get(newUrl)

//            val urlBuilder = url.newBuilder()
//            val newHttpUrl = urlBuilder
//                .scheme(newBaseUrl?.scheme())
//                .host(newBaseUrl?.host())
//                .port(newBaseUrl?.port()!!)
//                .build()

            builder = builder.url(newBaseUrl)
            val newRequest = builder.build()
            Logger.t(RETROFIT_TAG).d(newRequest.url().toString())
            return chain.proceed(newRequest)
        } else {
            // 不做任何处理
            return chain.proceed(request)
        }
    }

    /**
     * 通过反射修改属性
     */
    private fun intercept1(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val headers: Headers = request.headers()
        val url = request.url()

        val myHost = headers.get(HOST_KEY)
        Logger.t(RETROFIT_TAG).d("MultBaseUrlInterceptor oldUrl= $url myHost= $myHost")
        if (myHost != null && RetrofitManager.getHosts().contains(myHost)) {
            val newUrl = url.toString()
                .replace(
                    RetrofitManager.getRetrofit().baseUrl().toString(),
                    "https://train.aixuexi.com/",
                    true
                )
            Logger.t(RETROFIT_TAG).d("MultBaseUrlInterceptor newUrl= $newUrl")
            val newBaseUrl = HttpUrl.get(newUrl)
            try {
                val urlField = request.javaClass.getDeclaredField("url")
                urlField.isAccessible = true
                urlField.set(request, newBaseUrl)
                val headerField = request.javaClass.getDeclaredField("headers")
                headerField.isAccessible = true
                headerField.set(request, createNewHeaders(headers))
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        return chain.proceed(request)
    }

    //刚才自己的对应的URL地址清除掉
    private fun createNewHeaders(headers: Headers?): Headers? {
        if (null == headers) return null
        val headerSize = headers.size()
        val builder = Headers.Builder()
        for (i in 0 until headerSize) {
            if (HOST_KEY.equals(headers.name(i)) || "Host".equals(headers.name(i))) continue
            builder.add(headers.name(i), headers.value(i))
        }
        builder.add("Host", HttpUrl.parse(MyApplication.TRAIN_HOST)?.host())
        return builder.build()
    }
}