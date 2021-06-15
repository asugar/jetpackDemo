package com.yi.kotlin.collection

/**
 * 序列
 * @author wanghuayi
 * @version
 * @since 2021/6/11
 */

/**
 * 另一种容器类型
 * Sequence vs Iterable
 * 一个竖向执行，一个是横向执行
 * 序列可以避免生成中间步骤的结果，从而提高了整个集合处理链的性能
 */
private fun testSequence() {
    /**
     * 构造
     */
    val ns = sequenceOf("one", "two", "three", "four")

    /**
     * 由Iterable通过asSequence创建序列
     */
    val numbers = listOf("one", "two", "three", "four")
    val ns2 = numbers.asSequence()
    ns2.forEach {
        print(it)
    }
    /**
     * 由函数
     */
    val ns3 = generateSequence(1) {
        if (it > 9) {
            null
        } else {
            it + 2
        }
    }
    /**
     * 由组块
     */
}

fun main() {
    val ns3 = generateSequence(1) {
        if (it > 9) {
            null
        } else {
            it + 2
        }
    }
    println(ns3.toList())
}