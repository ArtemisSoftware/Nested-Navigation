package com.artemissoftware.nestednavigation.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    //alternateStatusBarColor: (Color) -> Unit,
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier,
            text = "Current status bar color",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold,
        )

                Button(
                    onClick = {
                        //alternateStatusBarColor(Color.Transparent)
                    },
                    content = {
                        Text(text = "Change status bar color")
                    },
                )

        Text(
            modifier = Modifier,
            text = "Current top bar color",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold,
        )

                    Button(
                        onClick = {
                            //alternateStatusBarColor(Color.Transparent)
                        },
                        content = {
                            Text(text = "Change top bar color")
                        },
                    )
    }

//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Settings") },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Green),
//            )
//        },
//        floatingActionButtonPosition = FabPosition.End,
//        floatingActionButton = {
//            FloatingActionButton(onClick = {}) {
//                Text("X")
//            }
//        },
//        content = {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(it),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//                Button(
//                    onClick = {
//                        alternateStatusBarColor(Color.Transparent)
//                    },
//                    content = {
//                        Text(text = "Change status bar color to Transparent")
//                    },
//                )
//            }
//        },
//        bottomBar = {
//            BottomAppBar(
//                containerColor = Color.Green,
//                content = {
//                    Text("BottomAppBar")
//                },
//
//            )
//        },
//    )
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen(
        //alternateStatusBarColor = {},
    )
}
