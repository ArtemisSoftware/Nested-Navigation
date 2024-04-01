package com.artemissoftware.nestednavigation.food

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.composables.NNScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    id: Int?,
    popBackStack: () -> Unit,
) {
    val gallery = remember {
        MockData.galleries.find { it.id == id } ?: MockData.galleries[0]
    }

    NNScaffold(
        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        "Details",
//                        fontSize = 18.sp,
//                        overflow = TextOverflow.Ellipsis,
//                    )
//                },
//                navigationIcon = {
//                    IconButton(
//                        onClick = popBackStack,
//                    ) {
//                        Icon(
//                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                            contentDescription = null,
//                        )
//                    }
//                },
//                actions = {
//                    IconButton(
//                        onClick = {
//                            // --navController.navigate("search")
//                        },
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Settings,
//                            contentDescription = null,
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                ),
//            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp),
        ) {
            Image(
                painter = painterResource(id = gallery.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = gallery.title,
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = gallery.subtitle,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun DetailsPreview() {
    DetailsScreen(id = 1, popBackStack = {})
}
