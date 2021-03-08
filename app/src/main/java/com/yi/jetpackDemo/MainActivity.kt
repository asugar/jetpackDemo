package com.yi.jetpackDemo

import android.os.Bundle
import android.os.Debug
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.orhanobut.logger.Logger
import com.yi.jetpackDemo.killed.API

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Debug.startMethodTracing("homeView")
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        Logger.t(tag)
            .d("versionName=${BuildConfig.VERSION_NAME} versionCode=${BuildConfig.VERSION_CODE}")

        val businessTags = getBusinessTags()
        Logger.t(tag).d("businessTags $businessTags")
    }

    override fun onResume() {
        super.onResume()
        API.init("")
    }

    private fun getBusinessTags(): List<String> {
        val tags = ArrayList<String>()
        try {
            val files = resources.assets.list("")
            Logger.t(tag).d("getBusinessTags ${files?.toList()} ")
            val prodBusinessTags = files?.filter { it.matches(Regex("^prod_\\w*.zip\$")) }
            val reactBusinessTags = files?.filter { it.matches(Regex("^react_\\w*.zip\$")) }
            Logger.t(tag)
                .d("BusinessTags ${prodBusinessTags?.toList()} ${reactBusinessTags?.toList()} ")
            prodBusinessTags?.forEach {
                val businessTag = it.substring(5, it.indexOf("."))
                Logger.t(tag).d("businessTag $businessTag ")
                tags.add(businessTag)
            }
            reactBusinessTags?.forEach {
                val businessTag = it.substring(6, it.indexOf("."))
                Logger.t(tag).d("businessTag $businessTag ")
                tags.add(businessTag)
            }
            return tags
        } catch (e: Exception) {
            return tags
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Debug.stopMethodTracing()
    }
}