package com.revan.hanged.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
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
import com.revan.hanged.presentation.components.Tabs
import com.revan.hanged.presentation.home.HomeEvent
import com.revan.hanged.presentation.home.HomeState
import com.revan.hanged.ui.theme.DarkGray
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.clickWithoutRipple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    uiState: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    val refreshState = rememberPullToRefreshState()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = DarkGray)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_rooms),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
                Text(
                    text = uiState.availableRoomCount.toString(),
                    modifier = Modifier.padding(horizontal = 2.dp),
                    color = LightGray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            val tabs = listOf("Waiting", "Playing", "Full")
            Tabs(
                tabs = tabs, selectedTabIndex = uiState.selectedTabIndex,
                onItemSelection = { tabIndex ->
                    onEvent(HomeEvent.OnItemSelection(tabIndex = tabIndex))
                })
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

    PullToRefreshBox(indicator = {
        PullToRefreshDefaults.Indicator(
            color = Red,
            modifier = Modifier.align(Alignment.TopCenter),
            isRefreshing = uiState.isRefreshing,
            state = refreshState
        )
    }, state = refreshState, isRefreshing = uiState.isRefreshing, onRefresh = {
        onEvent(HomeEvent.RefreshPage)
    }) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {

            item {
                Spacer(Modifier.height(10.dp))
            }
            if (!uiState.isLoading) {
                if (uiState.filteredRooms.isNotEmpty()) {
                    items(uiState.filteredRooms, key = { it.roomId }) { room ->
                        RoomListItem(room = room, onEvent = onEvent, uiState = uiState)
                    }
                } else {
                    item {
                        EmptyAvailableRoom(modifier = Modifier.fillParentMaxHeight())
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun HomeContentPrev() {
    HomeContent(uiState = HomeState(), onEvent = {})
}