package com.artemissoftware.nestednavigation.images

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val IMAGES_GRAPH = "images_graph"

fun NavGraphBuilder.imagesNavGraph(
    navController: NavController,
) {
    navigation(
        route = IMAGES_GRAPH,
        startDestination = ImageRoute.Images.route,
    ) {
        composable(route = ImageRoute.Images.route) {
            ImagesScreen(
                navigateToLandImage = {
                    navController.navigate(ImageRoute.ImageLand.route)
                },
                navigateToFaceImage = {
                    navController.navigate(ImageRoute.ImageFace.route)
                },
            )
        }
        composable(
            route = ImageRoute.ImageLand.route,
        ) {
            ImageLandScreen(
                navigateToFaceImage = {
                    navController.navigate(ImageRoute.ImageFace.route)
                },
            )
        }
        composable(
            route = ImageRoute.ImageFace.route,
        ) {
            ImageFaceScreen()
        }
    }
}

sealed class ImageRoute(val route: String) {
    data object Images : ImageRoute(route = "images")
    data object ImageLand : ImageRoute(route = "image_land")
    data object ImageFace : ImageRoute(route = "image_face")
}
