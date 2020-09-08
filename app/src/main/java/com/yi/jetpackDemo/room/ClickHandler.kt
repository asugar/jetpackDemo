package com.yi.jetpackDemo.room

import android.util.Log
import android.view.View
import com.yi.jetpackDemo.room.database.Address
import com.yi.jetpackDemo.room.database.DBManager
import com.yi.jetpackDemo.room.database.User
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
                val user = DBManager.getDBManager(view.context).userDao().findById(1)
                if (user != null) {
                    return@launch
                }
                DBManager.getDBManager(view.context).userDao().insertAll(
//                    address = Address("1", "1", "1", 1)
                    User(1, "hello", "java", "", address = Address(null, "1", "1", "1", 1)),
                    User(2, "hello", "android", "", address = Address(null, "1", "1", "1", 1)),
                    User(3, "hello", "es6", "", address = Address(null, "1", "1", "1", 1)),
                    User(4, "hello", "html", "", address = Address(null, "1", "1", "1", 1)),
                    User(5, "hello", "python", "", address = Address(null, "1", "1", "1", 1))
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