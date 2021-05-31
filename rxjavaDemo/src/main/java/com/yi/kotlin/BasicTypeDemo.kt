package com.yi.kotlin

/**
 * 基础
 * @author wanghuayi
 * @version
 * @since 2021/5/26
 */

fun main() {
//    testDisplay()
    testReturenAndBreak()
}

/**
 * 使用标签限制break或者continue
 */
fun testReturenAndBreak() {
    outer@ for (i in 1..10) {
        inner@ for (j in 1..5) {
            if (i == j) {
//                break@outer
                continue@outer
            }
        }
    }

    /**
     * 局部返回lambda表达式
     * foreach有默认隐式标签@forEach
     */
    listOf(1, 2, 3, 4, 5).forEach lit@{
        if (it == 3) {
            return@lit
        }
        println("--  $it")
    }
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 4) {
            return@forEach
        }
        println("--  $it")
    }

}

/**
 * 表示方式
 * 超过了-128-127的常量池，就会重新生成导致？
 */
fun testDisplay() {
    val a: Int = 100
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a

    val b: Int = 10000
    val boxedB: Int? = b
    val anotherBoxedB: Int? = b

    println(boxedA === anotherBoxedA) // true
    println(boxedB === anotherBoxedB) // false
    println(boxedB == anotherBoxedB) // false
}


