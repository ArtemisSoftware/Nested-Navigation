package com.artemissoftware.nestednavigation.authentication

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.artemissoftware.nestednavigation.food.FOOD_GRAPH
import com.artemissoftware.nestednavigation.food.FoodRoute
import com.artemissoftware.nestednavigation.home.HOME_GRAPH
import com.artemissoftware.nestednavigation.product.BaseDestination
import com.artemissoftware.nestednavigation.product.NavArguments
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

const val AUTHENTICATION_GRAPH = "auth_graph"

fun NavController.navigateToAuthGraph(navOptions: NavOptions) = navigate(AUTHENTICATION_GRAPH, navOptions)

fun NavController.navigateToLogin() = navigate(AuthRoute.SignUp.getRouteInFull())

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AUTHENTICATION_GRAPH,
        startDestination = AuthRoute.Login.route,
    ) {
//        composable(route = AuthScreen.Splash.route) {
//            SplashScreen(
//                navigateToLogin = {
//                    navController.navigate(AuthScreen.Login.route) {
//                        popUpTo(AuthScreen.Splash.route) { inclusive = true }
//                    }
//                },
//            )
//        }

        composable(route = AuthRoute.Login.route) {
            LoginScreen(
                navigateToLogin = {
                    navController.navigate(HOME_GRAPH) {
                        popUpTo(AuthRoute.Login.route) { inclusive = true }
                    }
                },
                navigateToSignUp = { navController.navigate(AuthRoute.SignUp.route) },
                navigateToForgotPassword = {
                    navController.navigate(AuthRoute.Forgot.route)
                },
            )
        }
        composable(route = AuthRoute.SignUp.route) {
            RegisterScreen(
                popBackStack = {
                    navController.popBackStack()
                },
                navigateToSignUp = {
                    navController.navigate(HOME_GRAPH) {
                        popUpTo(AuthRoute.SignUp.route) { inclusive = true }
                    }
                },
            )
        }
        composable(route = AuthRoute.Forgot.route) {
            ForgotPasswordScreen(
                popBackStack = {
                    navController.popBackStack()
                },
            )
        }
    }
}


sealed class AuthRoute(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    themeType: ThemeType = ThemeType.AUTHENTICATION,
) : BaseDestination(
    route = route,
    customArguments = arguments,
    themeType = themeType,
) {
    data object Splash : AuthRoute(route = "splash")
    data object Login : AuthRoute(route = "login")
    data object SignUp : AuthRoute(route = "sign_up")
    data object Forgot : AuthRoute(route = "forgot")
}
