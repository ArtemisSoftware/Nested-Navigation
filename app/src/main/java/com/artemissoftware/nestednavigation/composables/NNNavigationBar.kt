package com.artemissoftware.nestednavigation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NNNavigationBar(
//    selectedItemIndex: Int,
//    onSelect: (Int) -> Unit,
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: TopLevelDestination?,
    modifier: Modifier = Modifier,
) {
    currentDestination?.let {
        NavigationBar(
            modifier = modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.background,
            tonalElevation = 1.dp,
        ) {
            destinations.forEachIndexed { index, destination ->

                NavigationBarItem(
                    selected = currentDestination == destination,
                    onClick = { onNavigateToDestination(destination) },
                    label = {
                        Text(text = destination.title)
                    },
                    icon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = destination.title,
                        )
                    },
                )
            }
        }
    }

//    NavigationBar {
//        Constants.bottomNavigationItems.forEachIndexed { index, item ->
//            NavigationBarItem(
//                selected = selectedItemIndex == index,
//                onClick = { onSelect(index) },
//                label = {
//                    Text(text = item.title)
//                },
//                icon = {
//                    BadgedBox(
//                        badge = {
//                            if (item.badgeCount != null) {
//                                Badge {
//                                    Text(text = item.badgeCount.toString())
//                                }
//                            } else if (item.hasNews) {
//                                Badge()
//                            }
//                        },
//                    ) {
//                        Icon(
//                            imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
//                            contentDescription = item.title,
//                        )
//                    }
//                },
//            )
//        }
//    }
}

@Composable
fun NRNavigationBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: TopLevelDestination?,
) {
    currentDestination?.let {
        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.background,
            tonalElevation = 1.dp,
        ) {
            destinations.forEachIndexed { index, destination ->

//                NRNavigationBarItem(
//                    selected = currentDestination == destination,
//                    onClick = {
//                        onNavigateToDestination(destination)
//                    },
//                    icon = {
//                        Icon(
//                            painter = painterResource(id = destination.icon),
//                            contentDescription = null,
//                            modifier = Modifier.size(MaterialTheme.dimension.iconSize),
//                        )
//                    },
//                    selectedIcon = {
//                        Icon(
//                            painter = painterResource(id = destination.icon),
//                            contentDescription = null,
//                            modifier = Modifier.size(MaterialTheme.dimension.iconSize),
//                        )
//                    },
//                    label = {
//                        Text(
//                            text = stringResource(id = destination.text),
//                            style = MaterialTheme.typography.labelSmall,
//                        )
//                    },
//                )
            }
        }
    }
}
