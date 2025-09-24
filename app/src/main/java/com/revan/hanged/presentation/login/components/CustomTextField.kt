package com.revan.hanged.presentation.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.R
import com.revan.hanged.ui.theme.DarkGray
import com.revan.hanged.ui.theme.LightGray

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType,
    text: String,
    onValueChange: (String) -> Unit,
    isPasswordVisible: Boolean = false
) {
    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            Text(
                if (keyboardType == KeyboardType.Email) "Email"
                else if (keyboardType == KeyboardType.Password) "Password" else "",
                color = LightGray,
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = DarkGray,
            unfocusedContainerColor = DarkGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = {
            if (keyboardType == KeyboardType.Password) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (isPasswordVisible) R.drawable.ic_hide_password else R.drawable.ic_show_password
                    ),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        },
        visualTransformation = if (keyboardType == KeyboardType.Password) {
            if (isPasswordVisible) PasswordVisualTransformation('*') else VisualTransformation.None
        } else {
            VisualTransformation.None
        },
    )
}

@Preview
@Composable
private fun CustomTextFieldPrev() {
    CustomTextField(text = "", onValueChange = {}, keyboardType = KeyboardType.Password)
}