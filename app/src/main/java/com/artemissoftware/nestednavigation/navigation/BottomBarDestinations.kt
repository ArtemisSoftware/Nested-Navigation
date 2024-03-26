package com.artemissoftware.nestednavigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ImageAspectRatio
import androidx.compose.material.icons.filled.Settings
import com.artemissoftware.nestednavigation.gallery.GalleryRoute
import com.artemissoftware.nestednavigation.images.ImageRoute
import com.artemissoftware.nestednavigation.settings.SettingsRoute

object BottomBarDestinations {

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

    val destinations = listOf(home, images, settings)
}
