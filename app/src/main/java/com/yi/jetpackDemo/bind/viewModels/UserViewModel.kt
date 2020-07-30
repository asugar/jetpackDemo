package com.yi.jetpackDemo.bind.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yi.jetpackDemo.BR

/**
 * 使用viewModel+liveData
 */
class UserViewModel : ViewModel() {
    val name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val age: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val isChecked: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(true)
    }

//    @get:Bindable
//    var isChecked2: Boolean = false
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.age)
//        }

//    @Bindable
//    fun getRememberMe(): Boolean {
//        return data.rememberMe
//    }
//
//    fun setRememberMe(value: Boolean) {
//        // Avoids infinite loops.
//        if (data.rememberMe != value) {
//            data.rememberMe = value
//
//            // React to the change.
//            saveData()
//
//            // Notify observers of a new value.
//            notifyPropertyChanged(BR.remember_me)
//        }
//    }
}