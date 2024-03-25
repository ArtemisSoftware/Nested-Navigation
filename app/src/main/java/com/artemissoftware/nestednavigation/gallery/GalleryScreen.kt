package com.artemissoftware.nestednavigation.gallery

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.MockData.galleries
import com.artemissoftware.nestednavigation.composables.NNScaffold
import com.artemissoftware.nestednavigation.models.Gallery
import com.artemissoftware.nestednavigation.ui.theme.NestedNavigationTheme
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    navigateToSearch: () -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    NNScaffold(
        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                ),
//                title = {
//                    Text("Gallery")
//                },
//                actions = {
//                    IconButton(
//                        onClick = navigateToSearch,
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Search,
//                            modifier = Modifier.padding(end = 8.dp),
//                            contentDescription = null,
//                        )
//                    }
//                },
//            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            items(galleries) { gallery ->
                GalleryCard(
                    gallery = gallery,
                    onVideoClick = {
                        navigateToDetails(gallery.id)
                        // --navController.navigate("detailsPage")
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
private fun GalleryCard(
    gallery: Gallery,
    onVideoClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { onVideoClick() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = gallery.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = gallery.subtitle,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview
@Composable
private fun GalleryScreenPreview() {
    NestedNavigationTheme(themeType = ThemeType.GALLERY) {
        GalleryScreen(
            navigateToSearch = {},
            navigateToDetails = {},
        )
    }
}
