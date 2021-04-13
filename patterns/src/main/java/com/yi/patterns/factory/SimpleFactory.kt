package com.yi.patterns.factory

/**
 * 简单工厂
 * @author wanghuayi
 * @version
 * @since 2021/4/12
 */

/**
 * 产品接口
 */
interface IProduct {
    fun create()
}

/**
 * 产品实现类
 */
class ProductImp : IProduct {
    override fun create() {
        println("ProductorImp create")
    }
}

class ProductImp2 : IProduct {
    override fun create() {
        println("ProductorImp2 create")
    }
}

class ProductImp3 : IProduct {
    override fun create() {
        println("ProductorImp3 create")
    }
}

/**
 * 简单生产商
 */
class Productor {

    /**
     * 根据类型生产产品
     */
    fun createProduct(type: String): IProduct {
        return when (type) {
            "1" -> {
                ProductImp()
            }
            "2" -> {
                ProductImp2()
            }
            else -> {
                ProductImp3()
            }
        }
    }

    /**
     * 利用反射成产产品
     * out:类型是T的子类 对应 java中的<? extends T>
     * in：类型是T的父类 对应 java中的<? super T>
     */
    fun createProduct(clazz: Class<out IProduct>): IProduct {
        return clazz.newInstance()
    }
}

fun Productor.createProduct(clazz: Class<out IProduct>): IProduct {
    return clazz.newInstance()
}

class Productor2<T : IProduct> {
    /**
     * 利用反射成产产品
     */
    fun createProduct(clazz: Class<out T>): T {
        return clazz.newInstance() as T
    }
}

fun main() {
    val foctory = Productor()
    val foctory2 = Productor2<ProductImp>()
    foctory.createProduct("1").create()
    foctory.createProduct("2").create()
    foctory.createProduct("4").create()
    foctory.createProduct(ProductImp2::class.java).create()
    println("--------------------------")
    foctory2.createProduct(ProductImp::class.java).create()
    foctory.createProduct(ProductImp3::class.java).create()

}







