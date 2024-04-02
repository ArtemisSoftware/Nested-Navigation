package com.artemissoftware.nestednavigation.randomimages

import androidx.annotation.DrawableRes
import com.google.gson.Gson

sealed class RandomImage(@DrawableRes val id: Int, var clicks: Int = 0) {

    data class Full(val imageId: Int, val imageClicks: Int = 0) : RandomImage(id = imageId, clicks = imageClicks)

    data class Regular(val imageId: Int, val imageClicks: Int = 0) : RandomImage(id = imageId, clicks = imageClicks)

    fun toNavString(): String {
        val json = Gson().toJsonTree(this).asJsonObject
        json.addProperty("type", this.javaClass.simpleName)
        return Gson().toJson(json)
    }

    fun copyClicks(points: Int): RandomImage {
        this.clicks += points
        return this
    }
}
