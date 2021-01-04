package com.yi.patterns.decorator

class FlyCarDecorator : CarDecorator {

    constructor(decoratedCar: Car) : super(decoratedCar)

    override fun run() {
        decoratedCar?.run()
        fly()
    }

    private fun fly() {
        println("开启飞行汽车模式")
    }
}