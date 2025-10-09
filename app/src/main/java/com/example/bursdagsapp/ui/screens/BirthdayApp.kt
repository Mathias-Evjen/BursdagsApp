package com.example.bursdagsapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bursdagsapp.ui.components.FriendCard
import com.example.bursdagsapp.ui.theme.BursdagsAppTheme
import com.example.bursdagsapp.ui.viewmodels.FriendViewModel

@Composable
fun BirthdayApp(
    modifier: Modifier = Modifier,
    viewModel: FriendViewModel
) {
    val friends by viewModel.friends.collectAsState()

    LazyColumn(
        modifier = Modifier
    ) {
        items(friends) { friend ->
            FriendCard(friend = friend)
        }
    }



}

@Preview
@Composable
fun BirthdayAppPreview() {
    BursdagsAppTheme {


//        BirthdayApp(viewModel = FriendViewModel())
    }
}