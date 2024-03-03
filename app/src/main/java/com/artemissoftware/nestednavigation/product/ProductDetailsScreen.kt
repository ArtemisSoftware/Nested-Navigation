package com.artemissoftware.nestednavigation.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

@Composable
fun ProductDetailsScreen(
    navBackStackEntry: NavBackStackEntry,
    navController: NavController,
) {
    val arguments = navBackStackEntry.arguments
    val params = arguments?.getString(NavArguments.product_details_parameters)

    val paramsData = params?.let {
        ProductParametersType.parseValue(it)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Text(text = "My route v1:" + Route.ProductDetails.link)
            Text(text = "Result:" + paramsData)

            Button(
                onClick = {
                    navController.navigate(Route.ProductList.link)
                },
            ) {
                val productId = paramsData?.id ?: ""
                Text("Product Details : $productId")
            }
        }
    }
}
