package com.yi.kotlin.类与对象

/**
 * vs 内联方法
 * @author wanghuayi
 * @version
 * @since 2021/6/2
 */
/**
 * 使用inline 定义
 * 必须包含唯一的一个属性在主构造中初始化，不能有init代码块和委托属性
 * 可以有属性和函数
 * 可以实现接口，不能继承类
 */
inline class Password(val s: String) {
    val length: Int
        get() = s.length

    fun greet() {
        println("hello, #s")
    }
}

fun main() {
    val securePassword = Password("123455")
}

/**
 * todo 不太理解内联类有什么作用
 */






