package com.artemissoftware.nestednavigation.compliance.navigation

//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.composable
//import androidx.navigation.navigation
//import com.artemissoftware.nestednavigation.composables.GenericScreen
//import com.artemissoftware.nestednavigation.navigation.Graph
//
//fun NavGraphBuilder.complianceNavGraph(navController: NavHostController) {
//
//    navigation(
//        route = Graph.COMPLIANCE,
//        startDestination = ComplianceScreen.Compliance1.route,
//    ) {
//        composable(route = ComplianceScreen.Compliance1.route) {
//            GenericScreen(name = ComplianceScreen.Compliance1.route) {
//                navController.navigate(ComplianceScreen.Compliance2.route)
//            }
//        }
//        composable(route = ComplianceScreen.Compliance2.route) {
//            GenericScreen(name = ComplianceScreen.Compliance2.route) {
//                navController.navigate(ComplianceScreen.Compliance3.route)
//            }
//        }
//        composable(route = ComplianceScreen.Compliance3.route) {
//            GenericScreen(name = ComplianceScreen.Compliance3.route) {
//
//                navController.popBackStack(
//                    route = AuthScreen.Login.route,
//                    inclusive = false
//                )
//            }
//        }
//    }
//}
//
//sealed class ComplianceScreen(val route: String) {
//    data object Compliance1 : ComplianceScreen(route = "Compliance1")
//    data object Compliance2 : ComplianceScreen(route = "Compliance2")
//    data object Compliance3 : ComplianceScreen(route = "Compliance3")
//}
