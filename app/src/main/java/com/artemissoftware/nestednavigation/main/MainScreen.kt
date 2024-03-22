package com.artemissoftware.nestednavigation.main

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.nestednavigation.composables.topbar.SimpleLightTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {

    Scaffold(
        topBar = {
            if (navController.showTopBar()) {
                SimpleLightTopAppBar("Nested Navigation Demo")
            }
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
    ) { padding ->
        var modifier = Modifier.padding(padding)
        MainNavGraph(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarItem.Home,
        BottomBarItem.Profile,
        BottomBarItem.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (navController.showBottomBar()) {
        NavigationBar {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun NavHostController.showBottomBar(): Boolean {
    val navBackStackEntry by this.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val screens = listOf(
        BottomBarItem.Home,
        BottomBarItem.Profile,
        BottomBarItem.Settings,
    )
    return screens.any { it.route == currentDestination?.route }
}

@Composable
fun NavHostController.showTopBar(): Boolean {
    val navBackStackEntry by this.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val screens = listOf(
        BottomBarItem.Home,
        BottomBarItem.Profile,
        BottomBarItem.Settings,
    )
    return screens.any { it.route == currentDestination?.route }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
    )
}
