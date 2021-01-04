package com.yi.patterns.decorator

class AutoCarDecorator : CarDecorator {

    constructor(decoratedCar: Car) : super(decoratedCar)

    override fun run() {
        decoratedCar?.run()
        autoRun()
    }

    private fun autoRun() {
        println("开启自动驾驶")
    }
}