package com.artemissoftware.nestednavigation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.artemissoftware.nestednavigation.food.foodNavGraph
import com.artemissoftware.nestednavigation.food.navigateToFoodDetail
import com.artemissoftware.nestednavigation.randomimages.randomImagesNavGraph
import com.artemissoftware.nestednavigation.settings.settingsNavGraph

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

        randomImagesNavGraph(
            navController = navController,
            navigateToFoodDetail = {
                navController.navigateToFoodDetail(it)
            }
        )
        foodNavGraph(navController)
        settingsNavGraph(navController, {})
    }
}

// TODO: provisório

sealed class MainRoute(val route: String) {
    data object Home : MainRoute(route = "home")
    data object Profile : MainRoute(route = "splash")
    data object Settings : MainRoute(route = "settings")
}
