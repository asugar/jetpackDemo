package com.yi.jetpackDemo.lifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yi.jetpackDemo.databinding.FragmentLifecycleBinding

const val LIFECYCLE_TAG = "LifeCycleFragment"

class LifeCycleFragment : Fragment() {

    private var binding: FragmentLifecycleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLifecycleBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(MyObserver())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}