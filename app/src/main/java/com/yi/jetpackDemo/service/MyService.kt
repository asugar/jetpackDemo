package com.yi.jetpackDemo.service

import android.app.Service
import android.content.Intent
import android.content.res.Configuration
import android.os.*
import com.orhanobut.logger.Logger

/**
 * 普通service
 * @author wanghuayi
 * @version
 * @since 2021/4/26
 */
class MyService : Service(), ServerInterface {

    private var mServiceType: ServiceType? = ServiceType.AIDL

    override fun onBind(intent: Intent?): IBinder? {
        Logger.t(SERVICE_TAG)
            .d("MyService onBind mServiceType= ${mServiceType?.type} ${hashCode()}")
        return when (mServiceType) {
            ServiceType.BINDER -> {
                MyBinder()
            }
            ServiceType.MESSENGER -> {
                mMessenger.binder
            }
            ServiceType.AIDL -> {
                mBinder
            }
            else -> {
                MyBinder()
            }
        }
    }

    override fun onRebind(intent: Intent?) {
        Logger.t(SERVICE_TAG).d("MyService onRebind ")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Logger.t(SERVICE_TAG).d("MyService onUnbind ")
        return true
    }

    /**
     * 方式三 aidl
     */
    private val mBinder = object : IRemoteService.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {
            Logger.t(SERVICE_TAG).d("MyService aidl basicTypes ")
        }

        override fun registerClient(client: IClient?) {
            Logger.t(SERVICE_TAG).d("MyService aidl registerClient ")
            client?.sayHello("hello i am from service")
        }

        override fun saveUser(user: User?) {
            Logger.t(SERVICE_TAG).d("MyService aidl saveUser ${user?.name}")
        }

        override fun saveUser2(bundle: Bundle?) {
            bundle?.classLoader = classLoader
            val user = bundle?.getParcelable<User>("user")
            Logger.t(SERVICE_TAG).d("MyService aidl saveUser2 ${user?.name}")
        }
    }

    /**
     * 方式二 messenger
     * 利用replyTo实现双向通信
     */
    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            Logger.t(SERVICE_TAG).d("MyService handleMessage ${msg.what}")
            when (msg.what) {
                999 -> {
                    msg.replyTo.send(Message.obtain(null, 999, 0, 0))
                }
                else -> {

                }
            }
        }
    }
    private val mMessenger = Messenger(mHandler)


    private var mClient: ClientInterface? = null

    /**
     * 方式一，扩展Binder
     */
    inner class MyBinder : Binder() {
        fun hi(msg: String) {
            Logger.t(SERVICE_TAG).d("MyService myBinder hi $msg")
        }

        fun getServer(): ServerInterface {
            Logger.t(SERVICE_TAG).d("MyService getServer ")
            return this@MyService
        }

        fun registerClient(client: ClientInterface) {
            Logger.t(SERVICE_TAG).d("MyService registerClient ")
            mClient = client
        }
    }


    override fun onCreate() {
        Logger.t(SERVICE_TAG).d("MyService onCreate ${hashCode()}")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logger.t(SERVICE_TAG).d("MyService onStartCommand ${hashCode()}")
        // 再次startActivity的时候onCreate不会执行，但是onStartCommand会执行
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Logger.t(SERVICE_TAG).d("MyService onDestroy ${hashCode()}")
    }

    override fun onLowMemory() {
        Logger.t(SERVICE_TAG).d("MyService onLowMemory ${hashCode()}")
    }

    override fun onTrimMemory(level: Int) {
        Logger.t(SERVICE_TAG).d("MyService onTrimMemory level= $level ${hashCode()}")
        super.onTrimMemory(level)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Logger.t(SERVICE_TAG).d("MyService onConfigurationChanged ${hashCode()}")
    }

}