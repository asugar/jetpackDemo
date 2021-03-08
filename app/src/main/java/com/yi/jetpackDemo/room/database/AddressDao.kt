package com.yi.jetpackDemo.room.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AddressDao {
    @Query("SELECT * FROM Address")
    fun getAll(): List<Address>

    @Insert
    fun insertAll(vararg users: Address)

//    @Query("insert into user values(:user)")
//    fun insert(user: User)

    @Delete
    fun delete(user: Address)
}