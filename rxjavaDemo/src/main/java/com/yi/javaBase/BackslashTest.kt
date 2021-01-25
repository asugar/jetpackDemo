package com.yi.javaBase

import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * 反斜杠删除
 */

fun main() {
    val str = "{\\\"one\\\":\\\"111\",\\\"two\":\"222\",\"three\":\"333\"}"
    println("str= ${str.replace("\\\\", "")}")

    val map = HashMap<String, String>()
    val cMap = ConcurrentHashMap<String, String>()
    map.put("1","1")
    val table = Hashtable<String, String>()
    table.put("2","2")


}






