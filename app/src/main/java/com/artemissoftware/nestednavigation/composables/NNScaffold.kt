package com.artemissoftware.nestednavigation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.artemissoftware.nestednavigation.ui.theme.NestedNavigationTheme
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

@Composable
fun NNScaffold(
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = topBar,
        bottomBar = bottomBar,
        contentColor = MaterialTheme.colorScheme.onTertiary,
    ) { values ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(values),
        ) {
            content.invoke()
        }
    }
}

@Preview
@Composable
private fun NNScaffoldPreview() {
    NestedNavigationTheme(themeType = ThemeType.GALLERY) {
        NNScaffold {
            Box {}
        }
    }
}
