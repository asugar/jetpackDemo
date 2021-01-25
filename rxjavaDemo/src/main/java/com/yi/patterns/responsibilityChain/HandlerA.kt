package com.yi.patterns.responsibilityChain

class HandlerA : Handler() {

    override fun handleRequest(msg: String): String {
        var result = msg
        if (result.contains("a")) {
            result = result.replace('a', '*')
        } else if (this.mSuccessor != null) {
            result = this.mSuccessor!!.handleRequest(msg)
        }
        return result
    }
}