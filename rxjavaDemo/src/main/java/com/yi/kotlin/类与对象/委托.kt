package com.yi.kotlin.类与对象

/**
 * 委托
 * @author wanghuayi
 * @version
 * @since 2021/6/3
 */
/**
 * 委托模式是实现继承的一个很好的替代方式
 */
interface Base {
    val message: String
    fun print()
    fun printMessage()
}

class BaseImpl(val x: Int) : Base {
    override val message: String
        get() = "BaseImpl $x"

    override fun print() {
        println(x)
    }

    override fun printMessage() {
        println("$x $message")
    }
}

/**
 * 此处没有实现Base接口，通过by-子句表示b将会在Derived中内部存储
 */
class Derived(b: Base) : Base by b {
    // 在b的printMessage中不会访问到这个属性
    override val message: String
        get() = "Derived $x"

    override fun printMessage() {
        println("abc")
    }
}

fun main() {
    val b = BaseImpl(10)
    val derived = Derived(b)
    derived.print()
    derived.printMessage()
    println("${derived.message}")
}






