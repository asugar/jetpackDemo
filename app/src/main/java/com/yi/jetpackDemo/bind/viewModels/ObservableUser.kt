package com.yi.jetpackDemo.bind.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableLong
import com.yi.jetpackDemo.BR

/**
 * 可观察的字段/集合/对象
 */
data class ObservableUser(
    var className: ObservableField<String>, var classId: ObservableLong
) : BaseObservable() {

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
            notifyPropertyChanged(BR.age)
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