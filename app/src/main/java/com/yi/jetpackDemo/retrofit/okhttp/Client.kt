package com.yi.jetpackDemo.retrofit.okhttp

import com.yi.jetpackDemo.MyApplication
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import kotlin.concurrent.thread

class Client {

}

fun main() {
    //创建OkHttpClient
    val client = OkHttpClient.Builder().build()

    //创建请求
    val request = Request.Builder()
        .url("${MyApplication.TRAIN_HOST}thewolverine/trainCalendar/getMonthListForApp")
        .build()

    //同步任务开启新线程执行
    thread {
        val response = client.newCall(request).execute()
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        println("okhttp_test, response:  ${response.body()?.string()}")
    }
}