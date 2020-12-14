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

            // 新加表
            database.execSQL(
                "CREATE TABLE Address (id INTEGER, street TEXT, state TEXT, city TEXT, post_code INTEGER, PRIMARY KEY(id))"
            )

            // 添加表字段
            database.execSQL("ALTER TABLE Address ADD COLUMN door INTEGER")

            // 修改表字段
            database.execSQL(
                """
                CREATE TABLE new_Song (
                    id INTEGER PRIMARY KEY NOT NULL,
                    name TEXT,
                    tag TEXT NOT NULL DEFAULT ''
                )
                """.trimIndent()
            )
            database.execSQL(
                """
                INSERT INTO new_Song (id, name, tag)
                SELECT id, name, tag FROM Song
                """.trimIndent()
            )
            database.execSQL("DROP TABLE Song")
            database.execSQL("ALTER TABLE new_Song RENAME TO Song")
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