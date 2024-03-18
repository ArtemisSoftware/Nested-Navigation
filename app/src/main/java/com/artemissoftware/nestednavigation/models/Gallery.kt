package com.artemissoftware.nestednavigation.models

data class Gallery(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageId: Int,
    val source: String = " source"
)
