package com.artemissoftware.nestednavigation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
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

@Composable
fun NNNavigationBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: TopLevelDestination?,
    modifier: Modifier = Modifier,
) {

    AnimatedVisibility(
        visible = isVisible(destinations, currentDestination),
        enter = expandIn(),
        exit = fadeOut() + shrinkHorizontally()
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
    }
}

private fun isVisible(destinations: List<TopLevelDestination>, currentDestination: TopLevelDestination?): Boolean{
    return destinations.any { it.route == currentDestination?.route }
}

