package com.artemissoftware.nestednavigation.product

import androidx.navigation.NavType
import androidx.navigation.navArgument

data class CustomArgument(
    val key: String,
    val type: NavType<*> = NavType.StringType,
    val nullable: Boolean = true,
)

class CustomArgument_v2<T>(
    val key: String,
//    val argumentType: NavType<*>,
    val isNullable: Boolean = true,
    val argumentType: NavType<T?>,
) {
//    val lolo: NamedNavArgument = navArgument(name = key) {
//        type = argumentType
//        nullable = isNullable
//    }
}

fun cArtgs(
    key: String,
    argumentType: NavType<*> = NavType.StringType,
    isNullable: Boolean = true,
) = navArgument(name = key) {
    type = argumentType
    nullable = isNullable
}
