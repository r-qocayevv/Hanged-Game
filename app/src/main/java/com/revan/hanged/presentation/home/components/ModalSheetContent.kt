package com.revan.hanged.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.domain.RoomStatus
import com.revan.hanged.presentation.components.RoomStatusListItem
import com.revan.hanged.ui.theme.LightGray

@Composable
fun ModalSheetContent(
    modifier: Modifier = Modifier
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(22.dp),
        modifier = Modifier.padding(start = 34.dp, end = 34.dp, bottom = 23.dp)
    ){
        item{
            Text(
                text = "Room Status Guide",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = LightGray,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(RoomStatus.entries) {
            RoomStatusListItem(roomStatus = it)
        }
    }
}

@Preview
@Composable
private fun ModalSheetContentPrev() {
    ModalSheetContent()
}