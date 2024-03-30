package com.artemissoftware.nestednavigation.randomimages

import androidx.annotation.DrawableRes

sealed class Image(@DrawableRes val id: Int) {

    data class Full(val isFull: Boolean, val imageId: Int) : Image(id = imageId)

    data class Regular(val imageId: Int) : Image(id = imageId)

    companion object{
        fun lolo () = listOf(Image.Regular::class.simpleName, Image.Full::class.simpleName)
    }
}
