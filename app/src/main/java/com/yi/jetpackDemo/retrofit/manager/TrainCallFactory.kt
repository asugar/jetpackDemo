package com.yi.jetpackDemo.retrofit.manager

import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.retrofit.RETROFIT_TAG
import okhttp3.*

/**
 * 使用CallFactory会事https的证书验证失效
 */
class TrainCallFactory(val realCall: Call.Factory) : okhttp3.Call.Factory {

    //真实请求还是OkHttpClient，只是在请求之前，换掉URL即可
//    private val realCall: Call.Factory = OkHttpClient()

    override fun newCall(request: Request): Call {
        val headers: Headers = request.headers()
        val url = request.url()

        val myHost = headers.get(HOST_KEY)
        Logger.t(RETROFIT_TAG).d("MultBaseUrlInterceptor oldUrl= $url myHost= $myHost")
        if (RetrofitManager.getHosts().contains(myHost)) {

            var builder = request.newBuilder()
            builder.removeHeader(HOST_KEY)
            builder.removeHeader("Host")
            builder.addHeader("Host", myHost)
            val newUrl = url.toString()
                .replace(RetrofitManager.getRetrofit().baseUrl().toString(), myHost!!, true)
            Logger.t(RETROFIT_TAG).d("MultBaseUrlInterceptor newUrl= $newUrl")
            val newBaseUrl = HttpUrl.get(newUrl)
            url.pathSegments().forEach {
//                newBaseUrl.
            }

            try {
                val urlField = request.javaClass.getDeclaredField("url")
                urlField.isAccessible = true
                urlField.set(request, newBaseUrl)
                val headerField = request.javaClass.getDeclaredField("headers")
                headerField.isAccessible = true
//                headerField.set(request, createNewHeaders)
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
        return realCall.newCall(request)
    }
}