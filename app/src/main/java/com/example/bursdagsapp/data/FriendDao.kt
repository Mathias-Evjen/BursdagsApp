package com.example.bursdagsapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FriendDao {
    @Insert
    suspend fun insert(friend: Friend)

    @Query("SELECT * FROM friend_table ORDER BY id DESC")
    fun getAllFriends(): Flow<List<Friend>>
}