package com.artemissoftware.nestednavigation.randomimages

import com.artemissoftware.nestednavigation.R
import kotlin.random.Random

object RandomImagesConstants {

    val randomImages = listOf(
        getRandomImage(R.drawable.image_1),
        getRandomImage(R.drawable.image_2),
        getRandomImage(R.drawable.image_3),
        getRandomImage(R.drawable.image_4),
        getRandomImage(R.drawable.image_5),
        getRandomImage(R.drawable.image_6),
        getRandomImage(R.drawable.image_7),
        getRandomImage(R.drawable.image_8),
    ).shuffled()

    private fun getRandomImage(imageId: Int): RandomImage{

        val randomNumber = Random.nextInt(2)

        return when{
            randomNumber == 0 -> RandomImage.Full(imageId)
            randomNumber == 1 -> RandomImage.Regular(imageId)
            else -> throw NullPointerException()
        }
    }
}
