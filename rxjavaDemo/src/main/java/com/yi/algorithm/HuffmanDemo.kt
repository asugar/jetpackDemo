package com.yi.algorithm

import java.util.*
import kotlin.Comparator

/**
 * 霍夫曼数demo
 * https://mp.weixin.qq.com/s/Oqtlh5mOXBgDAa8fGOcu2Q
 */

fun main() {
    val n = 4
    val charArray = charArrayOf('A', 'B', 'C', 'D')
    val charfreq = arrayOf(5, 1, 6, 3)
    // 先进先出模式
    val q = PriorityQueue<HuffmanNode>(n, ImplementComparator())
    for (i in (0 until n)) {
        val hn = HuffmanNode(charfreq[i], charArray[i], null, null)
        q.add(hn)
    }

    var root: HuffmanNode? = null
    while (q.size > 1) {
        val x = q.peek()
        q.poll()
        val y = q.peek()
        q.poll()

        val f = HuffmanNode(x.item + y.item, '-', x, y)
        root = f
        q.add(f)
    }

    System.out.println(" 字符 | 霍夫曼编码 ");
    System.out.println("--------------------");
    println("${root?.c} | ${root?.item}")
//    println("${root?.left?.c} | ${root?.left?.item}")
//    println("${root?.right?.c} | ${root?.right?.item}")
//    println("${root?.left?.left?.c} | ${root?.left?.left?.item}")
//    println("${root?.right?.left?.c} | ${root?.right?.left?.item}")
    printCode(root, "")
}

private fun printCode(root: HuffmanNode?, str: String) {
    if (root != null) {
        println("${root.item} | ${root.c}")
        printCode(root.left, "")
        printCode(root.right, "")
    }
}

data class HuffmanNode(
    var item: Int,
    var c: Char,
    var left: HuffmanNode?,
    var right: HuffmanNode?
)

class ImplementComparator : Comparator<HuffmanNode> {
    override fun compare(o1: HuffmanNode, o2: HuffmanNode): Int {
        return o1.item - o2.item
    }
}



