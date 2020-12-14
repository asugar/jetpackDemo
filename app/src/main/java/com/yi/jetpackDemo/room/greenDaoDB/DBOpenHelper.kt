package com.yi.jetpackDemo.room.greenDaoDB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.greenrobot.greendao.database.Database

/**
 * greenDao 数据库helper
 */
class DBOpenHelper : DaoMaster.DevOpenHelper {

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
        super.onUpgrade(db, oldVersion, newVersion)
    }

    private fun execSql(db: Database, sql: String) {
        try {
            db.execSQL(sql)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}