package com.example.bursdagsapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.data.PreferenceManager
import com.example.bursdagsapp.repositories.FriendRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar

class FriendViewModel(private val repository: FriendRepository, application: Application) : AndroidViewModel(application) {

    private val preferenceManager = PreferenceManager(application.applicationContext)

    val friends: StateFlow<List<Friend>> = repository.allFriends.stateIn(
        viewModelScope,
        SharingStarted.Lazily, emptyList()
    )

    fun addFriend(name: String, phoneNumber: String, birthday: Long, message: String) {
        val calendar = Calendar.getInstance().apply { timeInMillis = birthday }

        val defaultMessage = preferenceManager.getDefaultMessage()

        viewModelScope.launch {
            repository.insert(
                Friend(
                    name = name,
                    phoneNumber = phoneNumber,
                    birthMonth = calendar.get(Calendar.MONTH) + 1,
                    birthDay = calendar.get(Calendar.DAY_OF_MONTH),
                    message = message.ifBlank { defaultMessage }
                )
            )
        }
    }


    fun updateFriend(id: Int, name: String, phoneNumber: String, birthday: Long, message: String) {
        val calendar = Calendar.getInstance().apply { timeInMillis = birthday }

        viewModelScope.launch {
            repository.update(
                Friend(
                    id = id,
                    name = name,
                    phoneNumber = phoneNumber,
                    birthDay = calendar.get(Calendar.MONTH) + 1,
                    birthMonth = calendar.get(Calendar.DAY_OF_MONTH),
                    message = message
                )
            )
        }
    }

    fun deleteFriend(friendToDelete: Friend) {
        viewModelScope.launch {
            repository.delete(friendToDelete)
        }
    }
}