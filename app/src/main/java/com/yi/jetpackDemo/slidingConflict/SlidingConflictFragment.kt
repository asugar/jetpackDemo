package com.yi.jetpackDemo.slidingConflict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yi.jetpackDemo.databinding.FragmentSlidingConflictBinding
import com.yi.jetpackDemo.slidingConflict.adapter.SlidingConflictAdapter
import com.yi.jetpackDemo.slidingConflict.bean.getTestDatas

const val SCTag = "scTag"

/**
 * 测试滑动冲突问题
 */
class SlidingConflictFragment : Fragment() {

    private var binding: FragmentSlidingConflictBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSlidingConflictBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SlidingConflictAdapter(this.getTestDatas())
        binding?.rvVertical?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}