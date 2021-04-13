package com.yi.patterns.factory

/**
 * 抽象工厂
 * @author wanghuayi
 * @version
 * @since 2021/4/12
 */

/**
 * 产品A接口
 */
interface IProductA {
    fun create()
}

class ProductAImp : IProductA {
    override fun create() {
        println("ProductAImp create")
    }
}

class ProductAImp2 : IProductA {
    override fun create() {
        println("ProductAImp create")
    }
}


/**
 * 产品B接口
 */
interface IProductB {
    fun create()
}

class ProductBImp : IProductB {
    override fun create() {
        println("ProductBImp create")
    }
}

class ProductBImp2 : IProductB {
    override fun create() {
        println("ProductBImp2 create")
    }
}

/**
 * 抽象工厂类
 */
abstract class IProductor {
    abstract fun createProductA(type: String): IProductA?
    abstract fun createProductB(type: String): IProductB?
}

/**
 * 抽象工厂1实现类
 */
class ProductorImp : IProductor() {
    override fun createProductA(type: String): IProductA? {
//        return when (type) {
//            "1" -> {
//                ProductAImp()
//            }
//            else -> {
//                ProductAImp2()
//            }
//        }
        return ProductAImp()
    }

    override fun createProductB(type: String): IProductB? {
        return ProductBImp()
    }
}

/**
 * 抽象工厂2实现类
 */
class ProductorImp2 : IProductor() {
    override fun createProductA(type: String): IProductA? {
        return ProductAImp2()
    }

    override fun createProductB(type: String): IProductB? {
//        return when (type) {
//            "1" -> {
//                ProductBImp()
//            }
//            else -> {
//                ProductBImp2()
//            }
//        }
        return ProductBImp2()
    }
}

class ProductorFactory {
    companion object {
        fun getFactory(type: String): IProductor {
            return when (type) {
                "1" -> {
                    ProductorImp()
                }
                "2" -> {
                    ProductorImp2()
                }
                else -> {
                    ProductorImp()
                }
            }
        }
    }
}

fun main() {
    val factory = ProductorFactory.getFactory("1")
    factory.createProductA("1")?.create()
    val factory2 = ProductorFactory.getFactory("2")
    factory2.createProductB("1")?.create()
}





