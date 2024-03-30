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

fun NavController.navigateToRandomImageFull(randomImage: RandomImage) = navigate(RandomImageRoute.ImageFull.withCustomArgs(randomImage))
fun NavController.navigateToRandomImage(image: Image) = navigate(RandomImageRoute.Image.withCustomArgs(image))

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
                    navController.navigateToRandomImage(Image.Regular(imageId = it.imageId))
                },
                navigateToImageFull = {
                    navController.navigateToRandomImageFull(it)
                },
            )
        }
        composable(
            route = RandomImageRoute.Image.getRouteInFull(),
            arguments = RandomImageRoute.Image.arguments,
        ) {
            val randomImage = it.arguments?.let { image -> ImageNavType().parseValue(image.get(NavArguments.RANDOM_IMAGE) as String) }!!
            val dd = it.arguments?.toString()
            val ddd = dd + "dsdfs"

            RandomImageScreen(
                popBackStack = {
                    navController.popBackStack()
                },
            )
        }
        composable(
            route = RandomImageRoute.ImageFull.getRouteInFull(),
            arguments = RandomImageRoute.ImageFull.arguments,
        ) {
            val randomImage = it.arguments?.let { image -> RandomImageNavType().parseValue(image.get(NavArguments.RANDOM_IMAGE) as String) }!!

            RandomImageFullScreen(
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
    themeType: ThemeType = ThemeType.DEFAULT,
) : BaseDestination(
    route = route,
    customArguments = arguments,
    themeType = themeType,
) {
    data object ImagesList : RandomImageRoute(route = "random_images_list")

    data object Image : RandomImageRoute(
        themeType = ThemeType.RANDOM_IMAGE,
        route = "random_image",
        arguments = listOf(
            navArgument(
                name = NavArguments.RANDOM_IMAGE,
            ) {
                type = ImageNavType()
                nullable = true
                defaultValue = null
            },
//            navArgument(
//                name = NavArguments.RANDOM_IMAGE_FULL,
//            ) {
//                type = ImageFullNavType()
//            },
        ),
    ) {

        override fun getJsonEncoding(arg: Any?): String? {
            arg?.let { argument ->
                val type = argument.javaClass.simpleName
                val lolo = gson.toJsonTree(argument).asJsonObject
                lolo.addProperty("type", type)
                val json = gson.toJson(lolo)

                return json
            }
            return null
        }
    }

    data object ImageFull : RandomImageRoute(
        themeType = ThemeType.RANDOM_IMAGE,
        route = "random_image_full",
        arguments = listOf(
            navArgument(
                name = NavArguments.RANDOM_IMAGE,
            ) {
                type = RandomImageNavType()
            },
        ),
    )
}
