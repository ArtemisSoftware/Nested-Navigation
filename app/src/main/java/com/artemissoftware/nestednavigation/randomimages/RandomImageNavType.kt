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


class ImageNavType : NavType<RandomImage?>(isNullableAllowed = false) {

    val gson = GsonBuilder().registerTypeAdapter(RandomImage::class.java, RandomImageAdapter()).create()

    override fun get(bundle: Bundle, key: String): RandomImage? {
        return bundle.getString(key)?.let { parseValue(it) }
    }

    override fun parseValue(value: String): RandomImage {
        val deserializedCircle: RandomImage = gson.fromJson(value, RandomImage::class.java)
        return deserializedCircle
    }
    override fun put(bundle: Bundle, key: String, value: RandomImage?) {
        value?.let {
            bundle.putString(key, it.toNavString())
        }
    }
}

// Custom JsonDeserializer for deserializing Shape objects
class RandomImageAdapter : JsonDeserializer<RandomImage> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): RandomImage {
        val jsonObject = json.asJsonObject
        val type = jsonObject.get("type").asString
        jsonObject.remove("type")

        return when (type) {
            RandomImage.Regular::class.simpleName -> {
                Gson().fromJson(jsonObject, RandomImage.Regular::class.java)
            }
            RandomImage.Full::class.simpleName -> {
                Gson().fromJson(jsonObject, RandomImage.Full::class.java)
            }
            else -> throw JsonParseException("Unknown random image type: $type")
        }
    }
}
