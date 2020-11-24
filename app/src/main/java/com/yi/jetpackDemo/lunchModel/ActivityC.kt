package com.yi.jetpackDemo.lunchModel

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.databinding.ActivityCBinding
import com.yi.jetpackDemo.lunchModel.fragmentLife.FragmentC

/**
 * fragment shared element transitions
 */
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
        val fragment = FragmentC.getInstance("fragmentC-${count++}")
        supportFragmentManager
            .beginTransaction()
            .add(R.id.flContent, fragment)
            .commit()

        mBinding.ivIcon.setOnClickListener {
            //启动activity
            val intent = Intent(this@ActivityC, ActivityC2::class.java)
            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@ActivityC,
                mBinding.ivIcon,
                "shared_image"
            )
            startActivity(intent, options.toBundle())
        }
    }
}