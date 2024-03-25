package com.artemissoftware.nestednavigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.artemissoftware.nestednavigation.home.MainRoute

sealed class BottomBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    data object Home : BottomBarItem(
        route = MainRoute.Home.route,
        title = "HOME",
        icon = Icons.Default.Home,
    )

    data object Profile : BottomBarItem(
        route = MainRoute.Profile.route,
        title = "PROFILE",
        icon = Icons.Default.Person,
    )

    data object Settings : BottomBarItem(
        route = MainRoute.Settings.route,
        title = "SETTINGS",
        icon = Icons.Default.Settings,
    )
}
