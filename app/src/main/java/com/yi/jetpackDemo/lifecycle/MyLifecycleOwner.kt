package com.yi.jetpackDemo.lifecycle

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * 生命周器感知只能用来感知对应activity，fragment生命周期的回调
 */
class MyLifecycleOwner : Activity(), LifecycleOwner {

    private var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycleRegistry.markState(Lifecycle.State.CREATED)
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }


    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}