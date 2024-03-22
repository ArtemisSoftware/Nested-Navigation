package com.artemissoftware.nestednavigation

sealed class MainEvent {
    data class ThemeChange(val route: String?) : MainEvent()
}
