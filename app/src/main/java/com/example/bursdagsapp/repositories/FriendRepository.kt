package com.example.bursdagsapp.repositories

import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.data.FriendDao
import kotlinx.coroutines.flow.Flow

class FriendRepository(private val dao: FriendDao) {
    val allFriends: Flow<List<Friend>> = dao.getAllFriends()

    suspend fun insert(friend: Friend) {
        dao.insert(friend)
    }

    suspend fun delete(friend: Friend) {
        dao.delete(friend)
    }
}