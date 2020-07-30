package com.yi.jetpackDemo.bind.viewModels

import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.ObservableArrayMap

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
}