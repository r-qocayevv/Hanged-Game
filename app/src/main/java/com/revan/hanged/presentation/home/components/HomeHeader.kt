package com.revan.hanged.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.presentation.components.CustomHangedText
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.utils.clickWithoutRipple

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.weight(1f)
        ) {
            CustomHangedText(size = 24.sp)
            Text(
                text = "Welcome, user",
                fontSize = 12.sp,
                color = LightGray,
                fontWeight = FontWeight.SemiBold
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_log_out),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.clickWithoutRipple {

            }
        )
    }
}

@Preview
@Composable
private fun HomeHeaderPrev() {
    HomeHeader()
}