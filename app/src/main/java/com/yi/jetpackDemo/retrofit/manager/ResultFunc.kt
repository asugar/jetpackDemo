package com.yi.jetpackDemo.retrofit.manager

import com.yi.jetpackDemo.retrofit.entity.Result
import com.yi.jetpackDemo.retrofit.manager.exception.ResultException
import io.reactivex.functions.Function

class ResultFunc<T> : Function<Result<T>, T> {

    override fun apply(result: Result<T>): T {

        if (!result.isSuccessful()) {
            throw ResultException(result.errorCode, result.errorMessage)
        }
        if (result.body == null) {
            throw ResultException()
        }

        return process(result.body!!)
    }

    open fun process(t: T): T = t

}