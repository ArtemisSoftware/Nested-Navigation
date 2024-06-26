package com.artemissoftware.nestednavigation.randomimages

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.artemissoftware.nestednavigation.food.FoodConstants
import com.artemissoftware.nestednavigation.navigation.BaseDestination
import com.artemissoftware.nestednavigation.navigation.NavArguments
import com.artemissoftware.nestednavigation.navigation.NavArguments.RANDOM_IMAGE_RECIPIENT
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

const val RANDOM_IMAGES_GRAPH = "random_images_graph"

fun NavController.navigateToRandomImagesGraph(navOptions: NavOptions) = navigate(RANDOM_IMAGES_GRAPH, navOptions)

fun NavController.navigateToRandomImage(randomImage: RandomImage) = navigate(RandomImageRoute.Image.withCustomArgs(randomImage))

fun NavGraphBuilder.randomImagesNavGraph(
    navController: NavController,
    navigateToFoodDetail: (Int) -> Unit,
) {
    navigation(
        route = RANDOM_IMAGES_GRAPH,
        startDestination = RandomImageRoute.ImagesList.route,
    ) {
        composable(route = RandomImageRoute.ImagesList.route) {

            val randomImageRecipient = it.savedStateHandle.get<RandomImageRecipient>(RANDOM_IMAGE_RECIPIENT)

            RandomImagesListScreen(
                randomImageRecipient = randomImageRecipient,
                navigateToImage = {
                    navController.navigateToRandomImage(it)
                },
            )
        }
        composable(
            route = RandomImageRoute.Image.getRouteInFull(),
            arguments = RandomImageRoute.Image.arguments,
        ) {
            val randomImage = it.arguments?.let { image -> ImageNavType().parseValue(image.get(
                NavArguments.RANDOM_IMAGE) as String) }!!

            RandomImageScreen(
                randomImage = randomImage,
                onLoveClick = { randomImageRecipient ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(RANDOM_IMAGE_RECIPIENT, randomImageRecipient)
                    navController.popBackStack()
                },
                popBackStack = {
                    navController.popBackStack()
                },
                onRandomFoodClick = {
                    navigateToFoodDetail(FoodConstants.mockFood.id)
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
