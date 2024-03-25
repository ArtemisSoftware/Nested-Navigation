package com.artemissoftware.nestednavigation

import com.artemissoftware.nestednavigation.ui.theme.ThemeType

sealed class MainEvent {
    data class ThemeChange(val route: String?) : MainEvent()

    data class Theme_Change(val theme: ThemeType) : MainEvent()
}
