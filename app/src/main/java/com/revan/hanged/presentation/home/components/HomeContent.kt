package com.revan.hanged.presentation.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import com.revan.hanged.presentation.home.HomeEvent
import com.revan.hanged.presentation.home.HomeState
import com.revan.hanged.ui.theme.DarkGray
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.clickWithoutRipple

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeState,
    onEvent: (HomeEvent) -> Unit
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        stickyHeader {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = DarkGray),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Available Room (${uiState.availableRoomCount})",
                    modifier = Modifier.weight(1f),
                    color = LightGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_info),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.clickWithoutRipple(onClick = {
                        onEvent(HomeEvent.ShowRoomStatusGuide(showBottomSheet = true))
                    })
                )
            }
        }

        if (uiState.isLoading) {
            item {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Red,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        if (uiState.rooms.isNotEmpty()) {
            items(uiState.rooms) { room ->
                RoomListItem(room = room, onEvent = onEvent)
            }
        } else {
            item {
                EmptyAvailableRoom(modifier = Modifier.fillParentMaxHeight())
            }
        }
    }


}

@Preview
@Composable
private fun HomeContentPrev() {
    HomeContent(uiState = HomeState(), onEvent = {})
}