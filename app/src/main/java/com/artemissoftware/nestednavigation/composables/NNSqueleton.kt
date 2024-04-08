package com.artemissoftware.nestednavigation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NNSkeleton(
    modifier: Modifier = Modifier,
    topBar: @Composable() (() -> Unit?)? = null,
    content: @Composable (PaddingValues) -> Unit,
    bottomBar: @Composable() ((Modifier) -> Unit?)? = null,
) {
    val bottomBarHeight = 104.0.dp
    val bottomBarHeightPx = with(LocalDensity.current) { bottomBarHeight.roundToPx().toFloat() }
    val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }

// connection to the nested scroll system and listen to the scroll
// happening inside child LazyColumn
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = bottomBarOffsetHeightPx.floatValue + delta
                bottomBarOffsetHeightPx.floatValue = newOffset.coerceIn(-bottomBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    val collapsedModifier = Modifier
        .height(bottomBarHeight)
        .offset { IntOffset(x = 0, y = -bottomBarOffsetHeightPx.floatValue.roundToInt()) }

    Scaffold(
        modifier = Modifier
            .then(modifier)
            .nestedScroll(nestedScrollConnection),
        topBar = {
            topBar?.invoke()
        },
        bottomBar = {
            bottomBar?.invoke(collapsedModifier)
        },
        content = { innerPadding ->

            val topPadding = if (topBar == null) 0.dp else innerPadding.calculateTopPadding()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = topPadding)
            ) {
                content(innerPadding)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun NNSkeletonPreview() {
    val colors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Yellow)
    NNSkeleton(
        topBar = {
            TopAppBar(
                title = { Text("TopAppBar") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                ),
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(count = 100) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(colors[it % colors.size]),
                    )
                }
            }
        },
        bottomBar = {
            NNNavigationBar(
                modifier = it,
                destinations = BottomBarDestinations.destinations,
                onNavigateToDestination = {},
                currentDestination = BottomBarDestinations.destinations[0],
            )
        },
    )
}
