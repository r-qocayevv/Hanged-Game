package com.revan.hanged.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.domain.RoomStatus
import com.revan.hanged.domain.model.JoinRoomInfo
import com.revan.hanged.domain.model.Room
import com.revan.hanged.presentation.home.HomeEvent
import com.revan.hanged.presentation.home.HomeState
import com.revan.hanged.presentation.login.components.CustomButton
import com.revan.hanged.ui.theme.LightGray

@Composable
fun RoomListItem(
    modifier: Modifier = Modifier,
    onEvent : (HomeEvent) -> Unit,
    room : Room
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(Color(0xFF2E3641), shape = RoundedCornerShape(10.dp))
            .border(1.dp, color = Color(0xFFC5C5C4).copy(0.2f), shape = RoundedCornerShape(10.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(room.status.icon),
                    contentDescription = null,
                    tint = room.status.color,
                    modifier = Modifier
                )
                Text(
                    text = room.roomName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_player),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(end = 2.dp)
                )
                Text(
                    text = "${room.playerCount}/${room.maxPlayers}",
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = LightGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.width(10.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_waiting),
                    contentDescription = null,
                    tint = LightGray,
                    modifier = Modifier.padding(end = 2.dp)
                )
                Text(
                    text = room.createdAt,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = LightGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_flame),
                    contentDescription = null,
                    tint = LightGray,
                    modifier = Modifier.padding(end = 2.dp)
                )
                Text(
                    text = room.difficulty,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = LightGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        CustomButton(text = "Join", isButtonEnabled = room.status != RoomStatus.FULL, onClick = {
            //TODO USER melumatlarini registerden sonra götürmek
            onEvent(HomeEvent.JoinRoom(JoinRoomInfo(room.roomId,"","Raven",room.difficulty,room.language)))
        })
    }
}

@Preview
@Composable
private fun RoomListItemPrev() {
    RoomListItem(room = Room("","hard","Az",4,4,"","Raven`s room", RoomStatus.PLAYING), onEvent = {})
}