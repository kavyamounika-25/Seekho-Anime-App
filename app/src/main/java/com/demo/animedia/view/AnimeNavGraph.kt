package com.demo.animedia.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.demo.animedia.viewmodel.AnimeListViewModel

sealed class Screen(val route: String) {
    object AnimeList : Screen("anime_list")
    object AnimeDetails : Screen("anime_details")
}

@Composable
fun AnimeNavGraph(viewModel: AnimeListViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.AnimeList.route) {
        composable(Screen.AnimeList.route) {
            AnimeHomepage(viewModel = viewModel, navController = navController)
        }
        composable(
            "${Screen.AnimeDetails.route}/{animeId}",
            arguments = listOf(navArgument("animeId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val animeId = backStackEntry.arguments?.getInt("animeId") ?: 0

            AnimeDetailScreen(animeId, viewModel.animeList.value)
        }
    }
}