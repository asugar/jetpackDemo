package com.yi.jetpackDemo.retrofit.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun main() {
    testAnotaion()
}

private fun testAnotaion() {
    val user =
        User(
            0,
            "xiaoyi",
            "beijing",
            "13688889999",
            "china",
            arrayOf("小易", "littleEasy", "大空翼")
        )
    val userJson = getGson().toJson(user)
    println(userJson)

    val userNew = getGson().fromJson<User>(userJson, User::class.java)
    println(userNew.toString())
}

private fun getGson(): Gson {
    val gsonBuilder = GsonBuilder()
//    gsonBuilder.setVersion(1.0)
//    gsonBuilder.registerTypeAdapter(User::class.java, UserSerializer())
//    gsonBuilder.registerTypeAdapter(User::class.java, UserDeserializer())
    gsonBuilder.registerTypeAdapter(User::class.java, UserTypeAdapter())
    return gsonBuilder.create()
}