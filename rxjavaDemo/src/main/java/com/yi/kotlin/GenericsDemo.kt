package com.yi.kotlin

/**
 * 泛型
 * @author wanghuayi
 * @version
 * @since 2021/5/31
 */

/**
 * 声明处型变
 * java中
 * ? extends Object 限制上界
 * ? super String   限制下界
 *
 * kotlin中
 * out 安全的转化为其父类  限定了下界  逆变
 * in  安全的转化为其子类  限定了上界  协变
 */
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs// T 被声明成out的时候，可以向上类型转化
}

interface Comparable<in T> {
    fun compareTo(other: T): Int
}

fun demo2(x: Comparable<Number>) {
    x.compareTo(1.0)
    val y: Comparable<Double> = x
}

/**
 * 类型投影
 * 使用处型变：类型投影
 * Array<out Any>  对应 Array<? extends Object>
 * Array<in String>  对应 Array<? super String>
 */
fun copy(from: Array<out Any>, to: Array<Any>) {}
fun fill(from: Array<in String>, value: String) {}

/**
 * 类型投影
 * 星投影
 * Foo<out T:TUpper>
 */
interface Foo<out T : String>// 范围更小的下界
interface Foo2<in T>

/**
 * 泛型函数
 * 函数也可以有
 */
fun <T> singletonList(item: T): List<T> {
    return listOf()
}

fun <T> T.basicToString(): String {
    return ""
}


