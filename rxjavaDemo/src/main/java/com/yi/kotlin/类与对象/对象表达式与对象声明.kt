package com.yi.kotlin.类与对象

/**
 *
 * @author wanghuayi
 * @version
 * @since 2021/6/2
 */
/**
 * 对象表达式
 * 1）可以带参数
 * 2）可以实现多个接口；一个继承类
 */
open class A(x: Int) {
    public open val y: Int = x
}

interface B {
    fun b()
}

val ab: A = object : A(1),
    B {
    override val y: Int
        get() = 6

    override fun b() {
        println("this is b")
    }
}

/**
 * 如果我们只需要一个对象而已，并不需要超类类型，我们可以简单的写：
 */
fun foo() {
    val adHoc = object {
        val x: Int = 0
        val y: Int = 0
    }
    println(adHoc.x + adHoc.y)
}

/**
 * 匿名对象可以用作只在本地和私有作用域中声明的类型
 */
class C {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "xx"
    }

    fun bar() {
        val x1 = foo().x // 没问题
//        val x2 = publicFoo().x // 错误：未能解析的引用x
    }
}

/**
 * 对象声明
 */

/**
 * 对象声明的初始化过程是线程安全的并且在首次访问时进行
 * 继承超类；实现接口
 */
object DefaultListener : A(1),
    B {
    override fun b() {

    }
}

/**
 * 伴生对象
 * 1）可以有名字也可省略，名字默认是Companion
 * 2）当然也可以继承超类和实现接口
 * 注意伴生对象不一定是静态的，如果想成为静态方法和字段，需要使用@JvmStatic注解
 */
class MyClass {
    companion object Factory : A(1),
        B {
        fun create(): MyClass =
            MyClass()
        override fun b() {
        }
    }
}

val x = MyClass


/**
 * 对象表达式和对象声明之间有一个重要的语义差别：
    对象表达式是在使用他们的地方立即执行（及初始化）的；
    对象声明是在第一次被访问到时延迟初始化的；
    伴生对象的初始化是在相应的类被加载（解析）时，与 Java 静态初始化器的语义相匹配。
 */







