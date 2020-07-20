package com.yi.jetpackDemo.bind

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.bind.viewModels.CommonUser
import com.yi.jetpackDemo.bind.viewModels.MyHandlers
import com.yi.jetpackDemo.databinding.FragmentDataBindingBinding

/**
 * 布局与绑定表达式
 */
class DataBindingFragment : Fragment() {

    private val mUser by lazy {
        CommonUser("欧阳", "玉峰")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDataBindingBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_data_binding,
                container,
                false
            )

        binding.user = mUser

        binding.index = 0
        binding.key = "1"
        binding.list = arrayListOf("0", "1", "2")
        binding.map = mapOf("0" to "hello", "1" to "world", "2" to "beijing")
        val spareArray: SparseArray<String> = SparseArray(3)
        spareArray.put(0, "123")
        spareArray.put(1, "nihao")
        spareArray.put(2, "beijing")
        binding.sparse = spareArray

        binding.handlers = MyHandlers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btLast).setOnClickListener {
            findNavController().navigate(R.id.toViewBindingFragment)
        }

        view.findViewById<Button>(R.id.btNext).setOnClickListener {
            findNavController().navigate(R.id.toDataBindingFragment2)
        }
    }
}