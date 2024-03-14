package com.artemissoftware.nestednavigation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.composables.NNScaffold
import com.artemissoftware.nestednavigation.composables.topbar.CenterAlignedTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListScreen() {
    NNScaffold(
        topBar = {
            CenterAlignedTopBar(title = "Item list")
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(15) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        },
    )
}
