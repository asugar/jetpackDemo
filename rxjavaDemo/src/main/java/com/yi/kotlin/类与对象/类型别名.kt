package com.yi.kotlin.类与对象

import android.net.Network
import java.io.File

/**
 * 类型别名
 * 给比较长的类型起一个名字
 * @author wanghuayi
 * @version
 * @since 2021/6/2
 */
/**
 * 缩短范型类型
 */
typealias NodeSet = Set<Network>
typealias FileTable<K> = MutableMap<K, MutableList<File>>

/**
 * 函数类型别名
 */
typealias MyHandler = (Int, String, Any) -> Unit
typealias Predicate<T> = (T) -> Boolean

/**
 * 内部类和嵌套类别名
 */
class D {
    inner class Inner
}

class F {
    inner class Inner
}
typealias DInner = D.Inner
typealias FInner = F.Inner

fun fo(p: Predicate<Int>) = p(42)
fun main() {
    val f: (Int) -> Boolean = { it > 0 }
    println(fo(f))

    val p: Predicate<Int> = { it > 0 }
    println(listOf(1, -2).filter(p))
}




