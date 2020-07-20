package com.yi.jetpackDemo.bind.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.yi.jetpackDemo.BR

/**
 * 可观察对象 BaseObservable
 */
class User2 : BaseObservable() {

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var age: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }

    @get:Bindable
    var address: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.address)
        }
}