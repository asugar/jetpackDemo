package com.yi.jetpackDemo.room.database

import androidx.room.*

//@Entity(primaryKeys = arrayOf("firstName", "lastName")) 联合主键
//@Entity(tableName = "xiaoyi") 表别名
//@ColumnInfo(name = "first_name") 字段别名
//@Ignore val address: String = "" 忽略字段 == @Entity(ignoredColumns = arrayOf("picture"))
//@Fts3 @Fts4
//@Entity(indices = arrayOf(Index(value = ["last_name", "address"], unique = true))) // 特定列编入索引
//Embedded 相当于再当前表中插入类中的字段
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int?,
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    @Ignore val telephone: String?,
    @Embedded val address: Address?
) {
    constructor(
        uid: Int,
        firstName: String?,
        lastName: String?,
        address: Address?
    ) : this(uid, firstName, lastName, "", address)
}