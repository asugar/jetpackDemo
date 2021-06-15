package com.yi.kotlin.函数与Lambda表达式

/**
 * 高阶函数与 lambda 表达式
 * @author wanghuayi
 * @version
 * @since 2021/6/4
 */

/**
 * kotlin函数是头等的，函数可以存储在变量与数据结构中、作为参数传递给高阶函数以及从其他高阶函数返回。可以像操作任何其他非函数值一样操作函数
 */

/**
 * 函数类型
 * (Int) -> String
 * A.(B) -> C
 * 挂起函数：suspend A.(B) -> C
 */
val onClick: () -> Unit = {}

/**
 * 函数类型实例化
 * lambda表达式：{a,b-> a+b}
 * 匿名函数：fun(s: String): Int { return s.toIntOrNull() ?: 0 }
 */

/**
 * 函数类型实例调用
 * invoke操作符
 */
val stringPlus: (String, String) -> String = String::plus
val intPlus: Int.(Int) -> Int = Int::plus
fun main() {
    println(stringPlus.invoke("<-", "->"))
    println(intPlus.invoke(1, 2))
    println(2.intPlus(2))// 类扩展调用
}

/**
 * 内联函数 后面会单独讲
 */

/**
 * Lambda表达式与匿名函数
 * max(strings, {a,b -> a.length < b.length}
 */

/**
 * Lambda表达式语法
 * 函数的最后一个参数是函数，那么作为相应参数传入的lambda表达式可以放在圆括号之外
 * it: 单个参数的隐试名称
 */
val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

/**
 * 闭包
 * Lambda表达式或者匿名函数（以及局部函数和对象表达式）可以访问其闭包，即在外部作用域中声明的变量。
 */
fun bb() {
    var sum = 0
    val ints = arrayOf(1, 2, 3, 4, 5, 6)
    ints.filter { it > 0 }.forEach {
        sum += it
    }
    println(sum)
}



