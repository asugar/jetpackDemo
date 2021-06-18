package com.yi.kotlin.collection

/**
 * 取集合的一部分
 * @author wanghuayi
 * @version
 * @since 2021/6/18
 */

/**
 * slice 返回具有给定索引的集合元素列表
 * 区间 整数集合
 */
private fun testSlice() {
    val nums = listOf("one", "two", "three", "four", "five", "six")
    println(nums.slice(1..3))
    println(nums.slice(0..4 step 2))
    println(nums.slice(setOf(3, 5, 0)))
}

/**
 * take:从头开始后去指定数量的元素，takeLast：从尾开始获取指定数量的元素，当调用的数字大于集合的大小时，两个函数都将返回整个集合
 * drop：和take相反，dropLast too
 * 使用谓词
 * takeWhile：
 * takeLastWhile
 * dropWhile
 * dropLastWhile
 * 碰到第一个不匹配的就开始中断
 */
private fun testTakeAndDrop() {
    val nums = listOf("one", "two", "three", "four", "five", "six")
    println(nums.take(3))
    println(nums.takeLast(3))
    println(nums.drop(3))
    println(nums.dropLast(3))
    println(nums.takeWhile { it.length <= 3 })// one two three
    println(nums.takeLastWhile { it.length <= 3 })// four five six
    println(nums.dropWhile { it.length == 3 })// four five six
    println(nums.dropLastWhile { it.length == 3 })// one two three
}

/**
 * chunked - 块
 * // size 是按照size分块
 */
private fun testChunked() {
    val numbers = (0..13).toList()
    println(numbers.chunked(3))
    println(numbers.chunked(3) { it.sum() })
}

/**
 * windowed
 * 按照size大小的所有窗口的可能
 * zipWithNext
 */
private fun testWindowed(){
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.windowed(3))
}

fun main() {
    testChunked()
//    testTakeAndDrop()
//    testSlice()
}