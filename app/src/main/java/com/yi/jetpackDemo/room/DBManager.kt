package com.yi.jetpackDemo.room

import android.content.Context
import androidx.room.Room

/**
 * 数据库管理者
 */
object DBManager {

    @Volatile
    private var mAppDatabase: AppDatabase? = null

    fun getDBManager(context: Context): AppDatabase {
        if (mAppDatabase == null) {
            mAppDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "database-jetpack"
            ).build()
        }
        return mAppDatabase!!
    }

}