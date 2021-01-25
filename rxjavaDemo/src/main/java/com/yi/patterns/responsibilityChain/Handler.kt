package com.yi.patterns.responsibilityChain

/**
 * 抽象handler
 */
abstract class Handler {

    protected var mSuccessor: Handler? = null
    fun setSuccessor(successor: Handler) {
        this.mSuccessor = successor
    }

    abstract fun handleRequest(msg: String): String
}