package com.artemissoftware.nestednavigation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.artemissoftware.nestednavigation.gallery.GalleryRoute

object BottomBarDestinations {

    val home = TopLevelDestination(
        route = GalleryRoute.Gallery.route,
        title = "Gallery",
        icon = Icons.Default.Home,
    )

    val destinations = listOf(home)
}
