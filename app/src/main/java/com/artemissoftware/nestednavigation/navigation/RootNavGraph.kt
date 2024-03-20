package com.artemissoftware.nestednavigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.artemissoftware.nestednavigation.gallery.DetailsScreen
import com.artemissoftware.nestednavigation.gallery.GalleryScreen
import com.artemissoftware.nestednavigation.gallery.SearchScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    startDestination: String,
    alterStatusBarColor: (Color) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Gallery.route,
    ) {
        composable(MainRoute.Gallery.route) {
            GalleryScreen(
                navigateToSearch = {
                    navController.navigate("search/Berries")
                },
                navigateToDetails = {
                    navController.navigate("details/$it")
                },
            )
        }

        composable(
            route = MainRoute.Details.route,
            arguments = MainRoute.Details.arguments,
        ) { backStackEntry ->
            val galleryId = backStackEntry.arguments?.getInt("id")
            DetailsScreen(
                galleryId,
                popBackStack = {
                    navController.popBackStack()
                },
            )
        }

        composable(
            route = MainRoute.Search.route,
            arguments = MainRoute.Search.arguments,
        ) { backStackEntry ->

            val argument = backStackEntry.arguments?.getString("args")

            SearchScreen(
                argument = argument,
                popBackStack = {
                    navController.popBackStack(MainRoute.Gallery.route, false)
                },
            )
        }
    }
}

sealed class MainRoute(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    data object Gallery : MainRoute("gallery")
    data object Details : MainRoute(
        route = "details/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType }),
    )
    data object Search : MainRoute(
        route = "search/{args}",
        arguments = listOf(navArgument("args") { type = NavType.StringType }),
    )
}

//    NavHost(
//        navController = navController,
//        route = Graph.ROOT,
//        startDestination = startDestination,
//    ) {
//        //imagesNavGraph(navController)
//
//        //settingsNavGraph(navController, alterStatusBarColor)
//    }
