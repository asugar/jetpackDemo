package com.yi.jetpackDemo.easyPermisions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yi.jetpackDemo.databinding.FragmentEasyPermisionsBinding

class EasyPermisionsFragment : Fragment() {

    private var binding: FragmentEasyPermisionsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEasyPermisionsBinding.inflate(layoutInflater)
        binding!!.lifecycleOwner = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

    }
}