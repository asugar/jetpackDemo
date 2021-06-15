package com.yi.kotlin.collection

/**
 * 集合转换
 * @author wanghuayi
 * @version
 * @since 2021/6/11
 */

private fun testTransform() {
    /**
     * 映射
     * mapNotNull函数取代map
     * mapIndexedNotNull取代mapIndexed
     */
    val nums = setOf(1, 2, 3)
    println(nums.map { it * 3 })
    println(nums.mapIndexed { index, i -> index * i })
    /**
     * mapKeys
     * mapValues
     * 只针对key后者value转化
     */
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    println(numbersMap.mapKeys { it.key.toUpperCase() })
    println(numbersMap.mapValues { it.value + it.key.length })

}

/**
 * 合拢
 * 通过zip把两个集合以pair形式拼成list，新的list以最短的为准，长的列表元素舍弃
 * 通过unzip进行反解
 */
private fun testZip() {
    val colors = listOf("Red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf", "test")
    println(colors zip animals)
}

/**
 * 使用associateWith去关联一个list->map
 * associateWith vs associateBy
 * 1）key和value相反
 * 2）key值相同取最后一个
 */
private fun testAssociateWith() {
    val numbers = listOf("one 1", "two 2", "three 3")
    val newMap = numbers.associateWith { it.length }
    println(newMap)
    println(numbers.associateBy { it.length })
    println(numbers.associate { num -> num.split(" ").let { it.first() to it.last() } })
}

/**
 * 打平
 * 将一个集合嵌套集合的列表，变成一个集合
 * flattne
 * flatMap : todo
 */
private fun testFlatten() {
    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
    println(numberSets.flatten())
//    numberSets.flatMap { it.Iterator }

//    val containers = listOf(
//        StringContainer(listOf("one", "two", "three")),
//        StringContainer(listOf("four", "five", "six")),
//        StringContainer(listOf("seven", "eight"))
//    )
//    println(containers.flatMap { it.values })
}

/**
 * 字符串表示
 * 使用joinToString表示一个字符串；可以使用separotro、prefix与postfix 指定间隔符及首尾
 */
private fun testJoinToString() {
    val numbers = listOf("one", "two", "three", "four")

    println(numbers)
    println(numbers.joinToString())

    val listString = StringBuffer("The list of numbers: ")
    numbers.joinTo(listString)
    println(listString)
}

fun main() {
    testFlatten()
//    testAssociateWith()
//    testZip()
}


