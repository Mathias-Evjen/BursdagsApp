package com.example.bursdagsapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.room.util.query
import com.example.bursdagsapp.R
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.ui.components.FriendCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    friends: List<Friend>,
    onFriendClick: (Friend) -> Unit
) {


//    val mockFriends = listOf(
//        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
//        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
//        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
//        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
//        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
//        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
//        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
//        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
//        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
//        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
//        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
//        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
//        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
//        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
//        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
//        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
//        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
//        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
//        Friend(name = "Jonas", phoneNumber = "12345678", birthMonth = 8, birthDay = 29),
//        Friend(name = "Thomas", phoneNumber = "87654321", birthMonth = 10, birthDay = 8),
//        Friend(name = "Mathias", phoneNumber = "92858252", birthMonth = 5, birthDay = 14),
//    )

    val headerPadding = 24
    val listItemPadding = 16

    var expanded by rememberSaveable { mutableStateOf(false)}
    var searchText by remember { mutableStateOf("") }

    val filteredFriends = if (searchText.isBlank()) {
        friends
    } else {
        friends.filter { friend ->
            friend.name.contains(searchText, ignoreCase = true)
        }
    }
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

        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp, start = 4.dp, end = 4.dp),
            shape = RoundedCornerShape(32.dp),
            placeholder = { Text("Search for a friend") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
        )

        LazyColumn(
            modifier = Modifier
        ) {
            items(filteredFriends) { friend ->
                FriendCard(friend = friend, padding = listItemPadding, onFriendClick = { onFriendClick(friend) })
            }
        }
    }
}