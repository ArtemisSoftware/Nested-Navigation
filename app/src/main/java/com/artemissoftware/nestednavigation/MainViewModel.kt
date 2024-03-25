package com.artemissoftware.nestednavigation

import androidx.lifecycle.ViewModel
import com.artemissoftware.nestednavigation.gallery.GalleryRoute
import com.artemissoftware.nestednavigation.ui.theme.ThemeType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun onTriggerEvent(event: MainEvent) {
        when (event) {
            is MainEvent.ThemeChange -> {
                updateTheme(route = event.route)
            }

            is MainEvent.Theme_Change -> update_Theme(event.theme)
        }
    }

    private fun update_Theme(themeType: ThemeType) = with(_state) {
        if (value.theme != themeType) {
            update {
                it.copy(theme = themeType)
            }
        }
    }

    private fun updateTheme(route: String? = null) = with(_state) {
        val theme = getTheme(route)
        if (value.theme != theme) {
            update {
                it.copy(theme = theme)
            }
        }
    }

    private fun getTheme(route: String? = null): ThemeType {
        return when {
            GalleryRoute.Gallery.route == route -> GalleryRoute.Gallery.themeType
//            Red.route == route -> Red.themeType
//            Blue.route == route -> Blue.themeType
//            Green.route == route -> Green.themeType
            else -> ThemeType.DEFAULT
        }
    }
}
