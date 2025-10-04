package com.revan.hanged.presentation.game_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.ui.theme.Red

@Composable
fun CreateRoom(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFC40100),
                        Color(0xFFA00100)
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )

    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 32.dp, bottom = 20.dp)
        ) {
            Text(
                text = "Ready to Play ?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Challenge your vocabulary skills with friends around the world",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White.copy(0.8f),
                modifier = Modifier.padding(end = 40.dp)
            )
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Color.White.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "+  Create New Room",
                    fontSize = 10.sp,
                    lineHeight = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Red.copy(alpha = 0.5f)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_create_room_bg_1),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.clip(RoundedCornerShape(topEnd = 10.dp))
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_create_room_bg_2),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.clip(RoundedCornerShape(bottomEnd = 10.dp))
            )
        }
    }
}


@Preview
@Composable
private fun CreateRoomPrev() {
    CreateRoom()
}