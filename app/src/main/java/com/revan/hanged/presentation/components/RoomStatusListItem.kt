package com.revan.hanged.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.domain.RoomStatus
import com.revan.hanged.ui.theme.LightGray

@Composable
fun RoomStatusListItem (
    modifier: Modifier = Modifier,
    roomStatus: RoomStatus
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = ImageVector.vectorResource(roomStatus.icon),
            contentDescription = null,
            tint = roomStatus.color
        )
        Spacer(Modifier.width(12.dp))
        Column (
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = roomStatus.label,
                color = roomStatus.color,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = roomStatus.description,
                color = LightGray,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
            )
        }

    }

}

@Preview
@Composable
private fun RoomStatusListItemPrev() {
    RoomStatusListItem(roomStatus = RoomStatus.FULL)
}