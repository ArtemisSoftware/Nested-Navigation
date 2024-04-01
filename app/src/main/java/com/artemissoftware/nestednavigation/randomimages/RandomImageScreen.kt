package com.artemissoftware.nestednavigation.randomimages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.composables.NNSqueleton_3
import com.artemissoftware.nestednavigation.ui.theme.randomImage2

@Composable
fun RandomImageScreen(
    popBackStack: () -> Unit,
    randomImage: RandomImage,
) {

    when(randomImage){
        is RandomImage.Full -> FullScreen(popBackStack = popBackStack, randomImage = randomImage)
        is RandomImage.Regular -> RegularScreen(popBackStack = popBackStack, randomImage = randomImage)
    }
}

@Composable
private fun FullScreen(
    randomImage: RandomImage,
    popBackStack: () -> Unit,
) {
    val image: Painter = painterResource(id = randomImage.id)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RegularScreen(
    popBackStack: () -> Unit,
    randomImage: RandomImage,
) {
    NNSqueleton_3(

        topBar = {
            TopAppBar(
                title = { Text("Random Image", color = Color.White) },
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
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = randomImage.id),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clip(MaterialTheme.shapes.medium),
                )
            }
        }
    )
}
