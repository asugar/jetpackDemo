package com.yi.jetpackDemo.room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Embedded
@Entity
data class Address(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo val street: String?,
    @ColumnInfo val state: String?,
    @ColumnInfo val city: String?,
    @ColumnInfo(name = "post_code") val postCode: Int
)