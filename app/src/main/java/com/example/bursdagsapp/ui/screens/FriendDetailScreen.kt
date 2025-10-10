package com.example.bursdagsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bursdagsapp.data.Friend

@Composable
fun FriendDetailScreen(
    friend: Friend
) {
    Column(
        Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Name: ${friend.name}", style = MaterialTheme.typography.headlineMedium)
        Text("Birthday: ${friend.birthDay}/${friend.birthMonth}")
        Text("Phone number: ${friend.phoneNumber}")
    }
}