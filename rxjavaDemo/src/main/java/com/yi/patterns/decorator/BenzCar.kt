package com.yi.patterns.decorator

/**
 * ConcreteComponent角色 -- 具体组件
 */
class BenzCar : Car {

    override fun run() {
        println("奔驰开车了！")
    }
}