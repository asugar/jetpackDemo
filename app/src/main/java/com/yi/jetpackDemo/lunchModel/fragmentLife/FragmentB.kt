package com.yi.jetpackDemo.lunchModel.fragmentLife

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.lunchModel.TAG_LAUNCH_MODEL
import kotlinx.android.synthetic.main.fragment_b.*

class FragmentB : Fragment() {

    companion object {
        const val key_title = "keyTitle"
        fun getInstance(title: String): FragmentB {
            val fragment = FragmentB()
            val args = Bundle()
            args.putString(key_title, title)
            fragment.arguments = args
            return fragment
        }
    }

    private var mTitle: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onAttach ${hashCode()}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onCreate ${hashCode()}")
        mTitle = arguments?.getString(FragmentA.key_title)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onCreateView ${hashCode()}")
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onViewCreated ${hashCode()}")
        tvTitle.text = mTitle ?: "FragmentB"
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onViewStateRestored ${hashCode()} $savedInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onStart ${hashCode()}")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.d(TAG_LAUNCH_MODEL, "FragmentB setUserVisibleHint ${hashCode()} $isVisibleToUser")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onHiddenChanged ${hashCode()} $hidden")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onResume ${hashCode()}")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onPause ${hashCode()}")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(key_title, tvTitle.text.toString())
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onSaveInstanceState ${hashCode()}")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onStop ${hashCode()}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onDestroyView ${hashCode()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onDestroy ${hashCode()}")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG_LAUNCH_MODEL, "FragmentB onDetach ${hashCode()}")
    }


}