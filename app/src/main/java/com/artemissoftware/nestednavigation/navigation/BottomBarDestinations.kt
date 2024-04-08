package com.artemissoftware.nestednavigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shuffle
import com.artemissoftware.nestednavigation.food.FoodRoute
import com.artemissoftware.nestednavigation.randomimages.RandomImageRoute
import com.artemissoftware.nestednavigation.settings.SettingsRoute

object BottomBarDestinations {

    val random = TopLevelDestination(
        route = RandomImageRoute.ImagesList.route,
        title = "Random",
        icon = Icons.Default.Shuffle,
    )

    val food = TopLevelDestination(
        route = FoodRoute.FoodList.route,
        title = "Food",
        icon = Icons.Default.Fastfood,
    )

    val settings = TopLevelDestination(
        route = SettingsRoute.Settings.route,
        title = "Settings",
        icon = Icons.Default.Settings,
    )

    val destinations = listOf(random, food, settings)
}
