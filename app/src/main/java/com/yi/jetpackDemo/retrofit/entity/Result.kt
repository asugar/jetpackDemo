package com.yi.jetpackDemo.retrofit.entity

/**
 * 网络请求的外壳
 */
data class Result<T>(
    var status: Int = 0,
    var errorCode: Int = 0,
    var errorMessage: String = "",
    var body: T? = null
) {
    fun isSuccessful(): Boolean = (status == 1 && errorCode == 0)

    override fun toString(): String {
        return "Result(status=$status, errorCode=$errorCode, errorMessage='$errorMessage', body=$body)"
    }
}