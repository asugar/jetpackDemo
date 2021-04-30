package com.yi.jetpackDemo.service

import android.app.IntentService
import android.content.Intent
import com.orhanobut.logger.Logger

/**
 * 使用IntentService
 * @author wanghuayi
 * @version
 * @since 2021/4/26
 */
class MyService2 : IntentService("myService2") {

    override fun onHandleIntent(intent: Intent?) {
        Logger.t(SERVICE_TAG).d("MyService2 onHandleIntent ${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.t(SERVICE_TAG).d("MyService2 onDestroy ${hashCode()}")
    }

}