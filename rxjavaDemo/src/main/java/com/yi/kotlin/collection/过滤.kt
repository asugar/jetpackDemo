package com.yi.kotlin.collection

/**
 * 过滤
 * @author wanghuayi
 * @version
 * @since 2021/6/15
 */

/**
 * filter()过滤函数对于List和Set，过滤结果返回list，对于map结果还是map
 * filterIndexed() 返回index和value
 */
private fun testFilter() {
    val nums = listOf("one", "two", "three", null, "four", "five", "six")
    println(nums.filter { it?.length ?: 0 > 3 })
    println(nums.filterNotNull().filterNot { it.length < 3 })
//    nums.filterIsInstance<>()
    val maps = mapOf("one" to 1, "two" to 2, "three" to 3)
    maps.filter { it.key.length > 2 }
}

/**
 * 划分 partition
 */
private fun testPartition() {
    val nums = listOf("one", "two", "three", "four", "five", "six")
    val (match, rest) = nums.partition { it.length > 3 }
    println(match)// 匹配的
    println(rest)// 剩余的
}

/**
 * 检查谓词
 * any() 至少又一个元素匹配给定谓词，返回true
 * none() 没有元素与给定谓词匹配，返回true
 * all() 所有元素都匹配给定谓词，返回true
 */
private fun testCheck() {
    val nums = listOf("one", "two", "three", "four", "five", "six")
    println(nums.any { it.endsWith("s") })
    println(nums.none { it.endsWith("s") })
    println(nums.all { it.length > 2 })
}


fun main() {
//    testFilter()
//    testPartition()
    testCheck()
}