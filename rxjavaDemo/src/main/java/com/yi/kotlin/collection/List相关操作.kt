package com.yi.kotlin.collection

/**
 * List 相关操作
 * @author wanghuayi
 * @version
 * @since 2021/6/23
 */

/**
 * 按索引取元素
 * elementAt
 */
private fun testGetByIndex() {
    val nums = listOf(1, 2, 3)
    nums.elementAt(1)
    nums.get(2)
    nums.getOrElse(6) { it }
    nums.getOrNull(6)
}

/**
 * 取列表的一部分 subList
 */
private fun testGetPart() {
    val nums = (0..13).toList()
    nums.subList(3, 6)
}

/**
 * 查找元素位置
 */
private fun testFind() {
    val nums = listOf(1, 2, 3, 4, 5, 6)
    nums.indexOf(2) // 第一个 2 出现的位置，没有返回-1
    nums.lastIndexOf(2) // 最后一个2 出现的位置，没有返回-1
    nums.indexOfFirst { it > 2 } // 返回与谓词匹配的第一个元素的索引，如果没有返回-1
    nums.indexOfLast { it > 2 } // 返回最后一个匹配的

    nums.binarySearch(3)// 二分查找，还可以指定区域
    println(nums.binarySearch(1, 5) { it - 3 })// comparison 当返回0时就是找到的元素，并返回该元素的index

    // Comparator 二分查找 todo 不太理解Comparable vs Comparator
}

/**
 * List写操作
 * add
 * addAll
 * set() 更新元素
 * fill 填充元素
 * removeAt 删除指定位置元素
 */
private fun testWrite(){

}

/**
 * 排序
 * sort
 */

fun main() {
    testFind()
}

