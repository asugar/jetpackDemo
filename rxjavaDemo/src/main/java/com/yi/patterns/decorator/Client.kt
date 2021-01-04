package com.yi.patterns.decorator

fun main() {
    val benzCar: Car = BenzCar()
    val bmwCar: Car = BmwCar()
    val teslaCar: Car = TeslaCar()

    //创建自动驾驶的奔驰汽车
    val autoBenzCar: CarDecorator = AutoCarDecorator(benzCar)
    //创建飞行的、自动驾驶的宝马汽车
    val flyAutoBmwCar: CarDecorator = FlyCarDecorator(AutoCarDecorator(bmwCar))

    benzCar.run()
    bmwCar.run()
    teslaCar.run()

    autoBenzCar.run()
    flyAutoBmwCar.run()
}