package com.artemissoftware.nestednavigation.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.nestednavigation.R

@Composable
fun ImageLandScreen(
    navigateToFaceImage: () -> Unit,
) {
    val image: Painter = painterResource(id = R.drawable.image_land)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Button(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = navigateToFaceImage,
            content = {
                Image(imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = "")
                Text(text = "Face Image")
            },
        )
    }
}

@Preview
@Composable
private fun ImageLandScreenPreview() {
    ImageLandScreen(
        navigateToFaceImage = {},
    )
}
