package com.artemissoftware.nestednavigation.food

data class Food(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageId: Int,
    val source: String = " source"
)
