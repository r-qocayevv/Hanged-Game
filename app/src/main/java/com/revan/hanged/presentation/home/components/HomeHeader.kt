package com.revan.hanged.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.utils.clickWithoutRipple

@Composable
fun HomeHeader(
    username: String,
    modifier: Modifier = Modifier,
    logoutButtonClick: () -> Unit,
    navigateGameHistory: () -> Unit
) {
    Row(
        modifier = modifier
            .background(color = Color(0xFF2E3641))
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .size(42.dp)
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            Color(0xFFC40100),
                            Color(0xFFA00100)
                        )
                    ),
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            //todo
            Text(
                text = "H",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(Modifier.width(13.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.hanged),
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.welcome, username),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LightGray,
                fontWeight = FontWeight.SemiBold
            )
        }
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.icon_history),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.clickWithoutRipple(
                onClick = {
                    navigateGameHistory()
                }
            )
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_log_out),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.clickWithoutRipple(
                onClick = {
                    logoutButtonClick()
                }
            )
        )
    }
}

@Preview
@Composable
private fun HomeHeaderPrev() {
    HomeHeader(username = "Raven", logoutButtonClick = {}, navigateGameHistory = {})
}