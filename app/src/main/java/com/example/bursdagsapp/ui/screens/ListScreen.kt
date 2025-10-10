package com.example.bursdagsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.bursdagsapp.R
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.ui.components.FriendCard

@Composable
fun ListScreen(
    friends: List<Friend>,
    onFriendClick: (Friend) -> Unit
) {


    val mockFriends = listOf(
        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
    )

    val headerPadding = 24
    val listItemPadding = 16

    Column(
        modifier = Modifier
    ) {
        FriendCard(
            personIcon = Icons.Default.Person,
            personIconDesc = "Person",
            cakeIcon = painterResource(R.drawable.cake),
            cakeIconDesc = "Birthday cake",
            padding = headerPadding
        )
        LazyColumn(
            modifier = Modifier
        ) {
            items(mockFriends) { friend ->
                FriendCard(friend = friend, padding = listItemPadding, onFriendClick = { onFriendClick(friend) })
            }
        }
    }
}