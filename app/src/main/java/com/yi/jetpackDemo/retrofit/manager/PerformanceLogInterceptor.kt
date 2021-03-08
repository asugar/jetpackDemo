package com.yi.jetpackDemo.retrofit.manager

import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.retrofit.RETROFIT_TAG
import com.yi.jetpackDemo.retrofit.utils.toBase64
import com.yi.jetpackDemo.retrofit.utils.toHmacSha1
import com.yi.jetpackDemo.retrofit.utils.toMd5
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset

/**
 * 按照性能埋点安全验证方法
 * http://iwork.gaosiedu.com/pages/viewpage.action?pageId=100995211
 */
open class PerformanceLogInterceptor : Interceptor {

//    APP
//    hdfiosafjisoadjfpsa23782kdfmds

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val builder = request.newBuilder()
        val method = request.method()
        var url = request.url()
        var body = request.body()
        val buffer = Buffer()
        body?.writeTo(buffer)
        val bodyStr = buffer.readString(Charset.defaultCharset())
        Logger.t(RETROFIT_TAG)
            .d("PerformanceLogInterceptor body= $bodyStr")
        val contentMd5 = bodyStr.toMd5()//

        Logger.t(RETROFIT_TAG)
            .d("PerformanceLogInterceptor contentMd5= $contentMd5 ")

//        val date = System.currentTimeMillis().toString()
        val date = "1610608506000"
        builder.addHeader("keyId", getKeyId())
        builder.addHeader("Date", date)
        builder.addHeader("Authorization", generatorAuth(method, contentMd5, date))
        request = builder.build()
        return chain.proceed(request)
    }

    /**
     * SignString = VERB + "\n" + CONTENT-MD5 + "\n" + DATE
     * Signature = base64(hmac-sha1(SignString,secretKey))
     * Authorization = "LOG"  + userId + ":" + Signature
     * sdfhaljfiaipw
     */
    private fun generatorAuth(method: String, contentMd5: String?, date: String): String {
//        val signString = "POST\\n875264590688CA6171F6228AF5BBB3D2\\n1610344372000"
        val signString = "$method\\n$contentMd5\\n$date"
        Logger.t(RETROFIT_TAG)
            .d("PerformanceLogInterceptor generatorAuth signString= $signString")
        val signature: String = signString.toHmacSha1(getSecretKey())
        Logger.t(RETROFIT_TAG)
            .d("PerformanceLogInterceptor generatorAuth signature= $signature")

        val afterBase64 = signature.toBase64()
        Logger.t(RETROFIT_TAG)
            .d("PerformanceLogInterceptor generatorAuth afterBase64= $afterBase64")
        val auth = "LOG".plus(getKeyId()).plus(":").plus(afterBase64)
        return auth
    }

    /**
     * userId是再日志管理中心申请的，不是用户id
     */
    open fun getKeyId(): String = ""
    open fun getSecretKey(): String = ""
}