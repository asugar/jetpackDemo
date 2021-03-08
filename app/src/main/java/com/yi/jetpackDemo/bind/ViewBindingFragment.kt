package com.yi.jetpackDemo.bind

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.databinding.FragmentViewBindingBinding


/**
 * 简单视图绑定，通过再build.gradle 中添加
 * viewBinding {
 *   enabled = true
 *  }
 *  优势：更快的编译速度；易于使用
 *  缺陷：不支持布局变量或者布局表达式；不支持双向数据绑定
 *  原理？
 */
class ViewBindingFragment : Fragment() {

    private var binding: FragmentViewBindingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewBindingBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.tvName?.text = "hello world"
        binding?.tvAge?.text = "2020"
        binding?.btnNext?.setOnClickListener {
//            showSnackBar("this is submit")
            findNavController().navigate(R.id.toDataBindingFragment)
        }

        binding?.btnBack?.setOnClickListener {
//            showSnackBar("this is submit")
            findNavController().popBackStack(R.id.MainFragment, false)
//            findNavController().navigate(R.id.toMainFragment,null,NavOptions.Builder().setPopUpTo())
        }
    }

    private fun showSnackBar(msg: String) {
        val snackbar = binding?.cl?.let { Snackbar.make(it, msg, Snackbar.LENGTH_SHORT) }
//        snackbar.setCallback()
        snackbar?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}