package com.artemissoftware.nestednavigation.product

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation

fun NavController.details(productParameters: ProductParameters) = navigate(RouteV2.ProductDetails.withCustomArgs(productParameters))

fun NavGraphBuilder.productNavGraph(
    route: String,
    navController: NavController,
) {
    navigation(
        route = route,
        startDestination = Route.ProductList.link,
    ) {
        composable(route = Route.ProductList.link) {
            ProductListScreen(navController)
        }
        composable(
            route = Route.ProductDetails.link,
            arguments = Route.ProductDetails.arguments,
        ) { navBackStackEntry ->
            ProductDetailsScreen(navBackStackEntry, navController)
        }
        composable(
            route = RouteV2.ProductDetails.getRouteInFull(),
            arguments = RouteV2.ProductDetails.arguments,
        ) { navBackStackEntry ->
            ProductDetailsScreen(navBackStackEntry, navController)
        }
        composable(
            route = "details/{device}",
            arguments = listOf(
                navArgument("device") {
                    type = AssetParamTypeV2()
                }
            )
        ) {
            val device = it.arguments?.getParcelable<DeviceV2>("device")
            val dd = device.toString() + ""
        }
    }
}

sealed class Route(
    val link: String,
    val arguments: List<NamedNavArgument> = emptyList(),
) {
    data object ProductList : Route(link = NavRouteName.product_list)
    data object ProductDetails : Route(
        link = "${NavRouteName.product_details}/{${NavArguments.product_details_parameters}}",
        arguments = listOf(
            navArgument(
                name = NavArguments.product_details_parameters,
            ) {
                type = ProductParametersType
            },
        ),
    )
}

sealed class RouteV2(
    link: String,
    argument: List<NamedNavArgument> = emptyList(),
) : BaseDestination(
    route = link,
    customArguments = argument,
) {
    data object ProductList : RouteV2(link = NavRouteName.product_list)
    data object ProductDetails : RouteV2(
        link = NavRouteName.product_details,
        argument = listOf(

            navArgument(
                name = NavArguments.product_details_parameters,
            ) {
                type = ProductParametersType
            },
        ),
//        argumentAlt = listOf(
//            CustomArgument_v2(
//                key = NavArguments.product_details_parameters,
//                argumentType = ProductParametersType,
//                isNullable = true,
//            ),
//        ),
    )
//    data object ProductDetails : RouteV2(
//        link = "${NavRouteName.product_details}/{${NavArguments.product_details_parameters}}",
//        argument = listOf(
//            CustomArgument_v2(
//                key = NavArguments.product_details_parameters,
//                argumentType = ProductParametersType,
//                isNullable = true,
//            ).lolo,
// //            navArgument(
// //                name = NavArguments.product_details_parameters,
// //            ) {
// //                type = ProductParametersType
// //            },
//        ),
//    )
}
