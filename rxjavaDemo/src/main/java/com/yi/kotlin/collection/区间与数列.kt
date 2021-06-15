package com.yi.kotlin.collection

/**
 * 区间与数列
 * @author wanghuayi
 * @version
 * @since 2021/6/10
 */

private fun testRange() {
    val i = 1
    /**
     * 区间判断可用于if和for
     */
    if (i in 1..4) {
        println(i)
    }
    for (i in 1.rangeTo(4)) {

    }
    for (i in 1..4) {
        println(i)
    }
    /**
     * 反向迭代: num downTo 1
     */
    for (i in 4 downTo 1) {
        print(i)
    }
    /**
     * 任意步长迭代
     */
    for (i in 9 downTo 1 step 3) {
        print(i)
    }
    /**
     * 迭代不包含结束元数的数字区间，使用until
     */
    for (i in 1 until 10) {
        print(i)
    }
    /**
     * 区间 rangeTo函数，操作符..
     */
//    data class Version(val begin:Int,val end:Int)
//    val ver = Version(1,11) rangeTo Version(1,30)
    /**
     * 数列
     */
    println((1..10).filter { i % 2 == 0 })
}
