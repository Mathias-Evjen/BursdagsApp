package com.example.bursdagsapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.repositories.FriendRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

class FriendViewModel(private val repository: FriendRepository, application: Application) : AndroidViewModel(application) {
    val friends: StateFlow<List<Friend>> = repository.allFriends.stateIn(
        viewModelScope,
        SharingStarted.Lazily, emptyList()
    )

    fun addFriend(name: String, phoneNumber: String, birthday: Long, message: String?) {

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = birthday

        val birthMonth = calendar.get(Calendar.MONTH) + 1
        val birthDay = calendar.get(Calendar.DAY_OF_MONTH)

        val friendToInsert = if (message != null) {
            Friend(
                name = name,
                phoneNumber = phoneNumber,
                birthMonth = birthMonth,
                birthDay = birthDay,
                message = message
            )
        } else {
            Friend(
                name = name,
                phoneNumber = phoneNumber,
                birthMonth = birthMonth,
                birthDay = birthDay
            )
        }

        viewModelScope.launch {
            repository.insert(friendToInsert)
        }
    }
}