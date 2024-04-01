package com.artemissoftware.nestednavigation.food

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
fun SearchScreen(
    argument: String?,
    popBackStack: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Your search parameter is:$argument ")
        Button(
            onClick = popBackStack,
        ) {
            Text("Go to Main Page")
        }
    }
}

@Preview
@Composable
private fun SearchPreview() {
    SearchScreen(
        popBackStack = {},
        argument = "Berries",
    )
}
