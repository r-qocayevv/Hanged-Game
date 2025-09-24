package com.revan.hanged.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.presentation.components.CustomHangedText
import com.revan.hanged.presentation.login.components.ContinueAsGuestButton
import com.revan.hanged.presentation.login.components.CustomButton
import com.revan.hanged.presentation.login.components.CustomTextField
import com.revan.hanged.presentation.login.components.OrDivider
import com.revan.hanged.ui.theme.LightGray

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            tint = Color.Unspecified
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f),
                contentAlignment = Alignment.Center
            ) {
                CustomHangedText(size = 40.sp)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 90.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.sign_in),
                    fontSize = 24.sp,
                    color = LightGray,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(30.dp))

                CustomTextField(
                    text = uiState.email,
                    keyboardType = KeyboardType.Email,
                    onValueChange = { email ->
                        onEvent(LoginEvent.EmailChanged(email))
                    })

                Spacer(Modifier.height(12.dp))

                CustomTextField(
                    text = uiState.password,
                    keyboardType = KeyboardType.Password,
                    onValueChange = { password ->
                        onEvent(LoginEvent.PasswordChanged(password))
                    })

                Spacer(Modifier.height(29.dp))

                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.sign_in),
                    isButtonEnabled = uiState.isButtonEnabled,
                    onClick = {
                        onEvent(LoginEvent.SignIn)
                    })

                Spacer(Modifier.height(11.dp))
                OrDivider()
                Spacer(Modifier.height(11.dp))
                ContinueAsGuestButton(onClick = {

                })
            }
        }
    }
}

@Preview()
@Composable
private fun LoginScreenPrev() {
    MaterialTheme {
        LoginScreen(uiState = LoginState(), onEvent = {})
    }
}