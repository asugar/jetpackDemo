package com.yi.jetpackDemo.room.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class, Address::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun AddressDao(): AddressDao
}