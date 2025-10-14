package com.example.bursdagsapp.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bursdagsapp.ui.screens.AddFriendScreen
import com.example.bursdagsapp.ui.screens.FriendDetailScreen
import com.example.bursdagsapp.ui.screens.ListScreen
import com.example.bursdagsapp.ui.screens.PreferencesScreen
import com.example.bursdagsapp.ui.viewmodels.FriendViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    viewModel: FriendViewModel,
    navController: NavHostController
) {
    val friends by viewModel.friends.collectAsState()

    NavHost(navController = navController, startDestination = "list", modifier = modifier) {
        composable(
            "list",
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX  = { -1000 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
        ) {
            ListScreen(
                friends,
                onFriendClick = {friend ->
                    navController.navigate("detail/${friend.id}")
                }
            )
        }
        composable(
            route = "detail/{friendId}",
            arguments = listOf(navArgument("friendId") { type =
                NavType.IntType })
        ) { backStackEntry ->
            val friendId = backStackEntry.arguments?.getInt("friendId")
            val friend = friends.find { it.id == friendId }
            friend?.let {
                FriendDetailScreen(it)
            }
        }

        composable(
            "add_friend",
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX  = { -1000 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
        ) {
            AddFriendScreen()
        }

        composable(
            "prefs",
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX  = { -1000 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
        ) {
            PreferencesScreen()
        }
    }
}