package com.artemissoftware.nestednavigation.randomimages

import androidx.annotation.DrawableRes
import com.google.gson.Gson

sealed class Image(@DrawableRes val id: Int) {

    data class Full(val isFull: Boolean, val imageId: Int) : Image(id = imageId)

    data class Regular(val imageId: Int) : Image(id = imageId)

    final override fun toString(): String {
        val json = Gson().toJsonTree(this).asJsonObject
        json.addProperty("type", this.javaClass.simpleName)
        return Gson().toJson(json)
    }
//    return (argument as com.artemissoftware.nestednavigation.randomimages.Image).toString()
}
