package com.yi.jetpackDemo.retrofit.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yi.jetpackDemo.retrofit.entity.Result
import kotlinx.coroutines.launch

fun <T> ViewModel.requestDataSources(
    requestBlock: suspend () -> Result<T>,
    successBlock: (T?) -> Unit,
    errorBlock: (message: String) -> Unit
) {
    viewModelScope.launch {
        runCatching {
            // 开始请求
            requestBlock()
        }.onSuccess {
            // 请求成功
            if (it.isSuccessful()) {
                // 请求成功
                successBlock(it.body)
            } else {
                // 请求失败
                errorBlock(it.errorMessage)
            }
        }.onFailure {
            // 请求失败
            Log.e("error", "失败:" + it.message)
            errorBlock(it.message.toString())
        }
    }
}