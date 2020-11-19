package com.yi.jetpackDemo.lunchModel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.yi.jetpackDemo.databinding.ActivityBBinding

class ActivityB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onCreate")
        val binding = ActivityBBinding.inflate(layoutInflater)
        binding.handler = ClickHandler(baseContext)
        setContentView(binding.root)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onPause")
    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onDestroy")
    }
}