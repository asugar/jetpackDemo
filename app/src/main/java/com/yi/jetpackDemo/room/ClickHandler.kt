package com.yi.jetpackDemo.room

import android.util.Log
import android.view.View
import kotlinx.coroutines.*

class ClickHandler(val fragment: RoomFragment) {

    private val mThreadPool = newFixedThreadPoolContext(6, "clickHandler")

    /**
     * 数据库插入
     */
    fun onClickInsert(view: View) {
        Log.d("xiaoyi", "onClickInsert")
        runBlocking {
            launch(mThreadPool) {
                DBManager.getDBManager(view.context).userDao().insertAll(
                    User(1, "hello", "java"),
                    User(2, "hello", "android"),
                    User(3, "hello", "es6"),
                    User(4, "hello", "html"),
                    User(5, "hello", "python")
                )
            }
        }
    }

    fun onClickQuery(view: View) {
        runBlocking {
            launch(mThreadPool) {
                val datas = DBManager.getDBManager(view.context).userDao().getAll()
                GlobalScope.launch(Dispatchers.Main) {
                    fragment.refreshData(datas)
                }
            }
        }
    }

    fun onClickDelete(view: View) {
        runBlocking {
            launch(mThreadPool) {
                DBManager.getDBManager(view.context).userDao().deleteAll()
            }
        }
    }
}