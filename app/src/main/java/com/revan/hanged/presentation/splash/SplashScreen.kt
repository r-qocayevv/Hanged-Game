package com.revan.hanged.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.R
import com.revan.hanged.ui.theme.DarkGray

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(color = DarkGray),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_hanged),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Spacer(Modifier.height(32.dp))

    }
}

@Preview
@Composable
private fun SplashScreenPrev() {
    SplashScreen()
}