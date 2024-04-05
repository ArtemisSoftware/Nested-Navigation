package com.artemissoftware.nestednavigation

import androidx.navigation.NavDestination
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

sealed class MainEvent {
    data class ThemeChangeByDestination(val navDestination: NavDestination?) : MainEvent()

    data class ThemeChange(val theme: ThemeType) : MainEvent()
}
