package com.artemissoftware.nestednavigation.util.extensions

import androidx.navigation.NavDestination
import com.artemissoftware.nestednavigation.authentication.AuthRoute
import com.artemissoftware.nestednavigation.food.FoodRoute
import com.artemissoftware.nestednavigation.product.BaseDestination
import com.artemissoftware.nestednavigation.randomimages.RandomImageRoute

fun NavDestination.toBaseDestination() : BaseDestination?{
    return when {
        RandomImageRoute.Image.route == route -> RandomImageRoute.Image
        RandomImageRoute.ImagesList.route == route -> RandomImageRoute.ImagesList

        FoodRoute.FoodList.route == route -> FoodRoute.FoodList
        FoodRoute.Detail.route == route -> FoodRoute.Detail

        AuthRoute.Login.route == route -> AuthRoute.Login
        else -> null
    }
}