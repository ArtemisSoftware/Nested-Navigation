package com.artemissoftware.nestednavigation.randomimages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RandomImageFullScreen(
    randomImage: RandomImage,
    popBackStack: () -> Unit,
) {
    val image: Painter = painterResource(id = randomImage.imageId)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(vertical = 40.dp)
                .padding(end = 20.dp),
            onClick = popBackStack,
        ) {
            Icon(
                tint = Color.White,
                imageVector = Icons.Default.Close,
                contentDescription = null,
            )
        }
    }
}
