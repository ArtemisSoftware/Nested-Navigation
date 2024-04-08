package com.artemissoftware.nestednavigation.settings

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val SETTINGS_GRAPH = "settings_graph"

fun NavController.navigateToSettingsNavGraph(navOptions: NavOptions) = navigate(SETTINGS_GRAPH, navOptions)

fun NavGraphBuilder.settingsNavGraph(
    navController: NavController,
    alternateStatusBarColor: (Color) -> Unit,
) {
    navigation(
        route = SETTINGS_GRAPH,
        startDestination = SettingsRoute.Settings.route,
    ) {
        composable(route = SettingsRoute.Settings.route) {
            SettingsScreen(
                //alternateStatusBarColor = alternateStatusBarColor,
            )
        }
    }
}

sealed class SettingsRoute(val route: String) {
    data object Settings : SettingsRoute(route = "settings")
}
