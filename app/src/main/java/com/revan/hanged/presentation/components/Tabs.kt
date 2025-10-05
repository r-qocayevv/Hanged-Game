package com.revan.hanged.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.ui.theme.DarkGray

@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    tabs : List<String>,
    selectedTabIndex : Int,
    onItemSelection : (Int) -> Unit
) {
    Box(
        modifier = modifier.background(color = DarkGray),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .border(width = 0.5.dp, color = Color(0xFF7D8899), shape = CircleShape)
                .background(Color.White.copy(alpha = 0.1f), shape = CircleShape)
        ) {
            tabs.forEachIndexed { index, item ->
                val isSelected = index == selectedTabIndex
                Box(
                    modifier = Modifier
                        .padding(vertical = 2.dp, horizontal = 4.dp)
                        .clip(shape = CircleShape)
                        .background(if (isSelected) Color.White else Color.Transparent)
                        .clickable {
                            onItemSelection(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp),
                        text = item,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        maxLines = 1,
                        color = if (isSelected) Color.Black else Color.White,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }

}

@Preview
@Composable
private fun TabsPrev() {
    Tabs(tabs = listOf("History", "LeaderboardDTO","Test"), selectedTabIndex = 0, onItemSelection = {})
}