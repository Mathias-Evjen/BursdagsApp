package com.example.bursdagsapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    navController: NavHostController,
    onEditClick: (Friend) -> Unit
) {
    var showDeletePopup by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Name: ${friend.name}", style = MaterialTheme.typography.headlineMedium)
        Text("Birthday: ${friend.birthDay}/${friend.birthMonth}")
        Text("Phone number: ${friend.phoneNumber}")
        Text("Birthday message: ${friend.message}")

        Button(
            onClick = { onEditClick(friend) }
        ){
            Text("Edit")
        }
        Button(
            onClick = {
                showDeletePopup = true
            }
        ) {
            Text("Delete")
        }
    }

    if (showDeletePopup) {
        AlertDialog(
            modifier = Modifier.padding(),
            onDismissRequest = { showDeletePopup = false},
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteFriend(friend)
                        navController.navigateUp()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeletePopup = false }
                ) {
                    Text("Cancel")
                }
            },
            title = {
                Text("Are you sure?")
            },
            text = {
                Text("Do yo wish to delete ${friend.name}?")
            }
        )
    }
}