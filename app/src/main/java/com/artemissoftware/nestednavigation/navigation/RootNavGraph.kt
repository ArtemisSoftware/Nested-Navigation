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
            DetailsScreen(galleryId)
        }

        composable(MainRoute.Search.route) {
            SearchScreen()
        }
    }
}

sealed class MainRoute(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    data object Gallery : MainRoute("gallery")
    data object Details : MainRoute(
        route = "details/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType }),
    )
    data object Search : MainRoute("search")
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
