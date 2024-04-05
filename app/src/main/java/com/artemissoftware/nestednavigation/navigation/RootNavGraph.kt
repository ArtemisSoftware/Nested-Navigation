package com.artemissoftware.nestednavigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artemissoftware.nestednavigation.authentication.authNavGraph
import com.artemissoftware.nestednavigation.authentication.navigateToLogin
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
        startDestination = startDestination,
    ) {
        authNavGraph(navController = navController)

        composable(route = HOME_GRAPH) {
            HomeScreen(
                changeTheme = changeTheme,
                navigateToLogin = {
                    navController.navigateToLogin()
                }
            )
        }

        //randomImagesNavGraph(navController = navController)

        //imagesNavGraph(navController = navController)
    }
}

