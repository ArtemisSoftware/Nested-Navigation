package com.artemissoftware.nestednavigation.randomimages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.composables.NNSkeleton
import kotlin.random.Random

@Composable
fun RandomImageScreen(
    popBackStack: () -> Unit,
    onRandomFoodClick: () -> Unit,
    onLoveClick: (RandomImageRecipient) -> Unit,
    randomImage: RandomImage,
) {

    val randomValue = remember {
        mutableIntStateOf(Random.nextInt(10))
    }

    NNSkeleton(
        topBar = GetTopBar(randomImage, popBackStack = popBackStack),
        content = {

            when(randomImage){
                is RandomImage.Full -> FullScreen(popBackStack = popBackStack, randomImage = randomImage)
                is RandomImage.Regular -> RegularScreen(randomImage = randomImage, onRandomFoodClick = onRandomFoodClick)
            }
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp)) {
                Button(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        onLoveClick(RandomImageRecipient(imageId = randomImage.id, randomValue.intValue))
                    },
                    content = {
                        Text(text = "Give ${randomValue.value} points")
                    },
                )
            }

        }
    )
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

@Composable
private fun RegularScreen(
    randomImage: RandomImage,
    onRandomFoodClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Button(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(24.dp),
            onClick = onRandomFoodClick,
            content = {
                Text(text = "Random Food")
            },
        )

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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun GetTopBar(randomImage: RandomImage, popBackStack: () -> Unit): @Composable() (() -> Unit?)? {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    var result: @Composable() (() -> Unit?)? = null

    val showTopBar = when(randomImage){
        is RandomImage.Full -> false
        is RandomImage.Regular -> true
    }

    if(showTopBar) {
        result = {
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
        }
    } else {
        result =  null
    }

    return result
}

@Preview
@Composable
private fun RandomImageScreenFullPreview() {
    RandomImageScreen(
        randomImage = RandomImagesConstants.randomImages.filterIsInstance<RandomImage.Full>().get(0) ,
        popBackStack = {},
        onLoveClick = {},
        onRandomFoodClick = {},
    )
}

@Preview
@Composable
private fun RandomImageScreenRegularPreview() {
    RandomImageScreen(
        randomImage = RandomImagesConstants.randomImages.filterIsInstance<RandomImage.Regular>().get(0) ,
        popBackStack = {},
        onLoveClick = {},
        onRandomFoodClick = {},
    )
}
