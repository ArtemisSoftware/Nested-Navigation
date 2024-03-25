package com.artemissoftware.nestednavigation.authentication

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.artemissoftware.nestednavigation.home.HOME_GRAPH

const val AUTHENTICATION_GRAPH = "auth_graph"

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AUTHENTICATION_GRAPH,
        startDestination = AuthScreen.Splash.route,
    ) {
        composable(route = AuthScreen.Splash.route) {
            SplashScreen(
                navigateToLogin = {
                    navController.navigate(AuthScreen.Login.route) {
                        popUpTo(AuthScreen.Splash.route) { inclusive = true }
                    }
                },
            )
        }

        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                navigateToLogin = {
                    navController.navigate(HOME_GRAPH) {
                        popUpTo(AuthScreen.Login.route) { inclusive = true }
                    }
                },
                navigateToSignUp = { navController.navigate(AuthScreen.SignUp.route) },
                navigateToForgotPassword = {
                    // Navigate to Forgot Password screen
                    navController.navigate(AuthScreen.Forgot.route)
                },
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            RegisterScreen(
                popBackStack = {
                    navController.popBackStack()
                },
                navigateToSignUp = {
                    navController.navigate(HOME_GRAPH) {
                        popUpTo(AuthScreen.SignUp.route) { inclusive = true }
                    }
                },
            )
        }
        composable(route = AuthScreen.Forgot.route) {
            ForgotPasswordScreen(
                popBackStack = {
                    navController.popBackStack()
                },
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Splash : AuthScreen(route = "splash")
    data object Login : AuthScreen(route = "login")
    data object SignUp : AuthScreen(route = "sign_up")
    data object Forgot : AuthScreen(route = "forgot")
}
