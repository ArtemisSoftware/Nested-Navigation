package com.artemissoftware.nestednavigation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.food.FoodRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSelector(
    route: String? = null,
    navigateToSearch: () -> Unit,
    popback: () -> Unit,
) {
    when {
        FoodRoute.FoodList.route == route || FoodRoute.Detail.route == route -> {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = getText(route),
                        color = Color.White,
                    )
                },
                navigationIcon = {
                    navigationIcon(route, popback)
                },
                actions = {
                    actions(route = route, navigateToSearch = navigateToSearch)
                },
            )
        }
        else -> Unit
    }
}

private fun getText(route: String? = null) = when {
    FoodRoute.FoodList.route == route -> {
        "Gallery"
    }
    FoodRoute.Detail.route == route -> {
        "Details"
    }
    else -> ""
}

@Composable
private fun navigationIcon(
    route: String? = null,
    popback: () -> Unit,
) {
    when {
        FoodRoute.FoodList.route == route -> {
            Unit
        }
        FoodRoute.Detail.route == route -> {
            IconButton(
                onClick = popback,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        }
        else -> Unit
    }
}

@Composable
private fun RowScope.actions(
    route: String? = null,
    navigateToSearch: () -> Unit,
) {
    when {
        FoodRoute.FoodList.route == route -> {
            IconButton(
                onClick = navigateToSearch,
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    modifier = Modifier.padding(end = 8.dp),
                    contentDescription = null,
                )
            }
        }
        FoodRoute.Detail.route == route -> {
            IconButton(
                onClick = navigateToSearch,
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                )
            }
        }
        else -> Unit
    }
}
