package com.yi.jetpackDemo.lunchModel

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.yi.jetpackDemo.databinding.ActivityABinding

/**
 * onRestoreInstanceState 什么时机能执行
 * 横竖屏切换
 */
class ActivityA : Activity() {

    lateinit var mBinding: ActivityABinding
    val KEY_STATE = "state"
    val KEY_VIEW = "view"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "ActivityA onCreate $savedInstanceState")
        mBinding = ActivityABinding.inflate(layoutInflater)
        mBinding.handler = ClickHandler(baseContext)
        setContentView(mBinding.root)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(tag, "ActivityA onNewIntent")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "ActivityA onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "ActivityA onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "ActivityA onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "ActivityA onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "ActivityA onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "ActivityA onDestroy")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val state = savedInstanceState.getString(KEY_STATE)
        val view = savedInstanceState.getString(KEY_VIEW)
        Log.d(tag, "ActivityA onRestoreInstanceState $state $view")
        mBinding.textView.text = view?.length.toString()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        val state = persistentState?.getString(KEY_STATE)
        val view = persistentState?.getString(KEY_VIEW)
        Log.d(tag, "ActivityA onRestoreInstanceState 2 $state $view")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(tag, "ActivityA onSaveInstanceState")
        outState.run {
            putString(KEY_STATE, "open-p")
            putString(KEY_VIEW, mBinding.textView.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.d(tag, "ActivityA onSaveInstanceState 2")
        outPersistentState.run {
            putString(KEY_STATE, "open-2")
            putString(KEY_VIEW, mBinding.textView.text.toString())
        }
        super.onSaveInstanceState(outState, outPersistentState)
    }
}