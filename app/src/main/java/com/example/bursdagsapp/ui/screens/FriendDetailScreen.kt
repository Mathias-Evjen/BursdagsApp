package com.example.bursdagsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.ui.viewmodels.FriendViewModel

@Composable
fun FriendDetailScreen(
    friend: Friend,
    viewModel: FriendViewModel,
    navController: NavHostController
) {
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Name: ${friend.name}", style = MaterialTheme.typography.headlineMedium)
        Text("Birthday: ${friend.birthDay}/${friend.birthMonth}")
        Text("Phone number: ${friend.phoneNumber}")
        Text("Birthday message: ${friend.message}")

        Button(
            onClick = {}
        ){
            Text("Edit")
        }
        Button(
            onClick = {
                viewModel.deleteFriend(friend)
                navController.navigateUp()
            }
        ) {
            Text("Delete")
        }
    }
}