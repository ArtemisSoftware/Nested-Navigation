package com.artemissoftware.nestednavigation.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artemissoftware.nestednavigation.R
import com.artemissoftware.nestednavigation.composables.topbar.TopBar
import com.artemissoftware.nestednavigation.ui.theme.NestedNavigationTheme
import com.artemissoftware.nestednavigation.ui.theme.ThemeType

@Composable
fun LoginScreen(
    onClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit,
    checkFullCompliance: () -> Unit,
    checkCompliance2: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.login))
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .clickable { onClick() },
                text = "LOGIN",
                fontSize = MaterialTheme.typography.displayLarge.fontSize,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(32.dp))

            ElevatedButton(
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                ),
                onClick = {
                    onSignUpClick()
                },
            ) {
                Text(
                    modifier = Modifier
                        .clickable { onSignUpClick() },
                    text = "Sign Up",
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    fontWeight = FontWeight.Medium,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            FilledTonalButton(
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                ),
                onClick = {
                    onForgotClick()
                },
            ) {
                Text(
                    modifier = Modifier,
                    text = "Forgot Password",
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    fontWeight = FontWeight.Medium,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            ElevatedButton(
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                ),
                onClick = {
                    checkFullCompliance()
                },
            ) {
                Text(
                    modifier = Modifier,
                    text = "Check full compliance",
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    fontWeight = FontWeight.Medium,
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            FilledTonalButton(
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                ),
                onClick = {
                    checkCompliance2()
                },
            ) {
                Text(
                    modifier = Modifier,
                    text = "Check compliance 2",
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    NestedNavigationTheme(themeType = ThemeType.AUTHENTICATION) {
        LoginScreen(
            onClick = {},
            onSignUpClick = {},
            onForgotClick = {},
            checkFullCompliance = {},
            checkCompliance2 = {},
        )
    }
}
