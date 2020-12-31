package com.yi.jetpackDemo.killed

object API {

    private lateinit var retrofit: String

    fun init(host: String) {
        retrofit = "初始化"
    }

    fun create(): String = retrofit

}