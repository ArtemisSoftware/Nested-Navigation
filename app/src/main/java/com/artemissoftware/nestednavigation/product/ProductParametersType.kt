package com.artemissoftware.nestednavigation.product

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

val ProductParametersType: NavType<ProductParameters?> = object : NavType<ProductParameters?>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ProductParameters? {
        return bundle.getString(key)?.let { parseValue(it) }
    }
    override fun parseValue(value: String): ProductParameters {
        return Gson().fromJson(value, ProductParameters::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: ProductParameters?) {
        bundle.putString(key, Gson().toJson(value))
    }

    fun NavType<ProductParameters?>.toNV_String(product: ProductParameters) = Gson().toJson(product)

//    fun serializeAsValue(value: ProductParameters?): String {
//        return Gson().toJson(value)
//    }
}

fun NavType<ProductParameters?>.toNVString(product: ProductParameters) = Gson().toJson(product)

abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}

class ProductParametersArgType_ : NavType<ProductParameters>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ProductParameters? {
        return bundle.getString(key)?.let { parseValue(it) }
    }

    override fun parseValue(value: String): ProductParameters {
        return Gson().fromJson(value, ProductParameters::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ProductParameters) {
        bundle.putString(key, Gson().toJson(value))
    }
}

class ProductParametersArgType : JsonNavType<ProductParameters>() {
    override fun fromJsonParse(value: String): ProductParameters = Gson().fromJson(value, ProductParameters::class.java)

    override fun ProductParameters.getJsonParse(): String = Gson().toJson(this)
}


//class ProductParametersArgType_v3 : NavType<ProductParameters>(isNullableAllowed = true) {
//    override fun get(bundle: Bundle, key: String): ProductParameters? {
//
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            bundle.getParcelable(key, ProductParameters::class.java)
//        } else {
//            bundle.getParcelable(key)
//        }
//    }
//
//    override fun put(bundle: Bundle, key: String, value: ProductParameters) {
//        bundle.putParcelable(key, value)
//    }
//
//    override fun parseValue(value: String): ProductParameters {
//        return Gson().fromJson(value, ProductParameters::class.java)
//    }
//
//}