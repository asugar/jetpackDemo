package com.yi.jetpackDemo.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.orhanobut.logger.Logger

class ProcessObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackground() {
        Logger.t(LIFECYCLE_TAG).d("onAppBackground")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onAppForeground() {
        Logger.t(LIFECYCLE_TAG).d("onAppForeground")
    }
}