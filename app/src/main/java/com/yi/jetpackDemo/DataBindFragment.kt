package com.yi.jetpackDemo

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yi.jetpackDemo.databinding.FragmentDataBindBinding
import com.yi.jetpackDemo.viewModels.MyHandlers
import com.yi.jetpackDemo.viewModels.User
import com.yi.jetpackDemo.viewModels.User2

/**
 * 数据绑定库使用
 * 1.需要添加build.gradle :
 * dataBinding {
 *  enabled = true
 *  }
 * 2.可观察字段：ObservableField  ObservableParcelable
 * 3.可观察集合：ObservableArrayMap ObservableArrayList
 * 4.可观察对象：BaseObservable
 */
class DataBindFragment : Fragment() {

    // 可观察字段

    private val mUser by lazy {
        User(ObservableField("hello"), ObservableField("world"))
    }
    private val mUser2 = User2()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val listItemBinding = ListItemBinding.inflate(layoutInflater, viewGroup, false)
        // or
        val binding: FragmentDataBindBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_data_bind, container, false)

        binding.user = mUser
        binding.index = 0
        binding.key = "1"
        binding.list = arrayListOf("0", "1", "2")
        binding.map = mapOf("0" to "hello", "1" to "world", "2" to "beijing")
        val apareArray: SparseArray<String> = SparseArray(3)
        apareArray.put(0, "123")
        apareArray.put(1, "nihao")
        apareArray.put(2, "beijing")
        binding.sparse = apareArray

        binding.handlers = MyHandlers(mUser)

        mUser2.name = "beijing"
        mUser2.address = "changping"
        mUser2.age = 10
        binding.user2 = mUser2

        return binding.root
//        return inflater.inflate(R.layout.fragment_data_bind, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}