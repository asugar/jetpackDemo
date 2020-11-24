package com.yi.jetpackDemo.lunchModel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yi.jetpackDemo.databinding.ActivityC2Binding


class ActivityC2 : AppCompatActivity() {

    lateinit var mBinding: ActivityC2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityC2Binding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

}