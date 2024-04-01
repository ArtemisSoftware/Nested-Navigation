package com.artemissoftware.nestednavigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ImageAspectRatio
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shuffle
import com.artemissoftware.nestednavigation.food.GalleryRoute
import com.artemissoftware.nestednavigation.images.ImageRoute
import com.artemissoftware.nestednavigation.randomimages.RandomImageRoute
import com.artemissoftware.nestednavigation.settings.SettingsRoute

object BottomBarDestinations {

    val random = TopLevelDestination(
        route = RandomImageRoute.ImagesList.route,
        title = "Random",
        icon = Icons.Default.Shuffle,
    )

    val home = TopLevelDestination(
        route = GalleryRoute.Gallery.route,
        title = "Gallery",
        icon = Icons.Default.Home,
    )

    val images = TopLevelDestination(
        route = ImageRoute.Images.route,
        title = "Images",
        icon = Icons.Default.ImageAspectRatio,
    )

    val settings = TopLevelDestination(
        route = SettingsRoute.Settings.route,
        title = "Settings",
        icon = Icons.Default.Settings,
    )

    val destinations = listOf(random, home, images, settings)
}
