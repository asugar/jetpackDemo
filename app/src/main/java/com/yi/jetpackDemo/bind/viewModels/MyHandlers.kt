package com.yi.jetpackDemo.bind.viewModels

import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.ObservableArrayMap

/**
 * 适合处理无过多业务的点击交互
 * 1）如果关联业务，建议使用普通接口回调
 * 2）可以使用可观察字段去做
 */
class MyHandlers {

    fun onClickFirstName(view: View) {
        Toast.makeText(
            view.context,
            "onClickFirstName ${(view as TextView).text}",
            Toast.LENGTH_SHORT
        ).show()
        Log.d("xiaoyi", "onClickFirstName")
//        user.firstName.set("xiaoyi")
    }

    fun onClickClassName(view: View, user: ObservableUser) {
        user.classId.set(user.classId.get() + 1)
    }


    fun onAgeClick(view: View, user: ObservableUser) {
        user.age++
    }


    fun onClickScore(view: View, scores: ObservableArrayMap<String, String>) {
        if (scores.size >= 10) {
            scores.clear()
        } else {
            scores[scores.size.toString()] = scores.size.toString()
        }
    }

    fun onSaveClick(view: View, key: String) {
        Toast.makeText(view.context, key, Toast.LENGTH_SHORT).show()
        Log.d("xiaoyi", "onSaveClick $key")
//        user.lastName.set("hello world")
    }

    fun onClickLiveDataName(view: View, user: UserViewModel) {
        Log.d("xiaoyi", "onClickLiveDataName isChecked= ${user.isChecked.value}")
        val age: Int? = user.age.value
        user.age.value = age?.plus(1) ?: 0
        if (user.name.value.equals("hello")) {
            user.name.value = "world"
        } else {
            user.name.value = "hello"
        }

    }

    fun onClickCountDown(view: View, share: SharedViewModel) {
        val value: Int? = share.countDown.value
        share.countDown.value = value?.minus(1) ?: 0

    }
}