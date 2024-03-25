package com.artemissoftware.nestednavigation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artemissoftware.nestednavigation.gallery.GalleryRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSelector(
    route: String? = null,
    navigateToSearch: () -> Unit,
) {
    when {
        GalleryRoute.Gallery.route == route -> {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Gallery")
                },
                actions = {
                    IconButton(
                        onClick = navigateToSearch,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            modifier = Modifier.padding(end = 8.dp),
                            contentDescription = null,
                        )
                    }
                },
            )
        }
        GalleryRoute.Details.route == route -> {
            TopAppBar(
                title = {
                    Text(
                        "Details",
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { /*popBackStack*/ },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = navigateToSearch,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = null,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
        else -> Unit
    }
}
