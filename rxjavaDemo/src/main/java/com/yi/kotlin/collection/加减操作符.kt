package com.yi.kotlin.collection

/**
 * plus 与 minus 操作符
 * @author wanghuayi
 * @version
 * @since 2021/6/17
 */

/**
 * 为集合定义了 plus（+） 和 minus（-） 操作符
 * 第一个操作数是集合，第二个操作数可以是集合也可以是一个元素，返回值是一个新的只读集合
 */
private fun testPlusAndMinus() {
    val nums = listOf("one", "two", "three")
    val plusList = nums + "six"
    val minusList = nums - listOf("two", "one")
    println(plusList)
    println(minusList)
}

fun main() {
    testPlusAndMinus()
}