package com.artemissoftware.nestednavigation.randomimages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.artemissoftware.nestednavigation.composables.NNSqueleton_3
import com.artemissoftware.nestednavigation.ui.theme.randomImage2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomImageScreen(
    popBackStack: () -> Unit,
) {
    NNSqueleton_3(
        topBar = {
            TopAppBar(
                title = { Text("Random Images", color = Color.White) },
                navigationIcon = {
                    IconButton(
                        onClick = popBackStack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = randomImage2,
                ),
            )
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = RandomImagesConstants.randomImages[1].imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(MaterialTheme.shapes.medium),
            )
        }
    }
}
