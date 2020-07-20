package com.yi.jetpackDemo.bind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.bind.viewModels.MyHandlers
import com.yi.jetpackDemo.bind.viewModels.ObservableUser
import com.yi.jetpackDemo.databinding.FragmentDataBinding2Binding

/**
 * 数据绑定库使用
 * 可观察字段/集合/对象
 */
class DataBindingFragment2 : Fragment() {

    private val mUser by lazy {
        ObservableUser(ObservableField("欧阳"), ObservableLong(10001))
    }

    private var mScores = ObservableArrayMap<String, String>().apply {
        put("综合", "230")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDataBinding2Binding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_data_binding2,
                container,
                false
            )

        binding.user = mUser
        mUser.name = "小明"
        mUser.address = "北京市昌平区"
        mUser.age = 101

        binding.handlers = MyHandlers()
        binding.scores = mScores

        // test map https://developer.android.google.cn/topic/libraries/data-binding/binding-adapters
//        mScores.addOnMapChangedCallback(object : ObservableMap.OnMapChangedCallback{
//
//        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btLast).setOnClickListener {
            findNavController().navigate(R.id.toDataBindingFragment)
        }

        view.findViewById<Button>(R.id.btNext).setOnClickListener {
            findNavController().navigate(R.id.toDataBindingFragment3)
        }
    }
}