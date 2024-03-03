package com.artemissoftware.nestednavigation.authentication.navigation

//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.composable
//import androidx.navigation.navOptions
//import androidx.navigation.navigation
//import com.artemissoftware.nestednavigation.authentication.LoginScreen
//import com.artemissoftware.nestednavigation.compliance.navigation.ComplianceScreen
//import com.artemissoftware.nestednavigation.composables.GenericScreen
//import com.artemissoftware.nestednavigation.navigation.Graph
//
//fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
//    navigation(
//        route = Graph.AUTHENTICATION,
//        startDestination = AuthScreen.Login.route,
//    ) {
//        composable(route = AuthScreen.Login.route) {
//            LoginScreen(
//                onClick = {
//                    navController.popBackStack()
//                    navController.navigate(Graph.HOME)
//                },
//                onSignUpClick = {
//                    navController.navigate(AuthScreen.SignUp.route)
//                },
//                onForgotClick = {
//                    navController.navigate(AuthScreen.Forgot.route)
//                },
//                checkFullCompliance = {
//                    navController.navigate(Graph.COMPLIANCE)
//                },
//                checkCompliance2 = {
//                    navController.navigate(ComplianceScreen.Compliance2.route)
//                },
//            )
//        }
//        composable(route = AuthScreen.SignUp.route) {
//            GenericScreen(name = AuthScreen.SignUp.route) {}
//        }
//        composable(route = AuthScreen.Forgot.route) {
//            GenericScreen(name = AuthScreen.Forgot.route) {}
//        }
//    }
//}
//
//sealed class AuthScreen(val route: String) {
//    data object Login : AuthScreen(route = "LOGIN")
//    data object SignUp : AuthScreen(route = "SIGN_UP")
//    data object Forgot : AuthScreen(route = "FORGOT")
//}
