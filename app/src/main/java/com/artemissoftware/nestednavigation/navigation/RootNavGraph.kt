package com.artemissoftware.nestednavigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.nestednavigation.authentication.authNavGraph
import com.artemissoftware.nestednavigation.home.HOME_GRAPH
import com.artemissoftware.nestednavigation.home.HomeScreen
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

const val ROOT_GRAPH = "root_graph"

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    startDestination: String,
    changeTheme: (ThemeType) -> Unit,
) {
    NavHost(
        navController = navController,
        route = ROOT_GRAPH,
        startDestination = HOME_GRAPH,
    ) {
        authNavGraph(navController = navController)

        composable(route = HOME_GRAPH) {
            HomeScreen(
                changeTheme = changeTheme,
            )
        }
    }

//    NavHost(
//        navController = navController,
//        startDestination = MainRoute.Gallery.route,
//    ) {
//        composable(MainRoute.Gallery.route) {
//            GalleryScreen(
//                navigateToSearch = {
//                    navController.navigate("search/Berries")
//                },
//                navigateToDetails = {
//                    navController.navigate("details/$it")
//                },
//            )
//        }
//
//        composable(
//            route = MainRoute.Details.route,
//            arguments = MainRoute.Details.arguments,
//        ) { backStackEntry ->
//            val galleryId = backStackEntry.arguments?.getInt("id")
//            DetailsScreen(
//                galleryId,
//                popBackStack = {
//                    navController.popBackStack()
//                },
//            )
//        }
//
//        composable(
//            route = MainRoute.Search.route,
//            arguments = MainRoute.Search.arguments,
//        ) { backStackEntry ->
//
//            val argument = backStackEntry.arguments?.getString("args")
//
//            SearchScreen(
//                argument = argument,
//                popBackStack = {
//                    navController.popBackStack(MainRoute.Gallery.route, false)
//                },
//            )
//        }
//    }
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
