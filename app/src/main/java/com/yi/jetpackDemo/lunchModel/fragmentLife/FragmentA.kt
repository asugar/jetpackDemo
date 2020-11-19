package com.yi.jetpackDemo.lunchModel.fragmentLife

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.lunchModel.OnActivityClickListener
import com.yi.jetpackDemo.lunchModel.TAG_LAUNCH_MODEL

class FragmentA : Fragment() {

    private var mListener: OnActivityClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onAttach")
        mListener = context as? OnActivityClickListener
        if (mListener == null) {
            throw ClassCastException("$context must implement OnActivityClickListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onCreateView")
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        activity// 获取activity的实例
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onStart")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onViewStateRestored $savedInstanceState")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onSaveInstanceState ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG_LAUNCH_MODEL, "FragmentA onDetach")
    }

}