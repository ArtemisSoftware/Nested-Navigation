package com.artemissoftware.nestednavigation.randomimages

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.composables.NNSkeleton
import com.artemissoftware.nestednavigation.randomimages.RandomImagesConstants.updateList
import com.artemissoftware.nestednavigation.ui.theme.randomImage1
import com.artemissoftware.nestednavigation.ui.theme.randomImage5

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomImagesListScreen(
    navigateToImage: (RandomImage) -> Unit,
    randomImageRecipient: RandomImageRecipient? = null,
) {
    var randomImages by remember {
        mutableStateOf(RandomImagesConstants.randomImages)
    }
    LaunchedEffect(key1 = randomImageRecipient){
        randomImageRecipient?.let { randomImages = updateList(it) }
    }


    NNSkeleton(
        topBar = {
            TopAppBar(
                title = { Text("Random Images", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(randomImages) { image ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navigateToImage(image)
                            },
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            Image(
                                painter = painterResource(id = image.id),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .height(200.dp)
                                    .clip(MaterialTheme.shapes.medium),
                            )

                            androidx.compose.animation.AnimatedVisibility(
                                modifier = Modifier
                                    .align(Alignment.TopStart),
                                visible = image.clicks > 0,
                                enter = fadeIn(animationSpec = tween(1000)),
                                exit = fadeOut(animationSpec = tween(1000))
                            ) {
                                Text(
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier
                                        .background(randomImage5)
                                        .padding(8.dp),
                                    text = image.clicks.toString(),
                                )
                            }

                            when(image){
                                is RandomImage.Full -> {
                                    Text(
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier = Modifier
                                            .align(Alignment.BottomEnd)
                                            .background(randomImage1)
                                            .padding(8.dp),
                                        text = "Full",
                                    )
                                }
                                else -> Unit
                            }
                        }
                    }
                }
            }
        }
    )
}
