package com.yi.jetpackDemo.lunchModel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.databinding.ActivityCBinding
import com.yi.jetpackDemo.lunchModel.fragmentLife.FragmentC


class ActivityC : AppCompatActivity() {

    lateinit var mBinding: ActivityCBinding
    private var count: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "ActivityC onCreate")
        mBinding = ActivityCBinding.inflate(layoutInflater)
        mBinding.handler = ClickHandler(baseContext)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        ViewCompat.setTransitionName(mBinding.ivIcon, "shared_image")
        mBinding.ivIcon.setOnClickListener {
            val fragment = FragmentC.getInstance("fragmentC-${count++}")
            supportFragmentManager.beginTransaction()
//                .setCustomAnimations(
//                    R.anim.slide_in,  // enter
//                    R.anim.fade_out,  // exit
//                    R.anim.fade_in,   // popEnter
//                    R.anim.slide_out  // popExit
//                )
                .addSharedElement(mBinding.ivIcon, "shared_image")
                .replace(R.id.flContent, fragment)
                .addToBackStack(null)
                .commit();
        }
    }
}