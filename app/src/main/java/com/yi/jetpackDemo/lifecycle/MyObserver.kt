package com.yi.jetpackDemo.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.orhanobut.logger.Logger

/**
 * 使MyObserver跟随Activity/Fragment的生命周期走，进行更加细粒化处理
 */
class MyObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Logger.t(LIFECYCLE_TAG).d("MyObserver ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Logger.t(LIFECYCLE_TAG).d("MyObserver ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Logger.t(LIFECYCLE_TAG).d("MyObserver ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Logger.t(LIFECYCLE_TAG).d("MyObserver ON_STOP")
    }


}