package com.artemissoftware.nestednavigation

import android.annotation.SuppressLint
import android.app.Activity
//import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.artemissoftware.nestednavigation.composables.NNSqueleton
import com.artemissoftware.nestednavigation.home.HOME_GRAPH
import com.artemissoftware.nestednavigation.images.IMAGES_GRAPH
import com.artemissoftware.nestednavigation.images.ImageFaceScreen
import com.artemissoftware.nestednavigation.navigation.RootNavigationGraph
import com.artemissoftware.nestednavigation.product.productNavGraph
import com.artemissoftware.nestednavigation.ui.theme.NestedNavigationTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        /*
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ),
        )
*/
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            ),
        )
        super.onCreate(savedInstanceState)
        setContent {
            val state = mainViewModel.state.collectAsState().value
            val navController = rememberNavController()
/*
            DisposableEffect(navController) {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_CREATE) {
                        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
                            mainViewModel.onTriggerEvent(MainEvent.ThemeChange(route = destination.route))
                        }
                        navController.addOnDestinationChangedListener(listener)

                        onDispose {
                            navController.removeOnDestinationChangedListener(listener)
                        }
                    }
                }

                lifecycle.addObserver(observer)

                onDispose {
                    lifecycle.removeObserver(observer)
                }
            }
*/

            NestedNavigationTheme(themeType = state.theme) {
                //setStatusBarColor(color = Color.Transparent)
                //ImageFaceScreen()

//                var statusBarColor by remember {
//                    mutableStateOf(Color.Yellow)
//                }
//                setStatusBarColor(color = statusBarColor)
                //setStatusBarColor(color = Color.Transparent)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NNSqueleton()

//                    RootNavigationGraph(
//                        navController = navController,
//                        startDestination = HOME_GRAPH,
//                        changeTheme = {
//                            mainViewModel.onTriggerEvent(MainEvent.Theme_Change(it))
//                        },
//                    )

                }

            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun setStatusBarColor(color: Color) {
    val view = LocalView.current

    if (!view.isInEditMode) {
        LaunchedEffect(key1 = color) {
            val window = (view.context as Activity).window
            window.statusBarColor = android.graphics.Color.TRANSPARENT
            //window.navigationBarColor = color.toArgb()
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
