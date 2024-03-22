package com.artemissoftware.nestednavigation.images

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.R
import com.artemissoftware.nestednavigation.composables.NNScaffold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ImagesScreen(
    navigateToLandImage: () -> Unit,
    navigateToFaceImage: () -> Unit,
) {
    NNScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val image: Painter = painterResource(id = R.drawable.image_face)
            Image(
                painter = image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
            )

            Button(
                onClick = navigateToFaceImage,
                content = {
                    Image(imageVector = Icons.Outlined.Face, contentDescription = "")
                    Text(text = "Face Image")
                },
            )

            Button(
                onClick = navigateToLandImage,
                content = {
                    Image(imageVector = Icons.Outlined.Place, contentDescription = "")
                    Text(text = "Land Image")
                },
            )
        }
    }
}

@Preview
@Composable
private fun ImagesScreenPreview() {
    ImagesScreen(
        navigateToLandImage = {},
        navigateToFaceImage = {},
    )
}
