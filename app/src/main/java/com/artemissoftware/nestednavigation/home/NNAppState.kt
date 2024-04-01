package com.artemissoftware.nestednavigation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.artemissoftware.nestednavigation.food.GalleryRoute
import com.artemissoftware.nestednavigation.food.navigateToGalleryGraph
import com.artemissoftware.nestednavigation.images.ImageRoute
import com.artemissoftware.nestednavigation.images.navigateToImagesGraph
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations.destinations
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations.home
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations.images
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations.random
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations.settings
import com.artemissoftware.nestednavigation.navigation.TopBarDestinations
import com.artemissoftware.nestednavigation.navigation.TopLevelDestination
import com.artemissoftware.nestednavigation.randomimages.RandomImageRoute
import com.artemissoftware.nestednavigation.randomimages.navigateToRandomImagesGraph
import com.artemissoftware.nestednavigation.settings.SettingsRoute
import com.artemissoftware.nestednavigation.settings.navigateToSettingsNavGraph
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

class NNAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            random.route -> random
            GalleryRoute.Gallery.route -> home
            ImageRoute.Images.route -> images
            SettingsRoute.Settings.route -> settings
            else -> null
        }

//    /**
//     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
//     * route.
//     */
    val topLevelDestinations: List<TopLevelDestination> = destinations

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.route}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination.route) {
                random.route -> navController.navigateToRandomImagesGraph(topLevelNavOptions)
                home.route -> navController.navigateToGalleryGraph(topLevelNavOptions)
                images.route -> navController.navigateToImagesGraph(topLevelNavOptions)
                settings.route -> navController.navigateToSettingsNavGraph(topLevelNavOptions)
//                search.route -> navController.navigateToSearchGraph(topLevelNavOptions)
//                bookmark.route -> navController.navigateToBookmarkGraph(topLevelNavOptions)
//                news.route -> navController.navigateToNewsGraph(topLevelNavOptions)
            }
        }
    }

    @Composable
    fun showTopBar(): Boolean {
        return currentDestination?.let {
            return TopBarDestinations.screens.any { it.route == it.route }
        } ?: run {
            true
        }
    }

    private var currentTheme = GalleryRoute.Gallery.themeType

    @Composable
    fun updateTheme(
        changeTheme: (ThemeType) -> Unit,
    ) {
        //val route = currentDestination?.route

        currentDestination?.let {
            val theme = when {

                RandomImageRoute.Image.route == it.route -> RandomImageRoute.Image.themeType
                RandomImageRoute.ImagesList.route == it.route -> RandomImageRoute.ImagesList.themeType

                GalleryRoute.Gallery.route == it.route -> GalleryRoute.Gallery.themeType
                GalleryRoute.Gallery.route == it.route -> GalleryRoute.Gallery.themeType
                GalleryRoute.Details.route == it.route -> GalleryRoute.Details.themeType
//            Red.route == route -> Red.themeType
//            Blue.route == route -> Blue.themeType
//            Green.route == route -> Green.themeType
                else -> currentTheme
            }

            currentTheme = theme
            changeTheme.invoke(theme)
        }
    }
}

@Composable
fun rememberNRAppState(
    navController: NavHostController = rememberNavController(),
): NNAppState {
    return remember(
        navController,
    ) {
        NNAppState(
            navController,
        )
    }
}
