package com.yi.jetpackDemo.workManager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.work.*
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.databinding.FragmentWorkManagerBinding
import java.lang.ref.SoftReference
import java.util.concurrent.TimeUnit

class WorkManagerFragment : Fragment() {

    lateinit var mBinding: FragmentWorkManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_work_manager,
                container,
                false
            )
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.handler = ClickHandler(SoftReference(this))
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}