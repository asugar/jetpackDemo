package com.yi.kotlin.collection

import java.util.*

/**
 * 构造集合
 * @author wanghuayi
 * @version
 * @since 2021/6/9
 */

/**
 * 由元素构造
 * arrayof
 */
val l = listOf<Int>(1, 2, 3)
val s = setOf(1, 2, 3)
val ml = mutableListOf<Int>(1, 2, 3)
val ms = mutableSetOf(1, 2, 3)
val m = mapOf<String, Int>("one" to 1, "two" to 2, "three" to 3)
val mm = mutableMapOf(Pair("one", 1), Pair("two", 2), "three" to 3)

val al = arrayListOf(1, 2, 3)// 不可变
val a = arrayOf(1, 2, 3)

val lhm = linkedMapOf<String, Int>("" to 1)
val lhs = linkedSetOf(1, 2, 3)

/**
 * 空集合
 */
val el = emptyList<String>()

/**
 * list的初始化函数
 */
val ll = List(3) { it * 2 }// 默认返回mutableList

/**
 * 具体类型构造函数
 */
val linkedList = LinkedList<String>(listOf("one", "two", "three"))

/**
 * 复制
 * 使用toList，toMutabelList，toSet
 */
val sourceList = mutableListOf(1, 2, 3)
val copyList = sourceList.toMutableList()
val copySet = sourceList.toMutableSet()

val o = sourceList.map { it * 2 }
val o2 = sourceList.filter { it > 1 }
fun main() {
    println(o)
    println(o2)
}