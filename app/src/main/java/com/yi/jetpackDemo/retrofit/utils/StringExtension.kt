package com.yi.jetpackDemo.retrofit.utils

import android.util.Base64
import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.retrofit.RETROFIT_TAG
import java.nio.charset.Charset
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


fun String.toMd5(): String {
    try {
        val instance: MessageDigest = MessageDigest.getInstance("MD5")
        val digest: ByteArray = instance.digest(this.toByteArray())
        var sb: StringBuffer = StringBuffer()
        for (b in digest) {
            //获取低八位有效值
            var i: Int = b.toInt() and 0xff
            //将整数转化为16进制
            var hexString = Integer.toHexString(i)
            if (hexString.length < 2) {
                //如果是一位的话，补0
                hexString = "0" + hexString
            }
            sb.append(hexString)
        }
        return sb.toString().toUpperCase()
    } catch (e: Exception) {
    }
    return ""
}

fun String.toHmacSha1(key: String): String {
    if (this.isNullOrEmpty() || key.isNullOrEmpty()) {
        return ""
    }
    val type = "HmacSHA1"
    val mac: Mac = Mac.getInstance(type)
    val secret =
        SecretKeySpec(key.toByteArray(Charset.defaultCharset()), type)
    mac.init(secret)
    val digest: ByteArray = mac.doFinal(this.toByteArray(Charset.defaultCharset()))

    val hexArray = byteArrayOf(
        '0'.toByte(), '1'.toByte(), '2'.toByte(), '3'.toByte(),
        '4'.toByte(), '5'.toByte(), '6'.toByte(), '7'.toByte(),
        '8'.toByte(), '9'.toByte(), 'a'.toByte(), 'b'.toByte(),
        'c'.toByte(), 'd'.toByte(), 'e'.toByte(), 'f'.toByte()
    )
    val hexChars = ByteArray(digest.size * 2)
    for (j in digest.indices) {
        val v: Int = digest[j].toInt() and 0xFF
        hexChars[j * 2] = hexArray[v ushr 4]
        hexChars[j * 2 + 1] = hexArray[v and 0x0F]
    }
    Logger.t(RETROFIT_TAG).d("hmacSha1= ${hexChars.toString(Charset.defaultCharset())}")
    return hexChars.toString(Charset.defaultCharset())
}

fun String.toBase64(): String {
    return Base64.encodeToString(this.toByteArray(Charset.defaultCharset()), Base64.NO_WRAP)
}