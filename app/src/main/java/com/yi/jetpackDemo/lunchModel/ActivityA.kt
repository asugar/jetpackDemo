package com.yi.jetpackDemo.lunchModel

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.R
import com.yi.jetpackDemo.databinding.ActivityABinding
import com.yi.jetpackDemo.killed.API
import com.yi.jetpackDemo.lunchModel.fragmentLife.FragmentA

/**
 * onRestoreInstanceState 什么时机能执行
 * 横竖屏切换
 */
class ActivityA : AppCompatActivity(), OnActivityClickListener {

    lateinit var mBinding: ActivityABinding
    val KEY_STATE = "state"
    val KEY_VIEW = "view"
    var count: Int = 1

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        // 只有冷启才会执行一次
        Logger.t("xiaoyi").d("ActivityA attachBaseContext")
    }

    override fun onActivityClick(name: String) {
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onActivityClick $name")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onCreate $savedInstanceState")
        mBinding = ActivityABinding.inflate(layoutInflater)
        mBinding.handler = ClickHandler(baseContext)
        setContentView(mBinding.root)
        initView()
    }

    private fun initView() {
        mBinding.tvSwitch.setOnClickListener {
            createFragment()
//            testKilledProcess()
        }
        mBinding.tvAdd.setOnClickListener {
            addFragment()
        }
    }

    private fun testKilledProcess() {
        val str = API.create()
        Logger.t("MyApplication").d("API.create() $str")
    }

    private fun addFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentA.getInstance("fragment-a-add-${count++}")
        transaction.setCustomAnimations(
            R.anim.slide_in,  // enter
            R.anim.fade_out,  // exit
            R.anim.fade_in,   // popEnter
            R.anim.slide_out  // popExit
        )
        transaction.add(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)// commit之后会放在返回栈中，点击返回会回到之前栈
        transaction.commit()
    }

    private fun createFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = FragmentA.getInstance("fragment-a-switch-${count++}")
        transaction.setCustomAnimations(
            R.anim.slide_in,  // enter
            R.anim.fade_out,  // exit
            R.anim.fade_in,   // popEnter
            R.anim.slide_out  // popExit
        )
        transaction.replace(R.id.fragment_a, fragment)
        transaction.addToBackStack(null)// commit之后会放在返回栈中，点击返回会回到之前栈
        transaction.commit()
//        supportFragmentManager.findFragmentById(R.id.fragment_a)// 获取fragment的实例
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onNewIntent")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onStart")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val state = savedInstanceState.getString(KEY_STATE)
        val view = savedInstanceState.getString(KEY_VIEW)
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onRestoreInstanceState $state $view")
        mBinding.tvTitle.text = view?.length.toString()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        val state = persistentState?.getString(KEY_STATE)
        val view = persistentState?.getString(KEY_VIEW)
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onRestoreInstanceState 2 $state $view")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onSaveInstanceState")
        outState.run {
            putString(KEY_STATE, "open-p")
            putString(KEY_VIEW, mBinding.tvTitle.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onSaveInstanceState 2")
        outPersistentState.run {
            putString(KEY_STATE, "open-2")
            putString(KEY_VIEW, mBinding.tvTitle.text.toString())
        }
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_LAUNCH_MODEL, "ActivityA onDestroy")
    }

}