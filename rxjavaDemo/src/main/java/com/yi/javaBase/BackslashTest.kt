package com.yi.javaBase

/**
 * 反斜杠删除
 */

fun main() {
    val str = "{\\\"one\\\":\\\"111\",\\\"two\":\"222\",\"three\":\"333\"}"
    println("str= ${str.replace("\\\\", "")}")
}







