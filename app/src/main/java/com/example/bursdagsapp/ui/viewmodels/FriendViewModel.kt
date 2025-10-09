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

class FriendViewModel(private val repository: FriendRepository, application: Application) : AndroidViewModel(application) {
    val friends: StateFlow<List<Friend>> = repository.allFriends.stateIn(viewModelScope,
        SharingStarted.Lazily, emptyList())

    fun addPerson(name: String, phoneNumber: String, birthMonth: Int, birthDay: Int) {
        viewModelScope.launch {
            repository.insert(Friend(
                name = name,
                phoneNumber = phoneNumber,
                birthMonth = birthMonth,
                birthDay = birthDay))
        }
    }
}