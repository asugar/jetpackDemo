package com.yi.jetpackDemo.retrofit.manager

import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object RetrofitManager {

    private val TIMEOUT_MILLIS: Long = 15000
    private var retrofit: Retrofit? = null
    private var baseUrl: String? = null
    private var cache: Cache? = null
    private var cacheInterceptor: Interceptor? = null
    private var loggingInterceptor: Interceptor? = null
    private var headerInterceptor: Interceptor? = null
    private var networkInterceptors: MutableList<Interceptor> = ArrayList()

    private val mOkHttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        builder.readTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        builder.writeTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        builder.sslSocketFactory(getSSLSocketFactory(), CustomTrustManager())
        builder.hostnameVerifier(getHostnameVerifier())
        builder.build()
    }

    fun initRetrofit(
        baseUrl: String,
        cache: Cache,
        loggingInterceptor: Interceptor,
        cacheInterceptor: Interceptor,
        headerInterceptor: Interceptor?,
        vararg networkInterceptors: Interceptor
    ) {
        RetrofitManager.baseUrl = baseUrl
        RetrofitManager.cache = cache
        RetrofitManager.loggingInterceptor = loggingInterceptor
        RetrofitManager.cacheInterceptor = cacheInterceptor
        RetrofitManager.headerInterceptor = headerInterceptor
        for (networkInterceptor in networkInterceptors) {
            RetrofitManager.networkInterceptors.add(networkInterceptor)
        }
    }

    fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            synchronized(this) {
                if (retrofit == null) {
                    val builder = Retrofit.Builder()
                    if (baseUrl != null) {
                        builder.baseUrl(baseUrl!!)
                    } else {
                        throw RuntimeException("baseUrl未设置")
                    }
                    val clientBuilder = mOkHttpClient.newBuilder()
                    // 此处增加缓存
                    clientBuilder.cache(cache)
                    // 此处增加各个拦截器
                    loggingInterceptor?.let {
                        clientBuilder.addInterceptor(it)
                    }
                    headerInterceptor?.let {
                        clientBuilder.addInterceptor(it)
                    }
                    cacheInterceptor?.let {
                        clientBuilder.addNetworkInterceptor(it)
                    }

                    if (networkInterceptors.isNullOrEmpty().not()) {
                        // 在StethoInterceptor后添加的Interceptor中的内容都不会被打印，故此处添加到第0个位置
                        clientBuilder.networkInterceptors().addAll(0, networkInterceptors)
                    }
                    builder.client(clientBuilder.build())

                    // 防止出现类无法解析的问题
                    val gsonBuilder = GsonBuilder()
                        .excludeFieldsWithModifiers(
                            Modifier.FINAL,
                            Modifier.TRANSIENT,
                            Modifier.STATIC
                        )
//                        .registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
//                        .registerTypeAdapter(BaseFeedEntity::class.java, FeedTypeAdapter())
                    retrofit = builder
                        .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                }

            }
        }
        return retrofit!!
    }

    private fun getSSLSocketFactory(): SSLSocketFactory? {
        var ssfFactory: SSLSocketFactory? = null
        try {
            val sc: SSLContext = SSLContext.getInstance("TLS")
            sc.init(null, arrayOf<TrustManager>(CustomTrustManager()), SecureRandom())
            ssfFactory = sc.socketFactory
        } catch (e: Exception) {
        }
        return ssfFactory
    }

    class CustomTrustManager : X509TrustManager {
        override fun checkClientTrusted(
            chain: Array<X509Certificate>?,
            authType: String?
        ) {

        }

        override fun checkServerTrusted(
            chain: Array<X509Certificate>?,
            authType: String?
        ) {

        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }

    }

    private fun getHostnameVerifier(): HostnameVerifier? {
        return HostnameVerifier { hostname, session -> true }
    }

}