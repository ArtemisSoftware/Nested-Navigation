package com.artemissoftware.nestednavigation.randomimages

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val RANDOM_IMAGES_GRAPH = "random_images_graph"

fun NavController.navigateToRandomImagesGraph(navOptions: NavOptions) = navigate(RANDOM_IMAGES_GRAPH, navOptions)

fun NavGraphBuilder.randomImagesNavGraph(
    navController: NavController,
) {
    navigation(
        route = RANDOM_IMAGES_GRAPH,
        startDestination = RandomImageRoute.ImagesList.route,
    ) {
        composable(route = RandomImageRoute.ImagesList.route) {
            RandomImagesListScreen(
                navigateToImage = {
                    navController.navigate(RandomImageRoute.Image.route)
                },
                navigateToImageFull = {
                    navController.navigate(RandomImageRoute.ImageFull.route)
                },
            )
        }
        composable(
            route = RandomImageRoute.Image.route,
        ) {
            RandomImageScreen()
        }
        composable(
            route = RandomImageRoute.ImageFull.route,
        ) {
            RandomImageFullScreen()
        }
    }
}

sealed class RandomImageRoute(val route: String) {
    data object ImagesList : RandomImageRoute(route = "random_images_list")
    data object Image : RandomImageRoute(route = "random_image")
    data object ImageFull : RandomImageRoute(route = "random_image_full")
}
