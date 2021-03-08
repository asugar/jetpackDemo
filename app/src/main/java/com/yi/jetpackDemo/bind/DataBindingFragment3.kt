package com.yi.jetpackDemo.bind

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.bind.adapter.Fragment3Adapter
import com.yi.jetpackDemo.bind.viewModels.MyHandlers
import com.yi.jetpackDemo.bind.viewModels.SharedViewModel
import com.yi.jetpackDemo.bind.viewModels.UserViewModel
import com.yi.jetpackDemo.databinding.FragmentDataBinding3Binding
import kotlinx.coroutines.*

/**
 * 数据绑定库使用
 * 双向绑定 and lifecycle的绑定
 */
class DataBindingFragment3 : Fragment() {

    // viewModule+liveData
    private val userModule: UserViewModel by viewModels()
    private val shareModel: SharedViewModel by activityViewModels()
    private val myAdapter = Fragment3Adapter(null)

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

        binding.share = shareModel

        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            binding.tvName.text = newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        userModule.name.observe(viewLifecycleOwner, nameObserver)

        binding.tvName.setOnClickListener {
            if (binding.tvName.text == "hello") {
                userModule.name.value = "world"
            } else {
                userModule.name.value = "hello"
            }

        }
        initRv(binding)
        getData { dt ->
            dt?.let {
                myAdapter.refreshData(it)
            }
        }
        binding.lifecycleOwner = this
        return binding.root
//        return inflater.inflate(R.layout.fragment_data_binding, container, false)
    }

    private fun initRv(binding: FragmentDataBinding3Binding) {
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter = myAdapter
    }

    /**
     * 模拟数据获取
     * 在子线程请求网络，在主线程刷新数据
     */
    private fun getData(cb: (data: List<String>?) -> Unit) = runBlocking {
        newSingleThreadContext("nst").use { nst ->
            runBlocking(nst) {
                Log.d(
                    "DataBindingFragment3",
                    "getData ${Thread.currentThread().name} ${checkMainThread()}"
                )
                val list = listOf("name1", "name2", "name3", "name4", "name5", "name6")
                GlobalScope.launch(Dispatchers.Main) {
                    Log.d(
                        "DataBindingFragment3",
                        "getData 2 ${Thread.currentThread().name} ${checkMainThread()}"
                    )
                    cb(list)
                }
            }
        }
    }

    private fun checkMainThread(): Boolean {
        return Thread.currentThread() === Looper.getMainLooper().thread
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btLast).setOnClickListener {
            findNavController().navigate(R.id.toDataBindingFragment2)
        }

        view.findViewById<Button>(R.id.btNext).setOnClickListener {
//            findNavController().navigate(R.id.toMainFragment)
            // 将mainFragment之前的fragment清栈
            findNavController().popBackStack(R.id.MainFragment, false)
        }
    }
}