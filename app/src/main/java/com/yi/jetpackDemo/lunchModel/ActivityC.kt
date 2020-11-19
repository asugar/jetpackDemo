package com.yi.jetpackDemo.lunchModel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.yi.jetpackDemo.databinding.ActivityCBinding

class ActivityC : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "ActivityC onCreate")
        val binding = ActivityCBinding.inflate(layoutInflater)
        binding.handler = ClickHandler(baseContext)
        setContentView(binding.root)
    }
}