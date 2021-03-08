package com.yi.jetpackDemo.rxjava

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.databinding.FragmentRxjavaBinding
import com.yi.jetpackDemo.rxjava.bean.ModuleBean
import com.yi.jetpackDemo.rxjava.bean.ModuleResBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxJavaFragment : Fragment() {

    private val TAG = "RxJavaFragment"

    private var binding: FragmentRxjavaBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRxjavaBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tvTitle?.setOnClickListener {
//            testSwitchThread()
            testArray()
        }
    }

    /**
     * 模拟学生端更新网络资源
     * 业务流程：请求资源接口 -> 判断更新apk还是资源 -> 弹出更新apkDialog（取消：停止；确定） --> 下载apk包（下载失败：弹重新下载dialog；下载成功） --> 进行安装
     *                                        -> +更新资源(判断版本号是否需要更新) -> 去下载资源包 -> 解压资源包 -> 更新资源版本
     * 可以使用rxjava的concatMap拼接
     * 将apk更新和res更新放在两个不同的Observer
     */
    private fun testArray() {
        val list = getResTestData()?.modules
        val dispose = Observable.fromIterable(list)
//            .concatMap { Observable.just(it.description) }
//            .concatMap { value ->
//                Observable.create<String> {
//                    it.onNext(value.plus("-1"))
//                    it.onComplete()
//                }
//            }
            .filter {
                it.version == "2.4.567"
            }
            .concatMap { value ->
                Observable.create<String> {
//                    if (value.packageTag != "rn") {
                    it.onNext(value.packageTag.plus("-1"))
//                    }
                    it.onComplete()
                }
            }
//            .concatMap { value ->
//                Observable.create<String> {
//                    it.onNext(value.plus("-2"))
//                    it.onComplete()
//                }
//            }
//            .concatMap { value ->
//                Observable.create<String> {
//                    it.onNext(value.plus("-3"))
//                    it.onComplete()
//                }
//            }
            .async()
            .subscribe({
                Logger.t(TAG).d("onNext $it ${Thread.currentThread().name}")
            }, {
                Logger.t(TAG).d("onError ${it.message} ${Thread.currentThread().name}")
            }, {
                Logger.t(TAG).d("onComplete ${Thread.currentThread().name}")
            })
    }

    private fun testSwitchThread() {
        val dispose = Observable.create<Int> {
            Logger.t(TAG).d("testSwitchThread start ${Thread.currentThread().name}")
            it.onNext(0)
            it.onComplete()
        }
            .concatMap { value ->
                Logger.t(TAG).d("testSwitchThread start $value ${Thread.currentThread().name}")
                Observable.create<Int> {
                    Thread.sleep(2000)
                    it.onNext(value + 1)
                    it.onComplete()
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .concatMap { value ->
                Logger.t(TAG).d("after observeOn $value ${Thread.currentThread().name}")
                // 可以在这里执行UI操作
                Observable.create<Int> {
                    it.onNext(value + 1)
                    it.onComplete()
                }
            }
            .observeOn(Schedulers.io())
            .concatMap { value ->
                Logger.t(TAG).d("switch to io observeOn $value ${Thread.currentThread().name}")
                Observable.create<Int> {
                    it.onNext(value + 1)
                    it.onComplete()
                }
            }
            .async()
            .subscribe({
                Logger.t(TAG).d("onNext $it ${Thread.currentThread().name}")
            }, {
                Logger.t(TAG).d("onError ${it.message} ${Thread.currentThread().name}")
            }, {
                Logger.t(TAG).d("onComplete ${Thread.currentThread().name}")
            })
    }

    /**
     * 模拟数据获取
     */
    private fun getResTestData(): ModuleResBean? {
        val moduleResBean = ModuleResBean()
        val modules = ArrayList<ModuleBean>()
        modules.add(
            ModuleBean(
                "axx",
                "https://aixuexi-test.oss-cn-beijing.aliyuncs.com/B:1013:K/1609084800/feeb3d3716f046f5a1183c3aa9bbe2e6.zip",
                "2.8.4",
                "123456",
                1,
                3,
                2
            )
        )
        modules.add(
            ModuleBean(
                "tol",
                "https://aixuexi-test.oss-cn-beijing.aliyuncs.com/B:1013:K/1608825600/ff7621149d1e4097ae0bfd7f17a794b2.zip",
                "2.4.1",
                "学生端h5拆包使用，不要删除，可以下线",
                0,
                3,
                2
            )
        )
        modules.add(
            ModuleBean(
                "rn",
                "https://aixuexi-test.oss-cn-beijing.aliyuncs.com/B:1013:K/1608825600/698a423459204bd09749582c388cdac5.zip",
                "2.2.1",
                "测试h5拆包下载，不要删模块，可以下线",
                0,
                5,
                2
            )
        )
        moduleResBean.appId = 2
        moduleResBean.platformType = 1
        moduleResBean.modules = modules
        return moduleResBean
    }

    private fun <T> Observable<T>.async(): Observable<T> = subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()
    )

    private val mGson by lazy {
        Gson()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}