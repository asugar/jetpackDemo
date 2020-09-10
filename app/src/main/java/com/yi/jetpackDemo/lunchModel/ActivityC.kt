package com.yi.jetpackDemo.lunchModel

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.yi.jetpackDemo.databinding.ActivityCBinding

class ActivityC : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "ActivityC onCreate")
        val binding = ActivityCBinding.inflate(layoutInflater)
        binding.handler = ClickHandler(baseContext)
        setContentView(binding.root)
    }
}