package com.yi.jetpackDemo.retrofit.entiry

/**
 * 网络请求的外壳
 */
data class Result<T>(
    var status: Int = 0,
    var errorCode: Int = 0,
    var errorMessage: String = "",
    var body: T? = null
) {
    fun isSuccessful(): Boolean = status == 1 && errorCode == 0
}