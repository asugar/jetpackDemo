package com.yi.jetpackDemo.room.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * 数据库管理者
 */
object DBManager {

    @Volatile
    private var mAppDatabase: AppDatabase? = null

    /**
     * 数据库升级
     * 简单的；修改表结构
     * 复杂的：新建表->插入数据->修改表名
     */
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {

            database.execSQL(
                "CREATE TABLE Address (id INTEGER, street TEXT, state TEXT, city TEXT, post_code INTEGER, PRIMARY KEY(id))"
            )

//            database.execSQL(
//                "ALTER TABLE user "
//                        + " ADD COLUMN address Address"
//            );

        }
    }

    fun getDBManager(context: Context): AppDatabase {
        if (mAppDatabase == null) {
            mAppDatabase = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "database-jetpack2"
            ).addMigrations(MIGRATION_1_2).build()
        }
        return mAppDatabase!!
    }

}