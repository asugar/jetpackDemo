package com.yi.jetpackDemo.retrofit.manager

import com.yi.jetpackDemo.MyApplication
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 还是
 */
class DomainInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headers = request.headers()
        val myHost = headers.get(HOST_KEY)
        if (RetrofitManager.getHosts().contains(myHost)) {
//            builder.removeHeader(HOST_KEY)
//            builder.removeHeader("Host")
//            builder.addHeader("Host", myHost)


            return chain.proceed(
                request.newBuilder()
                    .url(
                        request.url().toString()
                            .replace(MyApplication.APP_HOST, MyApplication.TRAIN_HOST)
                            .toHttpUrlOrNull() ?: request.url()
                    )
                    // OR
                    //.url(HttpUrl.parse(request.url().toString().replace("localhost", "yourdomain.com:443")) ?: request.url())
                    .build()
            )
        } else {
            return chain.proceed(request)
        }
    }
}

private fun String.toHttpUrlOrNull(): HttpUrl? {
    return HttpUrl.parse(this)
}
