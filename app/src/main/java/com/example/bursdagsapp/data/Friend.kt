package com.example.bursdagsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friend_table")
data class Friend (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phoneNumber: String,
    val birthMonth: Int,
    val birthDay: Int,
    val message: String
    )