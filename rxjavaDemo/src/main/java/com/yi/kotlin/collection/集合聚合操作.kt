package com.yi.kotlin.collection

/**
 * 集合聚合操作
 * @author wanghuayi
 * @version
 * @since 2021/6/21
 */

/**
 * min() max() 返回集合中最小和最大的元素
 * average() 返回数字集合中元素平均值
 * sum() 返回数字集合中元素的总和
 * count() 返回集合中元素的数量
 * maxBy()/minBy() 接受一个选择器函数并返回使选择器返回最大或最小值的函数
 * maxWith()/minWith() 接受一个comparator对象并且根据此Comparator对象返回最大或最少元素
 */
private fun testOperators() {
    val nums = listOf(1, 2, 3)
    println("count= ${nums.count()}")
    println("max= ${nums.maxOrNull()}")
    println("min= ${nums.minOrNull()}")
    println("average= ${nums.average()}")
    println("sum= ${nums.sum()}")

    println("minBy ${nums.minByOrNull { it % 2 }}")
    println("minWith ${nums.maxWithOrNull(compareBy { it % 2 })}")

}

/**
 * 依次将所提供的操作应用于集合元素并返回累积的结果
 * 操作有两个参数：先前的累积值和集合元素
 * 区别：
 * fold() 接受一个初始值并将其用作第一步的累积值，而reduce的第一步则将第一个和第二个元素作为第一步的操作参数
 * reduceRight foldRight 从最后一个元素开始
 * foldIndex reduceIndexed 添加了index作为参数
 */
private fun testFoldAndReduc() {
    val nums = listOf(1, 2, 3, 4, 5, 6)
    val sum = nums.fold(1) { sum, e -> sum + e }
    println(sum)//22
    val sum2 = nums.reduce { acc, i -> acc + i }
    println(sum2)//21
    val sum3 = nums.foldIndexed(1, { idx, acc, e ->
        println(idx)
        acc + e
    })
    println(sum3)
}

fun main() {
    testFoldAndReduc()
//    testOperators()
}