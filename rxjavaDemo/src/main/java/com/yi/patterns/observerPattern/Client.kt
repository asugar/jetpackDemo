package com.yi.patterns.observerPattern

class Client {

}

fun main() {
    val ob = ObserverSome()
    ob.setSomeListener(SomeListenerImp())
    ob.notifySome()
}