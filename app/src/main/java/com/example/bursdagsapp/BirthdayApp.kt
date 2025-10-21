package com.example.bursdagsapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavigationGraph(modifier = Modifier.padding(innerPadding), viewModel = viewModel, navController = navController)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        Destination.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = currentRoute == destination.route,
                onClick = {
                    navController.navigate(route = destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
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


enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDestination: String
) {
    LIST("list", "Friends", Icons.AutoMirrored.Filled.List, "List of friends"),
    ADD_FRIEND("add_friend", "Add friend", Icons.Default.Add, "Add friend"),
    PREFERENCES("prefs", "Preferences", Icons.Default.Settings, "Preferences")
}