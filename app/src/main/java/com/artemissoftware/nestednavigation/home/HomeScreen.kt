package com.artemissoftware.nestednavigation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.artemissoftware.nestednavigation.composables.NNNavigationBar
import com.artemissoftware.nestednavigation.composables.NNSqueleton_3
import com.artemissoftware.nestednavigation.randomimages.RANDOM_IMAGES_GRAPH
import com.artemissoftware.nestednavigation.ui.theme.ThemeType



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    appState: NNAppState = rememberNRAppState(),
    changeTheme: (ThemeType) -> Unit,
    navigateToLogin: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    appState.updateTheme(
        changeTheme = changeTheme,
    )

    NNSqueleton_3(
        content = {
            AnimatedVisibility(
                visible = appState.showTopBar(),
                enter = expandIn(),
            ) {
                MediumTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White,
                    ),
                    title = {
                        Text(
                            "Medium Top App Bar",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { appState.navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = navigateToLogin) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Login,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
            HomeNavGraph(
                navController = appState.navController,
                startGraph = RANDOM_IMAGES_GRAPH,
            )
        },
        bottomBar = {
            NNNavigationBar(
                modifier = it,
                destinations = appState.topLevelDestinations,
                currentDestination = appState.currentTopLevelDestination,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
            )
        }
    )
}

