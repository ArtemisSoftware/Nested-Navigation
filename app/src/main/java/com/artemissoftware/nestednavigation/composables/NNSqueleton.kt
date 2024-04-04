package com.artemissoftware.nestednavigation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.R
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NNSqueleton_3(
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
@Composable
fun NNSqueleton() {
    val colors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Magenta, Color.Yellow)

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

    Scaffold(
        Modifier.nestedScroll(nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("TopAppBar") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                ),
            )
        },
        bottomBar = {
            NNNavigationBar(
                modifier = Modifier
                    .height(bottomBarHeight)
                    .offset {
                        IntOffset(
                            x = 0,
                            y = -bottomBarOffsetHeightPx.floatValue.roundToInt()
                        )
                    },
                destinations = BottomBarDestinations.destinations,
                onNavigateToDestination = {},
                currentDestination = BottomBarDestinations.destinations[0],
            )

//            BottomAppBar(
//                modifier = Modifier
//                    //.height(boH)
//                    //.offset { IntOffset(x = 0, y = -bottomBarOffsetHeightPx.value.roundToInt()) },
//            ) {
//                IconButton(
//                    onClick = {
//                        // coroutineScope.launch { scaffoldState.drawerState.open() }
//                    },
//                ) {
//                    Icon(Icons.Filled.Menu, contentDescription = "Localized description")
//                }
//                IconButton(
//                    onClick = {
//                        // coroutineScope.launch { scaffoldState.drawerState.open() }
//                    },
//                ) {
//                    Icon(Icons.Filled.Menu, contentDescription = "Localized description")
//                }
//            }
        },

        content = { innerPadding ->

            val topPadding = innerPadding.calculateTopPadding()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = topPadding),
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
    )
}

@Composable
fun NNSqueleton_v2() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val image: Painter = painterResource(id = R.drawable.image_10)
        Image(
            painter = image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        NNNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            destinations = BottomBarDestinations.destinations,
            onNavigateToDestination = {},
            currentDestination = BottomBarDestinations.destinations[0],
        )
    }
}

@Preview
@Composable
fun NNSqueletonPreview() {
    NNSqueleton()
}
