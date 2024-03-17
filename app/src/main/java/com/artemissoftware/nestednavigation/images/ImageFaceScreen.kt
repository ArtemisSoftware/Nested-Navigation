package com.artemissoftware.nestednavigation.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.nestednavigation.R

@Composable
fun ImageFaceScreen() {
    val image: Painter = painterResource(id = R.drawable.image_face)
    Image(
        painter = image,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize(),
    )
}

@Preview
@Composable
private fun ImageFaceScreenPreview() {
    ImageFaceScreen()
}
