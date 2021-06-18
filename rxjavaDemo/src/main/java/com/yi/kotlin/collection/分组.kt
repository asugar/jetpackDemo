package com.yi.kotlin.collection

/**
 * 分组
 * @author wanghuayi
 * @version
 * @since 2021/6/18
 */

/**
 * 使用groupBy分组
 * 返回结构：Map<K, List<T>>
 *     keySelector-map的key
 *     valuseTransform-对value的转换
 *     groupingBy 一次将操作应用与所有分组
 *     支持eachCount()
 *     fold() reduce
 *     aggregate
 */
private fun testGroupBy() {
    val nums = listOf("one", "two", "three", "four", "five")
    println(nums.groupBy { it.first().toUpperCase() })
    println(nums.groupBy(keySelector = { it.first() }, valueTransform = { it.toUpperCase() }))
    println(nums.groupingBy { it.first() }.eachCount())
}

fun main() {
    testGroupBy()
}