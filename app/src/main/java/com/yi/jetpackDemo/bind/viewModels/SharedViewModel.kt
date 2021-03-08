package com.yi.jetpackDemo.bind.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 可以再同一个activity下的fragment里共享数据
 */
class SharedViewModel : ViewModel() {
    var countDown = MutableLiveData<Int>(10)
}