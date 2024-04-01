package com.artemissoftware.nestednavigation.randomimages

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.artemissoftware.nestednavigation.product.BaseDestination
import com.artemissoftware.nestednavigation.product.NavArguments
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

const val RANDOM_IMAGES_GRAPH = "random_images_graph"

fun NavController.navigateToRandomImagesGraph(navOptions: NavOptions) = navigate(RANDOM_IMAGES_GRAPH, navOptions)

fun NavController.navigateToRandomImage(randomImage: RandomImage) = navigate(RandomImageRoute.Image.withCustomArgs(randomImage))

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
                    navController.navigateToRandomImage(it)
                },
            )
        }
        composable(
            route = RandomImageRoute.Image.getRouteInFull(),
            arguments = RandomImageRoute.Image.arguments,
        ) {
            val randomImage = it.arguments?.let { image -> ImageNavType().parseValue(image.get(NavArguments.RANDOM_IMAGE) as String) }!!

            RandomImageScreen(
                randomImage = randomImage,
                popBackStack = {
                    navController.popBackStack()
                },
            )
        }
    }
}

sealed class RandomImageRoute(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    themeType: ThemeType = ThemeType.RANDOM_IMAGE,
) : BaseDestination(
    route = route,
    customArguments = arguments,
    themeType = themeType,
) {
    data object ImagesList : RandomImageRoute(route = "random_images_list")

    data object Image : RandomImageRoute(
        route = "random_image",
        arguments = listOf(
            navArgument(
                name = NavArguments.RANDOM_IMAGE,
            ) {
                type = ImageNavType()
            },
        ),
    ) {

        override fun getJsonEncoding(arg: Any?): String? {
            arg?.let { argument ->
                return (argument as RandomImage).toNavString()
            }
            return null
        }
    }
}
