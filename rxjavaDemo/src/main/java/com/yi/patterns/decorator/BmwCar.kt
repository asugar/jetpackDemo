package com.yi.patterns.decorator

/**
 * ConcreteComponent角色 -- 具体组件
 */
class BmwCar : Car {
    override fun run() {
        println("宝马开车了！")
    }
}