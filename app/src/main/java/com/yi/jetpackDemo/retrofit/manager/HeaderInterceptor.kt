package com.yi.jetpackDemo.retrofit.manager

import okhttp3.Interceptor
import okhttp3.Response

open class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val builder = request.newBuilder()
        val method = request.method()
        var url = request.url()
        val headers = headers()
        if (headers != null && headers.isNotEmpty()) {
            headers.forEach { builder.addHeader(it.key, it.value.toString()) }
        }
        request = builder.build()
        return chain.proceed(request)
    }

    open fun headers(): Map<String, Any>? = null
}