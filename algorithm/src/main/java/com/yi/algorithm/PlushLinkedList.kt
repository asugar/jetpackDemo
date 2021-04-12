package com.yi.algorithm

/**
 * 链表相加
 * 1-> 2-> 3
 * 4-> 5-> 6
 * 5   7   9
 */
fun main() {
    val first = Node(1, Node(2, Node(3, null)))
    val second = Node(4, Node(5, Node(6, null)))
    val result = linkedListPlus2(first, second)
    println(result)
}

private fun linkedListPlus2(first: Node, second: Node): Node {
    var firstTemp: Node? = first
    var secondTemp: Node? = second
    println("--- $firstTemp")
    println("--- $secondTemp")
    while (firstTemp?.value != null && secondTemp?.value != null) {
        println("--- $firstTemp")
        firstTemp.value = firstTemp.value?.plus(secondTemp.value!!)
        firstTemp = firstTemp.next
        secondTemp = secondTemp.next
    }
    // 处理剩下部分的copy
    return first
}

private fun linkedListPlus(first: Node, second: Node): Node {
    val result = Node(null, null)
    var current: Node? = first
    while (current?.value != null) {
        if (result.value == null) {
            result.value = current.value
        } else {
            var temp: Node? = result
            while (temp?.next != null) {
                temp = temp.next
            }
            temp?.next = Node(current.value, null)
        }

        println("linkedListPlus ${current.value}")
        current = current.next
    }

    current = second
    var current2: Node? = result
    while (current?.value != null) {
        current2?.value = current2?.value?.plus(current.value!!)

        current = current.next
        current2 = current2?.next
    }
    return result
}

private fun initNode(): Node {
    return Node(1, Node(2, Node(3, null)))
}

data class Node(
    var value: Int? = null,
    var next: Node? = null
)






