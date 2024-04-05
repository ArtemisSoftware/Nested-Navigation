package com.artemissoftware.nestednavigation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import com.artemissoftware.nestednavigation.ui.theme.ThemeType
import com.artemissoftware.nestednavigation.util.extensions.toBaseDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun onTriggerEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ThemeChangeByDestination -> {
                updateTheme(navDestination = event.navDestination)
            }

            is MainEvent.ThemeChange -> updateTheme(event.theme)
        }
    }

    private fun updateTheme(themeType: ThemeType) = with(_state) {
        if (value.theme != themeType) {
            update {
                it.copy(theme = themeType)
            }
        }
    }

    private fun updateTheme(navDestination: NavDestination? = null) = with(_state) {
        navDestination?.toBaseDestination()?.let { destination ->
            updateTheme(destination.themeType)
        } ?: updateTheme(ThemeType.DEFAULT)
    }

}
