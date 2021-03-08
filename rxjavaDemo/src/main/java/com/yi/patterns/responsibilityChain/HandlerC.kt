package com.yi.patterns.responsibilityChain

class HandlerC : Handler() {

    override fun handleRequest(msg: String): String {
        var result = msg
        if (result.contains("c")) {
            result = result.replace('c', '*')
        } else if (this.mSuccessor != null) {
            result = this.mSuccessor!!.handleRequest(msg)
        }
        return result
    }
}