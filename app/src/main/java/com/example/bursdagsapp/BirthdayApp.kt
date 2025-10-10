package com.example.bursdagsapp

import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.ui.components.FriendCard
import com.example.bursdagsapp.ui.navigation.NavigationGraph
import com.example.bursdagsapp.ui.theme.BursdagsAppTheme
import com.example.bursdagsapp.ui.viewmodels.FriendViewModel


@Composable
fun BirthdayApp(
    modifier: Modifier = Modifier,
    viewModel: FriendViewModel
) {
    val navController = rememberNavController()
    val startDestination = Destination.LIST
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            if (destination == Destination.ADD_FRIEND) {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.contentDestination,
                                    modifier = Modifier.size(36.dp)
                                )
                            } else {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.contentDestination)
                            }
                        },
                        label = { Text(destination.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavigationGraph(modifier = Modifier.padding(innerPadding), viewModel = viewModel, navController = navController)
    }
}


enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDestination: String
) {
    LIST("list", "Friends", Icons.Default.List, "List of friends"),
    ADD_FRIEND("add_friend", "Add friend", Icons.Default.Add, "Add friend"),
    PREFERENCES("prefs", "Preferences", Icons.Default.Settings, "Preferences")
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

        val headerPadding = 24
        val listItemPadding = 16


        Column(
            modifier = Modifier.Companion
        ) {
            FriendCard(
                personIcon = Icons.Default.Person,
                personIconDesc = "Person",
                cakeIcon = painterResource(R.drawable.cake),
                cakeIconDesc = "Birthday cake",
                padding = headerPadding,
                onFriendClick = {}
            )
            LazyColumn(
                modifier = Modifier.Companion
            ) {
                items(friends) { friend ->
                    FriendCard(friend = friend, padding = listItemPadding, onFriendClick = {})
                }
            }
        }
    }
}