package com.yi.patterns.responsibilityChain

class Client {

}

fun main() {
    val handlerA = HandlerA()
    val handlerB = HandlerB()
    val handlerC = HandlerC()
    handlerA.setSuccessor(handlerB)
    handlerB.setSuccessor(handlerC)
    println(handlerA.handleRequest("apple"));
    println(handlerA.handleRequest("bicycle"));
    println(handlerA.handleRequest("color"));
}