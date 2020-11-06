package com.yi.jetpackDemo

import android.app.Application
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class MyApplication : Application() {

    private val tag = "MyApplication"

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        initStetho()
        initLogger()
        Logger.t(tag).d("onCreate ")
    }

    /**
     * 初始化Logger
     */
    private fun initLogger() {
        // 初始化日志工具
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(7)
            .tag("jetpack")
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

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