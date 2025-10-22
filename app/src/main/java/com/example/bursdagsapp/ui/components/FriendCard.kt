package com.example.bursdagsapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bursdagsapp.R
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.ui.viewmodels.FriendViewModel

@Composable
fun FriendCard(
    modifier: Modifier = Modifier,
    friend: Friend,
    padding: Int,
    navController: NavHostController,
    viewModel: FriendViewModel
) {

    var isCardExpanded by remember { mutableStateOf(false) }
    var isMenuExpanded by remember { mutableStateOf(false) }

    var showDeletePopup by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(bottom = 4.dp, start = 4.dp, end = 4.dp)
            .animateContentSize(),
        onClick = {isCardExpanded = !isCardExpanded}
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row {
                        Icon(Icons.Default.Person,
                            contentDescription = "Person icon",
                            modifier = Modifier.padding(end = 16.dp),)
                        Text(text = friend.name, style = MaterialTheme.typography.titleLarge)
                    }
                }
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Icon(painter = painterResource(R.drawable.cake),
                            contentDescription = "Birthday cake",
                            modifier = Modifier.padding(end = 16.dp))
                        Text(text = "${friend.birthDay}/${friend.birthMonth}", style = MaterialTheme.typography.titleLarge)
                    }
                }

                Box(
                    modifier = Modifier.weight(0.5f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Button(
                        onClick = { isMenuExpanded = true },
                        colors = ButtonColors(containerColor = Color.Transparent, contentColor = Color.White, disabledContentColor = Color.Gray, disabledContainerColor = Color.Gray)
                    ) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false },
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.dropdown_edit)) },
                            onClick = {
                                navController.navigate("edit/${friend.id}")
                                isMenuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.dropdown_delete)) },
                            onClick = {
                                showDeletePopup = true
                                isMenuExpanded = false
                            }
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = isCardExpanded,
                modifier = Modifier.background(color = MaterialTheme.colorScheme.tertiary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp)
                ) {
                    Text("Details:", style = MaterialTheme.typography.titleMedium)
                    Text("Phone: ${friend.phoneNumber}")
                    Text("Message: \"${friend.message}\"")
                }
            }
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
                        showDeletePopup = false
                    }
                ) {
                    Text(stringResource(R.string.popup_confirm), color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeletePopup = false },
                ) {
                    Text(stringResource(R.string.popup_cancel), color = Color.LightGray)
                }
            },
            title = {
                Text(stringResource(R.string.popup_title))
            },
            text = {
                Text(stringResource(R.string.popup_text, friend.name))
            }
        )
    }
}
