package com.yi.jetpackDemo.retrofit.manager.exception

class ResultException : IllegalStateException {
    private var code: Int = 0

    constructor()
    constructor(s: String?) : super(s)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)

    constructor(code: Int?, message: String?) : super(message) {
        if (code != null) {
            this.code = code
        }
    }

    fun getErrorCode(): Int = code

    override fun toString(): String {
        return "ResultException(code=$code, message=$message)"
    }

}