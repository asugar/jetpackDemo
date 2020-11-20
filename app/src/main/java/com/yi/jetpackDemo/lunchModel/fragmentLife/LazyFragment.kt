package com.yi.jetpackDemo.lunchModel.fragmentLife

import androidx.fragment.app.Fragment

/**
 * https://zhuanlan.zhihu.com/p/134234521
 * viewPage模式下可以
 * add+show+hide 模式
 * 使用setMaxLifecycle限制最大生命周期
 * ViewPage2 本身支持懒加载
 */
abstract class LazyFragment : Fragment() {

    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()
}