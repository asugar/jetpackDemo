package com.yi.jetpackDemo

import android.app.Application
import android.os.Process
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.yi.jetpackDemo.retrofit.manager.*
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import kotlin.system.exitProcess

class MyApplication : Application() {

    private val tag = "MyApplication"

    companion object {
        const val APP_HOST = "https://schoolmaster.aixuexi.com/godfather/"
        const val TRAIN_HOST = "http://train.aixuexi.com/"
        private lateinit var mApplication: Application
        fun getApplication(): Application {
            return mApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        mApplication = this
        MultiDex.install(this)
        initStetho()
        initLogger()
        initRetrofit()
        Logger.t(tag).d("onCreate ")
    }

    private fun initRetrofit() {
        val cache = Cache(
            File(getApplication().cacheDir, "responses"), 50 * 1024 * 1024
        )
        RetrofitManager.initRetrofit(
            APP_HOST,
            cache = cache,
            loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                Logger.t("http").d(message)
            }).setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE),
            cacheInterceptor = CacheInterceptor(),
            headerInterceptor = object : HeaderInterceptor() {
                override fun headers(): Map<String, Any>? {
                    val map = HashMap<String, Any>()
                    map["ptaxxxzapp"] =
                        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTk1MDgyMDk0MjI3Mzc0ODIiLCJidXNpbmVzc1BsYXRmb3JtIjoiYWl4dWV4aSIsImlzcyI6InBhc3Nwb3J0U2VydmljZSIsImp3dF9yZWZfdG9rZW5fZXhwaXJlIjoxNjEzMDEyMDcyNzg4LCJleHAiOjE2MTMwMTIwNzIsImlhdCI6MTYxMjkyNTY3MiwibG9naW5TeXN0ZW0iOiJwdGF4eHh6YXBwIiwianRpIjoiYzU5ZDdhMWYwNTU5NGI5Njk4N2M1MDU2OThhNTFjMDYiLCJzSWQiOiI4ZTYzZDY4MjYyYmE0MTgzYjBiYTAzNzgzYTQwMWUxYyJ9.3MkdaheqMEXawvtMmhB2XFLkyZqAcNocPCu4uJ1utKI"
                    map["userId"] = "2416522"
                    return map
                }
            },
            domainInterceptor = MultBaseUrlInterceptor(),
            performanceInterceptor = object : PerformanceLogInterceptor() {
                override fun getKeyId() = "APP"

                override fun getSecretKey() = "hdfiosafjisoadjfpsa23782kdfmds"
            },
            networkInterceptors = * arrayOf()//StethoInterceptor(),
        )
        RetrofitManager.setHosts(TRAIN_HOST)
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