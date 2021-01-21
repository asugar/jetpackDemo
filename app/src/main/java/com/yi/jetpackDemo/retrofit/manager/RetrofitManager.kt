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
    private var mRetrofit: Retrofit? = null
    private var mBaseUrl: String? = null
    private var mCache: Cache? = null
    private var mCacheInterceptor: Interceptor? = null
    private var mLoggingInterceptor: Interceptor? = null
    private var mHeaderInterceptor: Interceptor? = null
    private var mDomainInterceptor: Interceptor? = null
    private var mNetworkInterceptors: MutableList<Interceptor> = ArrayList()
    private val mOkHttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        builder.readTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        builder.writeTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        builder.sslSocketFactory(getSSLSocketFactory(), CustomTrustManager())
        builder.hostnameVerifier(getHostnameVerifier())
        builder.build()
    }
    private val mHosts = ArrayList<String>()// 存放的多base

    /**
     * 获取多域名列表
     */
    fun getHosts(): ArrayList<String> = mHosts

    /**
     * 设置多域名列表
     */
    fun setHosts(vararg host: String) {
        host.forEach {
            mHosts.add(it)
        }
    }


    fun initRetrofit(
        baseUrl: String,
        cache: Cache,
        loggingInterceptor: Interceptor?,
        cacheInterceptor: Interceptor?,
        headerInterceptor: Interceptor?,
        domainInterceptor: Interceptor?,
        vararg networkInterceptors: Interceptor?
    ) {
        mBaseUrl = baseUrl
        mCache = cache
        mLoggingInterceptor = loggingInterceptor
        mCacheInterceptor = cacheInterceptor
        mHeaderInterceptor = headerInterceptor
        mDomainInterceptor = domainInterceptor
        for (networkInterceptor in networkInterceptors) {
            networkInterceptor?.let {
                mNetworkInterceptors.add(it)
            }
        }
    }

    fun getRetrofit(): Retrofit {
        if (mRetrofit == null) {
            synchronized(this) {
                if (mRetrofit == null) {
                    val builder = Retrofit.Builder()
                    if (mBaseUrl != null) {
                        builder.baseUrl(mBaseUrl!!)
                    } else {
                        throw RuntimeException("baseUrl未设置")
                    }
                    val clientBuilder = mOkHttpClient.newBuilder()
                    // 此处增加缓存
                    clientBuilder.cache(mCache)
                    // 此处增加各个拦截器

                    mLoggingInterceptor?.let {
                        clientBuilder.addInterceptor(it)
                    }
                    clientBuilder.addInterceptor(PerformanceLogInterceptor())
                    mHeaderInterceptor?.let {
                        clientBuilder.addInterceptor(it)
                    }
                    mCacheInterceptor?.let {
                        clientBuilder.addNetworkInterceptor(it)
                    }
                    mDomainInterceptor?.let {
                        clientBuilder.addInterceptor(it)
                    }

                    if (mNetworkInterceptors.isNullOrEmpty().not()) {
                        // 在StethoInterceptor后添加的Interceptor中的内容都不会被打印，故此处添加到第0个位置
                        clientBuilder.networkInterceptors().addAll(0, mNetworkInterceptors)
                    }
                    val build = clientBuilder.build()
                    builder.client(build)

                    // 防止出现类无法解析的问题
                    val gsonBuilder = GsonBuilder()
                        .excludeFieldsWithModifiers(
                            Modifier.FINAL,
                            Modifier.TRANSIENT,
                            Modifier.STATIC
                        )
//                        .registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
//                        .registerTypeAdapter(BaseFeedEntity::class.java, FeedTypeAdapter())
                    mRetrofit = builder
//                        .callFactory(TrainCallFactory(build))
                        .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                }

            }
        }
        return mRetrofit!!
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