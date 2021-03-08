package com.yi.jetpackDemo.room

import android.util.Log
import android.view.View
import android.widget.Toast
import com.yi.jetpackDemo.room.database.Address
import com.yi.jetpackDemo.room.database.DBManager
import com.yi.jetpackDemo.room.database.User
import com.yi.jetpackDemo.room.greenDaoDB.*
import kotlinx.coroutines.*

class ClickHandler(val fragment: RoomFragment) {

    private val mThreadPool = newFixedThreadPoolContext(6, "clickHandler")

    private val mDaoSession: DaoSession by lazy {
//        val database =
//            DBOpenHelper.getHelper(fragment.requireContext())?.getEncryptedWritableDb("abc")
//        DaoMaster(database).newSession()
        DaoMaster(DBOpenHelper.getHelper(fragment.requireContext())?.writableDb).newSession()
    }

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
                val users = Array<User>(10000) {
                    User(
                        null,
                        "hello-$it",
                        "android-$it",
                        "130-$it",
                        address = Address(null, "学情路-$it", "朝阳区-$it", "北京-$it", 1)
                    )
                }

                val currentTime = System.currentTimeMillis()
                Log.d("xiaoyi", "onClickInsert before insertAll $currentTime")
                DBManager.getDBManager(view.context).userDao().insertAll(*users)
                Log.d(
                    "xiaoyi",
                    "onClickInsert after insertAll totalTime = ${System.currentTimeMillis() - currentTime}"
                )
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(view.context, "insert success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onClickQuery(view: View) {
        runBlocking {
            launch(mThreadPool) {
                val currentTime = System.currentTimeMillis()
                Log.d("xiaoyi", "onClickQuery before query $currentTime")
                val datas = DBManager.getDBManager(view.context).userDao().getAll()
                Log.d(
                    "xiaoyi",
                    "onClickInsert after query totalTime = ${System.currentTimeMillis() - currentTime}"
                )
                GlobalScope.launch(Dispatchers.Main) {
                    fragment.refreshData(datas, null, 1)
                }
            }
        }
    }

    fun onClickDelete(view: View) {
        runBlocking {
            launch(mThreadPool) {
                val currentTime = System.currentTimeMillis()
                Log.d("xiaoyi", "onClickQuery before deleteAll $currentTime")
                DBManager.getDBManager(view.context).userDao().deleteAll()
                Log.d(
                    "xiaoyi",
                    "onClickInsert after deleteAll totalTime = ${System.currentTimeMillis() - currentTime}"
                )
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(view.context, "delete success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onClickInsert2(view: View) {
        runBlocking {
            launch(mThreadPool) {
//                mDaoSession.user2Dao.queryBuilder().b
                val users = Array<User2>(10000) {
                    val user2 = User2(
                        it.toLong(),
                        "hello-$it",
                        "android-$it",
                        "130-$it"
                    )
                    val address2 =
                        Address2(it.toLong(), "学情路-$it", "朝阳区-$it", "北京-$it", it.toLong(), 1)
                    user2.address = address2
                    user2
                }

                val currentTime = System.currentTimeMillis()
                Log.d("xiaoyi", "onClickInsert2 before insertAll $currentTime")
                mDaoSession.user2Dao.insertInTx(users.toList())
                val addresses = ArrayList<Address2>()
                users.forEach {
                    addresses.add(it.address)
                }
                mDaoSession.address2Dao.insertInTx(addresses)
                Log.d(
                    "xiaoyi",
                    "onClickInsert2 after insertAll totalTime = ${System.currentTimeMillis() - currentTime}"
                )
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(view.context, "insert success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onClickQuery2(view: View) {
        runBlocking {
            launch(mThreadPool) {
                val currentTime = System.currentTimeMillis()
                Log.d("xiaoyi", "onClickQuery2 before query $currentTime")
                val datas = mDaoSession.user2Dao.queryBuilder().list()

                Log.d(
                    "xiaoyi",
                    "onClickQuery2 after query totalTime = ${System.currentTimeMillis() - currentTime}"
                )
                GlobalScope.launch(Dispatchers.Main) {
                    fragment.refreshData(null, datas, 2)
                }
            }
        }
    }

    fun onClickDelete2(view: View) {
        runBlocking {
            launch(mThreadPool) {
                val currentTime = System.currentTimeMillis()
                Log.d("xiaoyi", "onClickDelete2 before deleteAll $currentTime")
                mDaoSession.user2Dao.deleteAll()
                mDaoSession.address2Dao.deleteAll()
                Log.d(
                    "xiaoyi",
                    "onClickDelete2 after deleteAll totalTime = ${System.currentTimeMillis() - currentTime}"
                )
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(view.context, "delete success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}