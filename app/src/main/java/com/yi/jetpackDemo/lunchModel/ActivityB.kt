package com.yi.jetpackDemo.lunchModel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.yi.jetpackDemo.databinding.ActivityBBinding
import com.yi.jetpackDemo.lunchModel.fragmentLife.FragmentB
import com.yi.jetpackDemo.view.vp.NavAdapter
import kotlinx.android.synthetic.main.activity_b.*

class ActivityB : AppCompatActivity() {

    lateinit var mBinding: ActivityBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onCreate")
        mBinding = ActivityBBinding.inflate(layoutInflater)
        mBinding.handler = ClickHandler(baseContext)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        mBinding.vpFragments.adapter = FragmentsAdapter(
            supportFragmentManager,
            NavAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        stlFragments.setViewPager(mBinding.vpFragments)
    }


    override fun onRestart() {
        super.onRestart()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onPause")
    }


    override fun onStop() {
        super.onStop()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_LAUNCH_MODEL, "ActivityB onDestroy")
    }

    private class FragmentsAdapter(fm: FragmentManager, behavior: Int) : NavAdapter(fm, behavior) {

        private val titles: Array<String> =
            arrayOf("B-1", "B-2", "B-3", "B-4", "B-5", "B-6", "B-7", "B-8", "B-9")

        override fun getItem(position: Int): Fragment {
            return FragmentB.getInstance("FragmentB-${titles[position]}")
        }

        override fun getCount(): Int {
            return titles.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }

    }
}