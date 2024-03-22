package com.artemissoftware.nestednavigation.gallery

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

const val GALLERY_GRAPH = "gallery_graph"

fun NavGraphBuilder.galleryNavGraph(
    navController: NavController,
) {
    navigation(
        route = GALLERY_GRAPH,
        startDestination = GalleryRoute.Gallery.route,
    ) {
        composable(GalleryRoute.Gallery.route) {
            GalleryScreen(
                navigateToSearch = {
                    navController.navigate("search/Berries")
                },
                navigateToDetails = {
                    navController.navigate("details/$it")
                },
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

sealed class GalleryRoute(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val themeType: ThemeType = ThemeType.GALLERY,
) {
    data object Gallery : GalleryRoute("gallery")
    data object Details : GalleryRoute(
        route = "details/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType }),
    )
    data object Search : GalleryRoute(
        route = "search/{args}",
        arguments = listOf(navArgument("args") { type = NavType.StringType }),
    )
}
