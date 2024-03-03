package com.artemissoftware.nestednavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.nestednavigation.product.productNavGraph
import com.artemissoftware.nestednavigation.ui.theme.NestedNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // RootNavigationGraph(navController = rememberNavController())

                    ProductNavHost(navController = rememberNavController())
                }
            }
        }
    }
}

@Composable
fun ProductNavHost(navController: NavHostController) {

    val BASE_ROUTE = "base"
    val PRODUCT_ROUTE = "product"

    NavHost(
        navController = navController,
        route = BASE_ROUTE,
        startDestination = PRODUCT_ROUTE,
    ) {
        productNavGraph(
            route = PRODUCT_ROUTE,
            navController = navController,
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NestedNavigationTheme {
        Greeting("Android")
    }
}
