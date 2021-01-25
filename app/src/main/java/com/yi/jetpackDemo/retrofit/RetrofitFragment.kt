package com.yi.jetpackDemo.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.MyApplication
import com.yi.jetpackDemo.databinding.FragmentRetrofitBinding
import com.yi.jetpackDemo.retrofit.entity.CommonFieldsLog
import com.yi.jetpackDemo.retrofit.entity.EventListItem
import com.yi.jetpackDemo.retrofit.entity.PerformanceLog
import com.yi.jetpackDemo.retrofit.gson.H5ReqTypeAdapter
import com.yi.jetpackDemo.retrofit.gson.H5RequestBody
import com.yi.jetpackDemo.retrofit.manager.ResultFunc
import com.yi.jetpackDemo.retrofit.manager.RetrofitManager
import com.yi.jetpackDemo.retrofit.manager.async
import com.yi.jetpackDemo.retrofit.manager.exception.ResultException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.nio.charset.Charset
import java.security.MessageDigest

const val RETROFIT_TAG = "RetrofitFragment"

class RetrofitFragment : Fragment() {

    private var binding: FragmentRetrofitBinding? = null
    private val mService: RetrofitService by lazy {
        RetrofitManager.getRetrofit().create(RetrofitService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRetrofitBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding?.btnNet?.setOnClickListener {
            mService.getAppIndexData("603", true)
                .map(ResultFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Logger.t(RETROFIT_TAG).d("onNext")
                        showNetContent(it.toString())
                    }, {
                        if (it is ResultException) {
                            Logger.t(RETROFIT_TAG).d("onError ${it.getErrorCode()} ${it.message}")
                        } else {
                            Logger.t(RETROFIT_TAG).d("onError ${it.message} ")
                        }
                        showNetContent(it.toString())
                    }, {
                        Logger.t(RETROFIT_TAG).d("onComplete")
                    }
                )
        }

        binding?.btnSwitchHost?.setOnClickListener {
            mService.getAppWithHosts()
                .map(ResultFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Logger.t(RETROFIT_TAG).d("multi host onNext")
                        showNetContent(it.toString())
                    }, {
                        if (it is ResultException) {
                            Logger.t(RETROFIT_TAG)
                                .d("multi host onError ${it.getErrorCode()} ${it.message}")
                        } else {
                            Logger.t(RETROFIT_TAG).d("multi host onError ${it.message} ")
                        }
                        showNetContent(it.toString())
                    }, {
                        Logger.t(RETROFIT_TAG).d("multi host onComplete")
                    }
                )
        }

