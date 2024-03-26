package com.artemissoftware.nestednavigation.product

import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson

@Composable
fun ProductListScreen(navController: NavController) {
    val product = ProductParameters(17)
    val device = Device("17", "device2")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Text(text = "My route v1:" + Route.ProductList.link)
            Text(text = "My route v1:" + NavRouteName.product_details + "/" + ProductParametersType.toNVString(product))



            Button(
                onClick = {

                    val productLink = NavRouteName.product_details + "/" + ProductParametersType.toNVString(product) // .toString() //serializeAsValue(product)
                    println("productLink : $productLink")
                    navController.navigate(productLink)
                },
            ) {
                Text("Product List")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "My route v2:" + RouteV2.ProductDetails.getRouteInFull())
            Text(text = "My route v2:" + RouteV2.ProductDetails.withCustomArgs(ProductParameters(18)))
            //Text(text = "My route v2:" + RouteV2.ProductDetails.argumentsAlt)

            Button(
                onClick = {
                    val productLink = RouteV2.ProductDetails.withCustomArgs(ProductParameters(18))
                    println("productLink : $productLink")
                    //navController.navigate(productLink)
                    navController.details(ProductParameters(18))
                },
            ) {
                Text("Product List")
            }

            Button(
                onClick = {
                    val device = DeviceV2("1", "My device")
                    val json = Uri.encode(Gson().toJson(device))
                    navController.navigate("details/$json")
                },
            ) {
                Text("Device")
            }
        }
    }
}
