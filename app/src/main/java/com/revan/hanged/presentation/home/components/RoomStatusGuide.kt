package com.revan.hanged.presentation.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.presentation.home.HomeEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomStatusGuide(
    modifier: Modifier = Modifier,
    onEvent : (HomeEvent) -> Unit
) {
    ModalBottomSheet(
        containerColor = Color(0xFF2E3740),
        modifier = modifier.padding(horizontal = 20.dp),
        onDismissRequest = {
            onEvent(HomeEvent.ShowRoomStatusGuide(showBottomSheet = false))
        }
    ) {
        RoomGuideModalSheetContent()
    }
}

@Preview
@Composable
private fun RoomStatusGuidePrev() {
    RoomStatusGuide(onEvent = {})
}