package com.yi.kotlin.类与对象

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 委托属性
 * @author wanghuayi
 * @version
 * @since 2021/6/3
 */

/**
 * by 后面就是委托
 */
class Example {
    val p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

fun main2() {
    val e = Example()
    println(e.p)
}

/**
 * 标准委托
 * lazy
 * 默认情况下，lazy属性的求值是同步锁的
 * 也可以通过mode参数指定
 */
val lazyValue: String by lazy(mode = LazyThreadSafetyMode.NONE) {
    println("computed!")
    "hello"
}

/**
 * 可观察属性Observable
 * Delegates.observable()接受两个参数：初始值与修改时处理程序
 */
class User {
    var name: String by Delegates.observable("no name") { prop, old, new ->
        println("$old -> $new")
    }
}

fun main() {
    val user = User()
    user.name = "first"
    user.name = "second"
}
/**
 * 委托给另一个属性
 * kotlin1.4开始才有
 */

/**
 * 将属性储存在映射中
 */
class User2(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

val user = User2(
    mapOf(
        "name" to "xiaoyi",
        "age" to 18
    )
)

/**
 * 局部委托属性
 */
fun example(computeFoo:() -> String){
    val memoizedFoo by lazy(computeFoo)
    if (memoizedFoo.isNotEmpty()){
        memoizedFoo.length
    }
}

/**
 * 属性委托要求
 * todo
 */

