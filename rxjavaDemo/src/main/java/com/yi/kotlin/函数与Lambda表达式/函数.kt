package com.yi.kotlin.函数与Lambda表达式

/**
 * 函数
 * @author wanghuayi
 * @version
 * @since 2021/6/4
 */

/**
 * 可变数量的参数 vararg
 */
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) {
        result.add(t)
    }
    return result
}

/**
 * 中缀表示法
 * 使用infix关键字
 * 它们必须是成员函数或扩展函数
 * 必须只有一个参数
 * 其参数不得接受可变数量的参数且不能有默认值
 */
infix fun Int.shl(x: Int): Int {
    return x
}

fun test() {
    // 两个写法一样效果
    1 shl 2
    1.shl(2)
}


