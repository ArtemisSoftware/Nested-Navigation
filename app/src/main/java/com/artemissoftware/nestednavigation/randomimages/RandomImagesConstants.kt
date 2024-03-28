package com.artemissoftware.nestednavigation.randomimages

import com.artemissoftware.nestednavigation.R

object RandomImagesConstants {

    val randomImages = listOf(
        RandomImage(R.drawable.image_1, false),
        RandomImage(R.drawable.image_2, true),
        RandomImage(R.drawable.image_3, true),
        RandomImage(R.drawable.image_4, false),
        RandomImage(R.drawable.image_5, false),
    ).shuffled()
}
