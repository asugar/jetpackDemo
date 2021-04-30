package com.yi.jetpackDemo.service

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.databinding.FragmentServiceBinding

/**
 * 服务主页面
 * @author wanghuayi
 * @version
 * @since 2021/4/25
 */
const val SERVICE_TAG = "ServiceActivity"

class ServiceFragment : Fragment(), ClientInterface {

    private var binding: FragmentServiceBinding? = null
    private val isIntentServce = true
    private var mServiceType: ServiceType? = ServiceType.AIDL

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServiceBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnStartService?.setOnClickListener {
            context?.let {
                if (isIntentServce) {
                    it.startService(Intent(it, MyService2::class.java))
                } else {
                    it.startService(Intent(it, MyService::class.java))
                }
            }
        }

        binding?.btnStopService?.setOnClickListener {
            context?.let {
                if (isIntentServce) {
                    it.stopService(Intent(it, MyService2::class.java))
                } else {
                    it.stopService(Intent(it, MyService::class.java))
                }
            }
        }

        binding?.btnBSBinder?.setOnClickListener {
            context?.let {
                it.bindService(Intent(it, MyService::class.java), mConnection, BIND_AUTO_CREATE)
            }
        }
        binding?.btnUBSBinder?.setOnClickListener {
            context?.let {
                if (mBinder == null) {
                    Toast.makeText(it, "please bind first", Toast.LENGTH_SHORT).show()
                }
                it.unbindService(mConnection)
            }
        }

//        binding?.btnBSMessenger?.setOnClickListener {
//            context?.let {
//                it.bindService()
//            }
//        }
    }

    private var mBinder: MyService.MyBinder? = null
    private var mServer: ServerInterface? = null
    private var mServiceMessenger: Messenger? = null
    private val mClientHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            Logger.t(SERVICE_TAG).d("ServiceFragment handleMessage ${msg.what}")
        }
    }
    private val mClientMessenger = Messenger(mClientHandler)
    private var mRemoteService: IRemoteService? = null
    private val mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Logger.t(SERVICE_TAG).d("bindService onServiceDisconnected name= $name")
            mBinder = null
            mServer = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Logger.t(SERVICE_TAG).d("bindService onServiceConnected name= $name")
            when (mServiceType) {
                ServiceType.BINDER -> {
                    // 服务端引用
                    mBinder = (service as? MyService.MyBinder)
                    mBinder?.hi("i am server")
                    mBinder?.registerClient(this@ServiceFragment)
                    mServer = mBinder?.getServer()
                }
                ServiceType.MESSENGER -> {
                    mServiceMessenger = Messenger(service)
                    Logger.t(SERVICE_TAG).d("bindService MESSENGER $mServiceMessenger")
                    val msg: Message = Message.obtain(null, 999, 0, 0)
                    try {
                        msg.replyTo = mClientMessenger
                        mServiceMessenger?.send(msg)
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
                ServiceType.AIDL -> {
                    mRemoteService = IRemoteService.Stub.asInterface(service)
                    mRemoteService?.basicTypes(1, 1L, true, 1.0f, 1.toDouble(), "1")
                    mRemoteService?.registerClient(mClient)
                }
            }
        }

    }

    private val mClient: IClient = object : IClient.Stub() {
        override fun sayHello(msg: String?) {
            Logger.t(SERVICE_TAG).d("bindservice mClient sayHello $msg")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}