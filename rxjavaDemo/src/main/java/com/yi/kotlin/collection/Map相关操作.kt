package com.yi.kotlin.collection

import android.os.Build
import androidx.annotation.RequiresApi

/**
 * map相关操作
 * @author wanghuayi
 * @version
 * @since 2021/6/25
 */

@RequiresApi(Build.VERSION_CODES.N)
private fun testGet() {
    val maps = mapOf("one" to 1, "two" to 2, "three" to 3)
    maps.get("one")
    maps["one"]
    maps.getOrDefault("foure", 6)
    maps.keys
    maps.values
}

private fun testFilter() {
    val maps = mapOf("one" to 1, "two" to 2, "three" to 3)
    maps.filter { it.value < 6 }
    maps.filterKeys { it.startsWith("t") }
    maps.filterValues { it < 6 }
    maps.filterNot { it.value > 6 }
    val newMaps = mutableMapOf<String, Int>()
    maps.filterTo(newMaps, { it.value < 6 })// 把符合条件的map放进一个新的map里
    println("newMaps= $newMaps ${newMaps.hashCode()}")
}

/**
 * plus +
 * minus -
 * plusAssign +=
 * minusAssign -=
 */
private fun testPlusAndMinus() {
    val maps = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
    println(maps.plus("four" to 4))
    println(maps + Pair("five", 5))
    println(maps + mapOf("six" to 6, "one" to 11))
    maps += Pair("a", 65)
    maps.minusAssign("one")
    println(maps)
}

/**
 * map写操作
 * 1）value可以更新，key永远不会改变
 * 2）每个键都有一个与之关联的值，也可以添加和删除整个条目
 */
private fun testSet() {
    val maps = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
    maps.put("three", 33)
    maps.putAll(mapOf("1" to 1, "2" to 2))
    maps.remove("two")
    maps.keys.remove("2")
    maps.values.remove(2)
    maps -= "three"
}

fun main() {
    testPlusAndMinus()
//    testFilter()
}