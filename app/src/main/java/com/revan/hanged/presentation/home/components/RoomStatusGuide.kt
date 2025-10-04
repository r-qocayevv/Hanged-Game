package com.revan.hanged.presentation.home.components

import android.graphics.RenderEffect
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.presentation.home.HomeEvent
import com.revan.hanged.ui.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomStatusGuide(
    modifier: Modifier = Modifier,
    onEvent : (HomeEvent) -> Unit
) {
    ModalBottomSheet(
        containerColor = Color(0xFF2E3740),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.padding(horizontal = 20.dp).navigationBarsPadding().padding(bottom = 8.dp),
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