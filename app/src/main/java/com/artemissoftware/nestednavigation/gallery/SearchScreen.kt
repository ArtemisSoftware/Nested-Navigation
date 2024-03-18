package com.artemissoftware.nestednavigation.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("This is Search Page ")
        Button(
            onClick = {
                // --navController.popBackStack("mainPage", false)
            },
        ) {
            Text("Go to Main Page")
        }
    }
}

@Preview
@Composable
private fun SearchPreview() {
    SearchScreen()
}
