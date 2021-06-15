package com.yi.kotlin.函数与Lambda表达式

import java.util.concurrent.locks.Lock

/**
 * 内联函数
 * @author wanghuayi
 * @version
 * @since 2021/6/8
 */

/**
 * 使用高阶函数会带来一些运行时的效率损失：每一个函数都是一个对象，并且会捕获一个闭包
 * 但是许多情况下通过内联化lambda表达式可以消除这类的开销
 */
inline fun <T> lock(lock: Lock, body: () -> T): T {
    return body.invoke()
}
/**
 * 内联使用inline，不使用内联使用noinline
 */


