package com.artemissoftware.nestednavigation.home

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.artemissoftware.nestednavigation.composables.NNNavigationBar
import com.artemissoftware.nestednavigation.composables.NNScaffold
import com.artemissoftware.nestednavigation.composables.NNSqueleton_3
import com.artemissoftware.nestednavigation.food.GALLERY_GRAPH
import com.artemissoftware.nestednavigation.food.navigateToGallerySearch
import com.artemissoftware.nestednavigation.navigation.TopBarSelector
import com.artemissoftware.nestednavigation.randomimages.RANDOM_IMAGES_GRAPH
import com.artemissoftware.nestednavigation.ui.theme.ThemeType



@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreenAlternative(
    appState: NNAppState = rememberNRAppState(),
    changeTheme: (ThemeType) -> Unit,
) {
    appState.updateTheme(
        changeTheme = changeTheme,
    )

    NNSqueleton_3(
        topBar = null,
        content = {
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



@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    appState: NNAppState = rememberNRAppState(),
    changeTheme: (ThemeType) -> Unit,
) {
    appState.updateTheme(
        changeTheme = changeTheme,
    )

    NNScaffold(
        topBar = {
            TopBarSelector(
                route = appState.currentDestination?.route,
                navigateToSearch = {
                    appState.navController.navigateToGallerySearch()
                },
                popback = {
                    appState.navController.popBackStack()
                },
            )

//            if (appState.showTopBar()) {
//                SimpleLightTopAppBar("Nested Navigation Demo")
//            }
        },
        content = {
            HomeNavGraph(
                navController = appState.navController,
                startGraph = GALLERY_GRAPH,
            )
        },
        bottomBar = {
            NNNavigationBar(
                destinations = appState.topLevelDestinations,
                currentDestination = appState.currentTopLevelDestination,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
            )
        },
    )

//    Scaffold(
//        topBar = {
// //            if (navController.showTopBar()) {
// //                SimpleLightTopAppBar("Nested Navigation Demo")
// //            }
//        },
//        bottomBar = {
//             BottomBar(navController = navController)
//        },
//    ) { padding ->
//        var modifier = Modifier.padding(padding)
//        // HomeNavGraph(navController = navController)
//    }
}

// @OptIn(ExperimentalMaterial3Api::class)
// @Composable
// fun HomeScreen() {
//    var selectedItemIndex by rememberSaveable {
//        mutableIntStateOf(0)
//    }
//
//    NNScaffold(
//        bottomBar = {
//            NNNavigationBar(
//                selectedItemIndex = selectedItemIndex,
//                onSelect = { selectedItemIndex = it },
//            )
//        },
//        content = {
//            ItemListScreen()
//        },
//    )
//
// //
// //    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
// //
// //    Scaffold(
// //        modifier = Modifier
// //            .fillMaxSize()
// //            .nestedScroll(scrollBehavior.nestedScrollConnection),
// //        topBar = {
// //            CenterAlignedTopBar(title = "Home", scrollBehavior = scrollBehavior)
// //        },
// //        bottomBar = {
// //            NavigationBar {
// //                Constants.bottomNavigationItems.forEachIndexed { index, item ->
// //                    NavigationBarItem(
// //                        selected = selectedItemIndex == index,
// //                        onClick = { selectedItemIndex = index },
// //                        label = {
// //                            Text(text = item.title)
// //                        },
// //                        icon = {
// //                            BadgedBox(
// //                                badge = {
// //                                    if (item.badgeCount != null) {
// //                                        Badge {
// //                                            Text(text = item.badgeCount.toString())
// //                                        }
// //                                    } else if (item.hasNews) {
// //                                        Badge()
// //                                    }
// //                                },
// //                            ) {
// //                                Icon(
// //                                    imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
// //                                    contentDescription = item.title,
// //                                )
// //                            }
// //                        },
// //                    )
// //                }
// //            }
// //        },
// //    ) { values ->
// //
// //        LazyColumn(
// //            modifier = Modifier
// //                .fillMaxSize()
// //                .padding(values),
// //        ) {
// //            items(100) {
// //                Text(
// //                    text = "Item $it",
// //                    modifier = Modifier.padding(16.dp),
// //                )
// //            }
// //        }
// //    }
// }
