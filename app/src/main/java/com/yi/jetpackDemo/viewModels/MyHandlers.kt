package com.yi.jetpackDemo.viewModels

import android.util.Log
import android.view.View
import android.widget.Toast

class MyHandlers(var user: User) {
    fun onClickFriend(view: View) {
        Toast.makeText(view.context, "onClickFriend", Toast.LENGTH_SHORT).show()
        Log.d("xiaoyi", "onClickFriend")
        user.firstName.set("xiaoyi")
    }

    fun onSaveClick(view: View, key: String) {
        Toast.makeText(view.context, key, Toast.LENGTH_SHORT).show()
        Log.d("xiaoyi", "onSaveClick $key")
        user.lastName.set("hello world")
    }

    fun onAgeClick(view: View, user2: User2) {
        user2.age++
    }
}