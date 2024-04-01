package com.artemissoftware.nestednavigation.randomimages

import androidx.annotation.DrawableRes
import com.google.gson.Gson

sealed class RandomImage(@DrawableRes val id: Int) {

    data class Full(val imageId: Int) : RandomImage(id = imageId)

    data class Regular(val imageId: Int) : RandomImage(id = imageId)

    fun toNavString(): String {
        val json = Gson().toJsonTree(this).asJsonObject
        json.addProperty("type", this.javaClass.simpleName)
        return Gson().toJson(json)
    }
}
