package com.yi.jetpackDemo

import android.app.Application
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        initStetho()
    }

    //初始化Stetho调试工具
    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(applicationContext);
            Stetho.initialize(
                Stetho.newInitializerBuilder(applicationContext)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(applicationContext))
                    .enableWebKitInspector(
                        Stetho.defaultInspectorModulesProvider(applicationContext)
                    )
                    .build()
            )
        }
    }

}