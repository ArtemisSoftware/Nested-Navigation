package com.artemissoftware.nestednavigation.food

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

const val GALLERY_GRAPH = "gallery_graph"

fun NavController.navigateToGalleryGraph(navOptions: NavOptions) = navigate(GALLERY_GRAPH, navOptions)

fun NavController.navigateToGallerySearch() = navigate(GalleryRoute.Search.route)

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

        composable(
            route = GalleryRoute.Details.route,
            arguments = GalleryRoute.Details.arguments,
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
            route = GalleryRoute.Search.route,
            arguments = GalleryRoute.Search.arguments,
        ) { backStackEntry ->

            val argument = backStackEntry.arguments?.getString("args")

            SearchScreen(
                argument = argument,
                popBackStack = {
                    navController.popBackStack(GalleryRoute.Gallery.route, false)
                },
            )
        }
    }
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
        themeType = ThemeType.AUTHENTICATION,
    )
    data object Search : GalleryRoute(
        route = "search/{args}",
        arguments = listOf(navArgument("args") { type = NavType.StringType }),
    )
}
