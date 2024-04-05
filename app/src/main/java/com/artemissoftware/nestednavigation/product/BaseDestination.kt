package com.artemissoftware.nestednavigation.product

import android.net.Uri
import androidx.navigation.NamedNavArgument
import com.artemissoftware.nestednavigation.randomimages.RandomImage
import com.artemissoftware.nestednavigation.randomimages.ShapeAdapter
import com.artemissoftware.nestednavigation.ui.theme.ThemeType
import com.google.gson.GsonBuilder

abstract class BaseDestination(
    val route: String,
    val customArguments: List<NamedNavArgument> = emptyList(),
    val themeType: ThemeType = ThemeType.DEFAULT,
    val title: String = ""
) {

    val gson = GsonBuilder().registerTypeAdapter(RandomImage::class.java, ShapeAdapter()).create()


    fun getRouteInFull(): String {
        return if (customArguments.isEmpty()) route else fullRoute
    }

    private val fullRoute: String = buildString {
        append(route)
        customArguments.forEachIndexed { index, custom ->
            val symbol = if (index == 0) "?" else "&"
//            append("$symbol${custom.key}={${custom.key}}")
            append("$symbol${custom.name}={${custom.name}}")
        }
    }

    val arguments = customArguments

    fun withCustomArgs(vararg args: Any?): String {
        return buildString {
            append(route)
            args.forEachIndexed { index, arg ->
                val json = Uri.encode(getJsonEncoding(arg))
                val symbol = if (index == 0) "?" else "&"
                append("$symbol${customArguments[index].name}=$json")
            }
        }
    }

    open fun getJsonEncoding(arg: Any?): String? {
        return gson.toJson(arg)
    }

//    fun getRoutel(): String {
//        return if (customArguments.isEmpty()) route else fullRoute
//    }
//
//    private val fullRoute: String = buildString {
//        append(route)
//        customArguments.forEachIndexed { index, custom ->
//            val symbol = if (index == 0) "?" else "&"
//            append("$symbol${custom.key}={${custom.key}}")
//        }
//    }
//
//    val arguments: List<NamedNavArgument> = customArguments.map {
//        navArgument(it.key) {
//            type = it.type
//            nullable = it.nullable
//        }
//    }
//
//    fun withArgs(vararg args: Any?): String {
//        return buildString {
//            append(route)
//            args.forEachIndexed { index, arg ->
//                val symbol = if (index == 0) "?" else "&"
//                append("$symbol${customArguments[index].key}=$arg")
//            }
//        }
//    }
//
//    fun withArgs(list: List<String>?): String {
//        return buildString {
//            append(route)
//            list?.forEachIndexed { index, arg ->
//                val symbol = if (index == 0) "?" else "&"
//                append("$symbol${customArguments[index].key}=$arg")
//            }
//        }
//    }
//
//    fun withCustomArgs(vararg args: Any?): String {
//        return buildString {
//            append(route)
//            args.forEachIndexed { index, arg ->
//                val json = Uri.encode(Gson().toJson(arg))
//                val symbol = if (index == 0) "?" else "&"
//                append("$symbol${customArguments[index].key}=$json")
//            }
//        }
//    }
}
