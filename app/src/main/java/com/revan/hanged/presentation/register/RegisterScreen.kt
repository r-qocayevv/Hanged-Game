package com.revan.hanged.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.navigation.ScreenRoute
import com.revan.hanged.presentation.components.CustomButton
import com.revan.hanged.presentation.login.components.CustomTextField
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.clickWithoutRipple


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    uiState: RegisterState,
    onEvent: (RegisterEvent) -> Unit
) {

    LaunchedEffect(key1 = uiState.email, key2 = uiState.password, key3 = uiState.username) {
        onEvent(RegisterEvent.CheckValidation)
    }

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
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_hanged),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
            Column(
                modifier = Modifier
                    .padding(vertical = 90.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    fontSize = 24.sp,
                    color = LightGray,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(30.dp))

                CustomTextField(
                    text = uiState.username,
                    keyboardType = KeyboardType.Text,
                    onValueChange = { username ->
                        onEvent(RegisterEvent.UsernameChanged(newUsername = username))
                    }
                )

                Spacer(Modifier.height(12.dp))

                CustomTextField(
                    text = uiState.email,
                    keyboardType = KeyboardType.Email,
                    onValueChange = { email ->
                        onEvent(RegisterEvent.EmailChanged(newEmail = email))
                    }
                )

                Spacer(Modifier.height(12.dp))

                CustomTextField(
                    text = uiState.password,
                    keyboardType = KeyboardType.Password,
                    onValueChange = { password ->
                        onEvent(RegisterEvent.PasswordChanged(newPassword = password))
                    },
                    showPasswordIconClicked = {
                        onEvent(RegisterEvent.ChangePasswordVisibility)
                    },
                    isPasswordVisible = uiState.isPasswordVisible
                )



                Spacer(Modifier.height(29.dp))

                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    textVerticalPadding = 15.dp,
                    text = stringResource(R.string.sign_up),
                    isButtonEnabled = uiState.isButtonEnabled,
                    isLoading = uiState.isLoading,
                    onClick = {
                        onEvent(RegisterEvent.SignUp)
                    })

                Spacer(Modifier.height(11.dp))

            }

            Text(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .clickWithoutRipple(onClick = {
                        onEvent(
                            RegisterEvent.OnNavigate(
                                ScreenRoute.Login,
                                popUpTo = ScreenRoute.Login
                            )
                        )
                    }),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(stringResource(R.string.already_have_an_account))
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Red,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(stringResource(R.string.sign_in))
                    }
                }
            )
        }
    }
}

@Preview()
@Composable
private fun RegisterScreenPrev() {
    MaterialTheme {
        RegisterScreen(uiState = RegisterState(), onEvent = {})
    }
}