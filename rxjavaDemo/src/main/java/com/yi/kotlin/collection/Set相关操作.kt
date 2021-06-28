package com.yi.kotlin.collection

/**
 * Set相关操作
 * @author wanghuayi
 * @version
 * @since 2021/6/25
 */

/**
 * union 合并集合
 * intersect 交集
 * subtract 差集
 * 可以使用中缀形式
 */
private fun testSet() {
    val numbers = setOf("one", "two", "three")

    println(numbers union setOf("four", "five"))
    println(setOf("four", "five") union numbers)

    println(numbers intersect setOf("two", "one"))
    println(numbers subtract setOf("three", "four"))
    println(numbers subtract setOf("four", "three"))
}