        binding?.btnWithUrl?.setOnClickListener {
            mService.getAppWithUrl(
                "${MyApplication.TRAIN_HOST}thewolverine/trainCalendar/getMonthListForApp",
                "603"
            )
                .map(ResultFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Logger.t(RETROFIT_TAG).d("with rul onNext")
                        showNetContent(it.toString())
                    }, {
                        if (it is ResultException) {
                            Logger.t(RETROFIT_TAG)
                                .d("with rul onError ${it.getErrorCode()} ${it.message}")
                        } else {
                            Logger.t(RETROFIT_TAG).d("with rul onError ${it.message} ")
                        }
                        showNetContent(it.toString())
                    }, {
                        Logger.t(RETROFIT_TAG).d("with rul onComplete")
                    }
                )
        }

        binding?.btnRefreshH5Get?.setOnClickListener {
            refreshH5Get()
        }

        binding?.btnRefreshH5Post?.setOnClickListener {
            refreshH5Post()
        }

        binding?.btnPerformanceLog?.setOnClickListener {
            uploadLog()
        }

    }

    /**
     * 测试日志上传
     *
     */
    private fun uploadLog() {
        val url = "https://api-idata.aixuexi.com/collectWeb/app_log/save"
        val body = PerformanceLog()
        body.commonFields =
            CommonFieldsLog("uiduiduid", "deviceIddeviceIddeviceId", "projectIdprojectIdprojectId")
        body.eventList = listOf(EventListItem("2731923123131", 12, "fdasjdklfa;.dfkass;fa;ls"))
        val json = Gson().toJson(body)
        val md5 =
            MessageDigest.getInstance("MD5").digest(json.toByteArray(Charset.defaultCharset()))
        Logger.t(RETROFIT_TAG).d("uploadLog json= $json md5= ${md5.toString()}")

        val dispose = mService.uploadLogPost(url, body)
            .async()
            .subscribe({
                Logger.t(RETROFIT_TAG).d("uploadLog onNext")
                showNetContent(it.toString())
            }, {
                if (it is ResultException) {
                    Logger.t(RETROFIT_TAG)
                        .d("uploadLog onError ${it.getErrorCode()} ${it.message}")
                } else {
                    Logger.t(RETROFIT_TAG).d("uploadLog onError ${it.message} ")
                }
                showNetContent(it.toString())
            }, {
                Logger.t(RETROFIT_TAG).d("uploadLog onComplete")
            })
    }

    /**
     *  {"method":"get",
     *  "param":{"assistantId":"","teacherId":"72475","pageSize":10,"startTime":"2020-10-04","endTime":"2021-01-12","userId":"1537885","pageNum":1},
     *  "url":"https://mall.aixuexi.com/vampire/app/mallOrder/list"}
     */
    private fun refreshH5Get() {
        val url = "https://mall.aixuexi.com/vampire/app/mallOrder/list"
        val map = HashMap<String, String>()
        map["assistantId"] = ""
        map["teacherId"] = "72475"
        map["pageSize"] = "20"
        map["startTime"] = "2020-10-04"
        map["endTime"] = "2021-01-12"
        map["userId"] = "1537885"
        map["pageNum"] = "1"
        val dispose = mService.refreshH5Get(url, map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Logger.t(RETROFIT_TAG).d("refreshH5Get onNext")
                    showNetContent(it.toString())
                }, {
                    if (it is ResultException) {
                        Logger.t(RETROFIT_TAG)
                            .d("refreshH5Get onError ${it.getErrorCode()} ${it.message}")
                    } else {
                        Logger.t(RETROFIT_TAG).d("refreshH5Get onError ${it.message} ")
                    }
                    showNetContent(it.toString())
                }, {
                    Logger.t(RETROFIT_TAG).d("refreshH5Get onComplete")
                }
            )
    }

    /**
     *  {"method":"post",
     *  "param":{"assistantId":"","teacherId":"72475","roleId":"","pageSize":10,"userId":"1537885","pageNum":1,"content":""},
     *  "url":"https://schoolmaster.aixuexi.com/godfather/app/xyPlan/findPrincipalAssistantList"}
     */
    private fun refreshH5Post() {
        val jsonStr =
            "{\"method\":\"post\",\"param\":{\"assistantId\":\"\",\"teacherId\":\"72475\",\"roleId\":\"\",\"pageSize\":20,\"userId\":\"1537885\",\"pageNum\":1,\"content\":\"\"},\"url\":\"https://schoolmaster.aixuexi.com/godfather/app/xyPlan/findPrincipalAssistantList\"}"
        val url = "https://schoolmaster.aixuexi.com/godfather/app/xyPlan/findPrincipalAssistantList"
        val body = getGson().fromJson<H5RequestBody>(jsonStr, H5RequestBody::class.java)
        val dispose = mService.refreshH5Post(url, body.param!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Logger.t(RETROFIT_TAG).d("refreshH5Post onNext")
                    showNetContent(it.toString())
                }, {
                    if (it is ResultException) {
                        Logger.t(RETROFIT_TAG)
                            .d("refreshH5Post onError ${it.getErrorCode()} ${it.message}")
                    } else {
                        Logger.t(RETROFIT_TAG).d("refreshH5Post onError ${it.message} ")
                    }
                    showNetContent(it.toString())
                }, {
                    Logger.t(RETROFIT_TAG).d("refreshH5Post onComplete")
                }
            )
    }

    private fun getGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(H5RequestBody::class.java, H5ReqTypeAdapter())
        return gsonBuilder.create()
    }

    private fun showNetContent(msg: String) {
        binding?.tvNetContent?.text = msg
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}