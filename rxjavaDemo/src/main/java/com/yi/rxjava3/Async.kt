package com.yi.rxjava3

import io.reactivex.Flowable

/**
 *
 *
 * @author wanghuayi
 * @version
 * @since 2021/4/12
 */

fun main() {
    val args = arrayOf("java", "android", "hello")
    Flowable.fromArray("java", "android", "hello").subscribe {
        println("test $it")
    }


}