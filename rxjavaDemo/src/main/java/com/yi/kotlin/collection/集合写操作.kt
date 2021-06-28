package com.yi.kotlin.collection

/**
 * 集合写操作
 * @author wanghuayi
 * @version
 * @since 2021/6/21
 */

/**
 * 使用MutableCollection的借口
 * 添加元素
 * add addAll
 * plus运算符-plusAssign(+=)添加元素
 *
 * 删除元素 remove
 * removeAll()：移除所有元素
 * retainAll()：和removeAll相反，一处除参数集合中的元素之外的所有元素
 */
private fun testWriteOperator() {
    val nums = mutableListOf(1, 2, 3, 4, 5, 6)
//    println()
    nums.retainAll { it >= 3 }
}



