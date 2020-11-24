package com.yi.jetpackDemo.lunchModel.fragmentLife

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.lunchModel.TAG_LAUNCH_MODEL
import kotlinx.android.synthetic.main.fragment_c.*

class FragmentC2 : Fragment() {

    companion object {
        const val key_title = "keyTitle"
        fun getInstance(title: String): FragmentC2 {
            val fragment = FragmentC2()
            val args = Bundle()
            args.putString(key_title, title)
            fragment.arguments = args
            return fragment
        }
    }

    private var mTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTitle = arguments?.getString(FragmentC.key_title)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = androidx.transition.TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.shared_image)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG_LAUNCH_MODEL, "FragmentC2 onCreateView")
        return inflater.inflate(R.layout.fragment_c_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = mTitle ?: "FragmentC2"
    }
}