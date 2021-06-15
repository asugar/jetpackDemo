package com.yi.kotlin.collection

/**
 * 迭代器
 * 只能使用一次，迭代一遍后，如果想再次遍历集合，请创建一个新的迭代器
 * @author wanghuayi
 * @version
 * @since 2021/6/10
 */

/**
 * list遍历
 */
private fun testListIterator() {
    val numbers = listOf<String>("one", "two", "three")
    val numbersIterator = numbers.iterator()
    while (numbersIterator.hasNext()) {
        println(numbersIterator.next())
    }
    for (item in numbers) {
        println(item)
    }
    numbers.forEach {
        println(it)
    }
}


private fun testMapIterator() {
    val map = mapOf<String, Int>("one" to 1, "two" to 2, "three" to 3)
    val mapIterator = map.iterator()
    while (mapIterator.hasNext()) {
        println("${mapIterator.next()} ")
    }
}

/**
 * List迭代器
 * ListIterator支持双向迭代：正向和反向
 * hasPrevious previous
 */
private fun testListIterator2() {
    val numbers = listOf<String>("one", "two", "three")
    val listIterator = numbers.listIterator()
    while (listIterator.hasNext()) listIterator.next()
    println("Iterating backwards:")
    while (listIterator.hasPrevious()) {
        print("Index: ${listIterator.previousIndex()}")
        println(", value: ${listIterator.previous()}")
    }
}

/**
 * 可变迭代器
 * 在迭代的过程中删除元素
 * java中iterator也是迭代过程中可以删除元素的
 */
private fun testMutableIterator() {
    val numbers = mutableListOf("one", "two", "three")
    val iterator = numbers.listIterator()
    iterator.next()
    iterator.remove()
    iterator.next()
    iterator.set("2")
}


fun main() {
//    testListIterator()
//    testMapIterator()
    testListIterator2()
}

