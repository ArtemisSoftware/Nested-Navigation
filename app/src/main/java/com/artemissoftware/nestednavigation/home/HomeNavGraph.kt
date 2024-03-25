package com.artemissoftware.nestednavigation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.artemissoftware.nestednavigation.gallery.GALLERY_GRAPH
import com.artemissoftware.nestednavigation.gallery.galleryNavGraph

const val HOME_GRAPH = "home_graph"

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    startGraph: String
) {
    NavHost(
        navController = navController,
        route = HOME_GRAPH,
        startDestination = startGraph,
    ) {
        galleryNavGraph(navController)

//        composable(route = MainRoute.Home.route) {
//            ScreenContent(
//                name = "Home",
//                onClick = {
//                    navController.navigate(GALLERY_GRAPH)
//                },
//            )
//        }
//        composable(route = MainRoute.Profile.route) {
//            ScreenContent(
//                name = "Profile",
//                onClick = {
//                    navController.navigate(IMAGES_GRAPH)
//                },
//            )
//        }
//        composable(route = MainRoute.Settings.route) {
//            SettingsScreen(
// //                name = "Settings",
// //                onClick = {
// //                    // navController.navigate(Graph.DETAILS)
// //                },
//            )
//        }
//
//
//        imagesNavGraph(navController)
//        // detailsNavGraph(navController = navController)
    }
}

// TODO: provisório

sealed class MainRoute(val route: String) {
    data object Home : MainRoute(route = "home")
    data object Profile : MainRoute(route = "splash")
    data object Settings : MainRoute(route = "settings")
}