package com.yi.jetpackDemo.lunchModel

import android.content.Context
import android.content.Intent
import android.view.View

const val TAG_LAUNCH_MODEL = "launchModel"

class ClickHandler(val context: Context) {

    fun onClickA(view: View) {
        val intent = Intent(context, ActivityB::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK 无效
        context.startActivity(intent)
    }

    fun onClickB(view: View) {
        val intent = Intent(context, ActivityC::class.java)
        context.startActivity(intent)
    }

    fun onClickC(view: View) {
        val intent = Intent(context, ActivityA::class.java)
        // standard模式设置该flags无效
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP+singleTop == singleTask
        context.startActivity(intent)
    }
}