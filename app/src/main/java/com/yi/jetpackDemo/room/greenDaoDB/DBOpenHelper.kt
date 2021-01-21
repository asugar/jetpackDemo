package com.yi.jetpackDemo.room.greenDaoDB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.greenrobot.greendao.database.Database

/**
 * greenDao 数据库helper
 */
class DBOpenHelper : DaoMaster.DevOpenHelper {

    private val V_1 = 1
    private val V_2 = 2

    companion object {
        private val NAME = "jetpack-db"

        @Volatile
        private var mHelper: DBOpenHelper? = null

        @Synchronized
        fun getHelper(context: Context): DBOpenHelper? {
            if (mHelper == null) {
                mHelper = DBOpenHelper(
                    context.applicationContext,
                    NAME,
                    null
                )
            }
            return mHelper
        }
    }


    constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?)
            : super(context, name, factory)

    override fun onCreate(db: Database?) {
//        super.onCreate(db)
        DaoMaster.createAllTables(db, true)
    }

    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < V_2) {
//            MessageDao.createTable(db, true)
//            MigrationHelper.migrate(db, MessageDao::class.java)
        } else {
            super.onUpgrade(db, oldVersion, newVersion) // 删除所有表，再重新创建所有表
        }
    }

    private fun execSql(db: Database, sql: String) {
        try {
            db.execSQL(sql)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}