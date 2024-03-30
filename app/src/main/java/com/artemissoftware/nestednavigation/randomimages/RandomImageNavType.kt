package com.artemissoftware.nestednavigation.randomimages

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class RandomImageNavType : NavType<RandomImage>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): RandomImage? {
        return bundle.getString(key)?.let { parseValue(it) }
    }

    override fun parseValue(value: String): RandomImage {
        return Gson().fromJson(value, RandomImage::class.java)
    }
    override fun put(bundle: Bundle, key: String, value: RandomImage) {
        bundle.putString(key, Gson().toJson(value))
    }
}

class ImageNavType : NavType<Image?>(isNullableAllowed = true) {

    val gson = GsonBuilder().registerTypeAdapter(Image::class.java, ShapeAdapter()).create()

    override fun get(bundle: Bundle, key: String): Image? {
        return bundle.getString(key)?.let { parseValue(it) }
    }

    override fun parseValue(value: String): Image? {
        val deserializedCircle: Image = gson.fromJson(value, Image::class.java)
        return deserializedCircle
    }
    override fun put(bundle: Bundle, key: String, value: Image?) {
        value?.let {
            val type = value.javaClass.simpleName
            val lolo = RandomImageRoute.Image.gson.toJsonTree(value).asJsonObject
            lolo.addProperty("type", type)
            bundle.putString(key, Gson().toJson(lolo))
        }

    }
}

// Custom JsonDeserializer for deserializing Shape objects
class ShapeAdapter : JsonDeserializer<Image> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Image {
        val jsonObject = json.asJsonObject
        val shapeType = jsonObject.get("type").asString



        return when (shapeType) {
            "Regular" -> {
                jsonObject.remove("type")
                Gson().fromJson(jsonObject, Image.Regular::class.java)
            }
            else -> throw JsonParseException("Unknown shape type: $shapeType")
        }
    }
}
