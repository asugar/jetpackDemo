package com.yi.patterns.decorator

/**
 * Decorator角色 -- 装饰器得抽象类
 */
open class CarDecorator : Car {

    protected var decoratedCar: Car? = null

    constructor(decoratedCar: Car) {
        this.decoratedCar = decoratedCar
    }

    override fun run() {
        decoratedCar?.run()
    }
}