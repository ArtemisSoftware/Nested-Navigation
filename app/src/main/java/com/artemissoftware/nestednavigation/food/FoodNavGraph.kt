package com.artemissoftware.nestednavigation.food

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.artemissoftware.nestednavigation.navigation.BaseDestination
import com.artemissoftware.nestednavigation.navigation.NavArguments
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

const val FOOD_GRAPH = "food_graph"

fun NavController.navigateToFoodGraph(navOptions: NavOptions) = navigate(FOOD_GRAPH, navOptions)

fun NavController.navigateToGallerySearch() = navigate(FoodRoute.Search.route)

fun NavController.navigateToFoodDetail(foodId: Int) = navigate(FoodRoute.Detail.withCustomArgs(foodId))

fun NavGraphBuilder.foodNavGraph(
    navController: NavController,
) {
    navigation(
        route = FOOD_GRAPH,
        startDestination = FoodRoute.FoodList.route,
    ) {
        composable(
            route = FoodRoute.FoodList.getRouteInFull(),
            arguments = FoodRoute.FoodList.arguments
        ) {
            FoodListScreen(
                navigateToDetails = {
                    navController.navigate(FoodRoute.Detail.withCustomArgs(it))
                },
            )
        }

        composable(
            route = FoodRoute.Detail.getRouteInFull(),
            arguments = FoodRoute.Detail.arguments,
        ) { backStackEntry ->
            val foodId = backStackEntry.arguments?.getInt(NavArguments.FOOD_ID)
            FoodDetailsScreen(
                foodId = foodId,
                onNextFood = {
                    navController.navigate(FoodRoute.Detail.withCustomArgs(it))
                },
            )
        }

        composable(
            route = FoodRoute.Search.route,
            arguments = FoodRoute.Search.arguments,
        ) { backStackEntry ->

            val argument = backStackEntry.arguments?.getString("args")

            SearchScreen(
                argument = argument,
                popBackStack = {
                    navController.popBackStack(FoodRoute.FoodList.route, false)
                },
            )
        }
    }
}

sealed class FoodRoute(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    themeType: ThemeType = ThemeType.FOOD,
    title: String = "",
) : BaseDestination(
    route = route,
    customArguments = arguments,
    themeType = themeType,
    title = title,
) {
    data object FoodList : FoodRoute("food")

    data object Detail : FoodRoute(
        route = "food_detail",
        title = "Food Detail",
        arguments = listOf(
            navArgument(
                name = NavArguments.FOOD_ID,
            ) {
                type = NavType.IntType
            },
        ),
    )

    data object Search : FoodRoute(
        route = "search/{args}",
        arguments = listOf(navArgument("args") { type = NavType.StringType }),
    )
}
