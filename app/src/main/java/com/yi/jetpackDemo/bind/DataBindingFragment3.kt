package com.yi.jetpackDemo.bind

import android.os.Bundle
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

    // viewModule+liveData
    private val userModule: UserViewModel by viewModels()
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



        binding.handlers = MyHandlers()
        userModule.name.value = "hello"
        userModule.age.value = 100
        binding.user = userModule


        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            binding.tvName.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        userModule.name.observe(viewLifecycleOwner, nameObserver)

        binding.tvName.setOnClickListener {
            if (binding.tvName.text.equals("hello")) {
                userModule.name.value = "world"
            } else {
                userModule.name.value = "hello"
            }

        }
        binding.lifecycleOwner = this
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