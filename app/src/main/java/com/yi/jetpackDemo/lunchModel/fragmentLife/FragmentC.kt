package com.yi.jetpackDemo.lunchModel.fragmentLife

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.lunchModel.TAG_LAUNCH_MODEL
import kotlinx.android.synthetic.main.fragment_c.*

class FragmentC : Fragment() {

    companion object {
        const val key_title = "keyTitle"
        fun getInstance(title: String): FragmentC {
            val fragment = FragmentC()
            val args = Bundle()
            args.putString(key_title, title)
            fragment.arguments = args
            return fragment
        }
    }

    private var mTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "FragmentC onCreate")
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)
        mTitle = arguments?.getString(key_title)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG_LAUNCH_MODEL, "FragmentC onCreateView")
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = mTitle ?: "FragmentC"
    }
}