package com.yi.kotlin.collection

/**
 * 集合排序
 * @author wanghuayi
 * @version
 * @since 2021/6/18
 */

/**
 * Comparable接口：自然顺序，当没有指定其他顺序时，使用自然顺序为他们排序
 * Comparator接口：自定义顺序，多用在compareBy
 * compareBy - 自定义顺序
 */
private fun testComparable() {
    class Version(val major: Int, val minor: Int) : Comparator<Version>, Comparable<Version> {
        override fun compare(o1: Version, o2: Version): Int {
            println("compare")
            if (o1.major != o2.major) {
                return o2.major - o1.major
            } else if (o1.minor != o2.minor) {
                return o2.minor - o1.minor
            } else return 0
        }

        override fun compareTo(other: Version): Int {
            println("compareTo")
            if (this.major != other.major) {
                return this.major - other.major
            } else if (this.minor != other.minor) {
                return this.minor - other.minor
            } else return 0
        }
    }

    println(Version(1, 2) > Version(1, 3))
    println(Version(2, 2) > Version(1, 5))

    println(listOf("aaa", "bb", "c").sortedWith(compareBy { it.length }))
}

/**
 * sorted() 自然顺序生序
 * sortedDescending() 自然顺序降序
 * sortedBy() 自定义顺序生序 使用comparable
 * sortedByDescending() 自定义顺序降序
 */
private fun testSorted() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.sorted())
    println(numbers.sortedDescending())
    println(numbers.sortedBy { it.length })
    println(numbers.sortedByDescending { it.length })
}

/**
 * reversed() 返回带有元素副本的新集合-如果原始图改变，不会影响
 * asReversed() 返回相同集合实例的一个反向试图-如果原集合改变，会影响；更轻量，更适合
 */
private fun testReversed() {
    val nums = mutableListOf("one", "two", "three", "four")
    val re = nums.reversed()
    val re2 = nums.asReversed()
    println(re)
    println(re2)
    nums[3] = "six"
    println(re)
    println(re2)
}

/**
 * shuffed()函数，返回一个包含了以随机顺序排序的集合元素的新list
 */
private fun testShuffed() {
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.shuffled())
}

fun main() {
    testShuffed()
//    testReversed()
//    testSorted()
//    testComparable()
}