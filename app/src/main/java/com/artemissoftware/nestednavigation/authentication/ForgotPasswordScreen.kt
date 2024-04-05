package com.artemissoftware.nestednavigation.authentication

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.artemissoftware.nestednavigation.R

@Composable
fun ForgotPasswordScreen(
    popBackStack: () -> Unit,
) {
    var email by remember { mutableStateOf("") }

    var showConfirmDialog by remember { mutableStateOf(false) }
    BackHandler(enabled = true) {
        showConfirmDialog = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.food16),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(bottom = 16.dp),
        )
        Text(text = "*** Forgot Screen ***")

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = popBackStack,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(text = "Reset Password")
        }

        if (showConfirmDialog) {
            ConfirmDialog(
                onDismissRequest = { showConfirmDialog = false },
                backToMain = popBackStack
            )
        }
    }
}

@Composable
private fun ConfirmDialog(
    onDismissRequest: () -> Unit,
    backToMain: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .height(200.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
        ) {
            Button(onClick = onDismissRequest ) {
                Text(text = "Cancel")
            }
            Button(onClick = backToMain) {
                Text(text = "Confirm")
            }
        }
    }
}

@Preview
@Composable
private fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(
        popBackStack = {},
    )
}
