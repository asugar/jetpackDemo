package com.yi.jetpackDemo.bind

import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.databinding.FragmentDataBinding3Binding
import com.yi.jetpackDemo.bind.viewModels.*

/**
 * 数据绑定库使用
 * 双向绑定 and lifecycle的绑定
 */
class DataBindingFragment3 : Fragment() {

    // liveData
    private val model: NameViewModel by viewModels()

    private val mUser by lazy {
        User(ObservableField("hello"), ObservableField("world"))
    }
    private val mUser2 = User2()

    private val shareModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val listItemBinding = ListItemBinding.inflate(layoutInflater, viewGroup, false)
        // or
        val binding: FragmentDataBinding3Binding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.fragment_data_binding3,
                container,
                false
            )

//        binding.user = mUser
        binding.index = 0
        binding.key = "1"
        binding.list = arrayListOf("0", "1", "2")
        binding.map = mapOf("0" to "hello", "1" to "world", "2" to "beijing")
        val apareArray: SparseArray<String> = SparseArray(3)
        apareArray.put(0, "123")
        apareArray.put(1, "nihao")
        apareArray.put(2, "beijing")
        binding.sparse = apareArray

        binding.handlers = MyHandlers()

        mUser2.name = "beijing"
        mUser2.address = "changping"
        mUser2.age = 10
//        binding.user2 = mUser2

        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            binding.tvCurrentName.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentName.observe(viewLifecycleOwner, nameObserver)

        binding.tvCurrentName.setOnClickListener {
            if (binding.tvCurrentName.text.equals("long long ago")) {
                model.currentName.value = "xiaoyi"
            } else {
                model.currentName.value = "long long ago"
            }

        }
        return binding.root
//        return inflater.inflate(R.layout.fragment_data_binding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.toViewBindingFragment)
        }
    }
}