package com.artemissoftware.nestednavigation.randomimages

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RandomImageRecipient(
    val imageId: Int,
    val points: Int
) : Parcelable
