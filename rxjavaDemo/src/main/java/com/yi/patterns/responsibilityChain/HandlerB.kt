package com.yi.patterns.responsibilityChain

class HandlerB : Handler() {

    override fun handleRequest(msg: String): String {
        var result = msg
        if (result.contains("b")) {
            result = result.replace('b', '*')
        } else if (this.mSuccessor != null) {
            result = this.mSuccessor!!.handleRequest(msg)
        }
        return result
    }
}