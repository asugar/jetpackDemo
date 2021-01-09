package com.yi.jetpackDemo.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.databinding.FragmentRetrofitBinding
import com.yi.jetpackDemo.retrofit.manager.ResultFunc
import com.yi.jetpackDemo.retrofit.manager.RetrofitManager
import com.yi.jetpackDemo.retrofit.manager.exception.ResultException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitFragment : Fragment() {

    private val TAG = "RetrofitFragment"

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
            mService.getAppIndexData("603")
                .map(ResultFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Logger.t(TAG).d("onNext")
                        showNetContent(it.toString())
                    }, {
                        if (it is ResultException) {
                            Logger.t(TAG).d("onError ${it.getErrorCode()} ${it.message}")
                        } else {
                            Logger.t(TAG).d("onError ${it.message} ")
                        }
                        showNetContent(it.toString())
                    }, {
                        Logger.t(TAG).d("onComplete")
                    }
                )
        }
    }

    private fun showNetContent(msg: String) {
        binding?.tvNetContent?.text = msg
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}