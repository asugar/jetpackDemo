package com.yi.kotlin.collection

/**
 * 取单个元素
 * @author wanghuayi
 * @version
 * @since 2021/6/18
 */

/**
 * list 使用索引访问操作符（get()或者[]）更为习惯
 * set：LinkedHashSet SortedSet 使用elementAt
 * first() last() 第一个和最后一个
 * elementAtOrNull 返回元素或者null
 * elementAtOrElse 使用一个lambda表达式
 */
private fun testElementAt() {
    val numbers = linkedSetOf("one", "two", "three", "four", "five")
    println(numbers.elementAt(3))

    val numbersSortedSet = sortedSetOf("one", "two", "three", "four")
    println(numbersSortedSet.elementAt(0)) // 元素以升序存储

    println(numbers.elementAtOrElse(6) { "from else" })
}

/**
 * first： 得到对其调用谓词产生true的第一个元素
 * last： 带有一个谓词last()返回与其匹配的最后一个元素，而不是从后开始匹配
 * 可以使用别名
 * find == firstOrNull
 * findLast == lastOrNull
 */
private fun testFirst() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.first { it.length > 3 }) // three
    println(numbers.last { it.startsWith("f") }) // five
    numbers.firstOrNull { it == "two" }
}

/**
 * 随机取元素-random()函数
 */
private fun testRandom() {
    val nums = listOf(1, 2, 3, 4)
    println(nums.random())
    nums.randomOrNull()
}

/**
 * 检查存在与否 使用contains()函数
 * 如果一次检查多个实例存在，可以使用这些实例的集合作为参数调用containsAll
 * isEmpty或者isNotEmpty检查集合是否为空或者不为空
 */
private fun testContains() {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.contains("four"))
    println("zero" in numbers)

    println(numbers.containsAll(listOf("four", "two")))
    println(numbers.containsAll(listOf("one", "zero")))
}

fun main() {
    testFirst()
}


