package com.yi.jetpackDemo

import android.app.Application
import android.os.Process
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import kotlin.system.exitProcess

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

    override fun onLowMemory() {
        super.onLowMemory()
        try {
            Logger.t(tag).d("onLowMemory invoked")
        } catch (e: Exception) {
            Logger.t(tag).e("onLowMemory ${e.message}")
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        try {
            Logger.t(tag).d("onLowMemory invoked level= $level")
            if (level >= TRIM_MEMORY_MODERATE) { //内存不足，并且该进程在后台进程列表的中部
                //主动杀进程，防止内存回收后，打开应用加载静态变量时为null
                Logger.t(tag).d("onLowMemory invoked kill self")
                Process.killProcess(Process.myPid())
                exitProcess(0)
                System.gc()
            }
        } catch (e: Exception) {
            Logger.t(tag).e("onTrimMemory ${e.message}")
        }
    }
}