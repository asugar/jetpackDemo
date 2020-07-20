package com.yi.jetpackDemo.bind.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 可以再同一个activity下的fragment里共享
 */
class SharedViewModel : ViewModel() {
    val selected = MutableLiveData<Int>()

    fun select(item: Int) {
        selected.value = item
    }
}