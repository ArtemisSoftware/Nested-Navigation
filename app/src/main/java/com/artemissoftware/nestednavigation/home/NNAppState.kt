package com.artemissoftware.nestednavigation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.artemissoftware.nestednavigation.food.FoodRoute
import com.artemissoftware.nestednavigation.food.navigateToFoodGraph
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations.destinations
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations.food
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations.random
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations.settings
import com.artemissoftware.nestednavigation.navigation.TopLevelDestination
import com.artemissoftware.nestednavigation.randomimages.navigateToRandomImagesGraph
import com.artemissoftware.nestednavigation.settings.SettingsRoute
import com.artemissoftware.nestednavigation.settings.navigateToSettingsNavGraph
import com.artemissoftware.nestednavigation.ui.theme.ThemeType
import com.artemissoftware.nestednavigation.util.extensions.toBaseDestination

class NNAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            random.route -> random
            food.route -> food
            settings.route -> settings
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
                food.route -> navController.navigateToFoodGraph(topLevelNavOptions)
                settings.route -> navController.navigateToSettingsNavGraph(topLevelNavOptions)
            }
        }
    }

    @Composable
    fun showTopBar(): Boolean {
        val result = currentDestination?.let {
            val show = when (it.route) {
                FoodRoute.Detail.getRouteInFull() -> true
                else -> false
            }

            show
        } ?: run {
            false
        }

        return result
    }

    private var currentTheme = FoodRoute.FoodList.themeType

    @Composable
    fun updateTheme(
        changeTheme: (ThemeType) -> Unit,
    ) {
        currentDestination?.let { navDestination ->
            LaunchedEffect(key1 = navDestination){

                val theme = navDestination.toBaseDestination()?.themeType ?: currentTheme

                currentTheme = theme
                changeTheme.invoke(theme)
            }
        }
    }

    @Composable
    fun getTitle() = currentDestination?.toBaseDestination()?.title ?: ""
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
