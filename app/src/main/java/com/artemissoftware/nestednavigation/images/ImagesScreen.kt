package com.artemissoftware.nestednavigation.images

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ImagesScreen(
    navigateToLandImage: () -> Unit,
    navigateToFaceImage: () -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
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
