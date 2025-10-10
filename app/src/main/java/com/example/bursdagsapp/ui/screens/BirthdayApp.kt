package com.example.bursdagsapp.ui.screens

import androidx.compose.foundation.background
import com.example.bursdagsapp.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.ui.components.FriendCard
import com.example.bursdagsapp.ui.theme.BursdagsAppTheme
import com.example.bursdagsapp.ui.viewmodels.FriendViewModel

@Composable
fun BirthdayApp(
    modifier: Modifier = Modifier,
    viewModel: FriendViewModel
) {
//    val friends by viewModel.friends.collectAsState()

    val friends = listOf(
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

    val HEADER_PADDING = 24
    val LIST_ITEM_PADDING = 16

    Column(
        modifier = modifier
    ) {
        FriendCard(
            personIcon = Icons.Default.Person,
            personIconDesc = "Person",
            cakeIcon = painterResource(R.drawable.cake),
            cakeIconDesc = "Birthday cake",
            padding = HEADER_PADDING)
        LazyColumn(
            modifier = Modifier
        ) {
            items(friends) { friend ->
                FriendCard(friend = friend, padding = LIST_ITEM_PADDING)
            }
        }
    }





}

@Preview
@Composable
fun BirthdayAppPreview() {
    BursdagsAppTheme {
        val friends = listOf(
            Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
            Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
            Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14)
        )

        val HEADER_PADDING = 24
        val LIST_ITEM_PADDING = 16


        Column(
            modifier = Modifier
        ) {
            FriendCard(
                personIcon = Icons.Default.Person,
                personIconDesc = "Person",
                cakeIcon = painterResource(R.drawable.cake),
                cakeIconDesc = "Birthday cake",
                padding = HEADER_PADDING)
            LazyColumn(
                modifier = Modifier
            ) {
                items(friends) { friend ->
                    FriendCard(friend = friend, padding = LIST_ITEM_PADDING)
                }
            }
        }
    }
}