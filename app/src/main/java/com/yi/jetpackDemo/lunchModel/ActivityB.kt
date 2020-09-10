package com.yi.jetpackDemo.lunchModel

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.yi.jetpackDemo.databinding.ActivityBBinding

class ActivityB : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "ActivityB onCreate")
        val binding = ActivityBBinding.inflate(layoutInflater)
        binding.handler = ClickHandler(baseContext)
        setContentView(binding.root)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "ActivityB onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "ActivityB onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "ActivityB onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "ActivityB onPause")
    }


    override fun onStop() {
        super.onStop()
        Log.d(tag, "ActivityB onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "ActivityB onDestroy")
    }
}