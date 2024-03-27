package com.artemissoftware.nestednavigation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.nestednavigation.R
import com.artemissoftware.nestednavigation.navigation.BottomBarDestinations

@Composable
fun NNSqueleton() {
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
