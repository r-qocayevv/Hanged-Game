package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HangmanCanvas(wrong: Int) {
    Canvas(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(vertical = 13.dp)
    ) {

        val w = size.width
        val h = size.height
        val baseY = h * 0.95f
        val poleX = w * 0.2f
        val topY = h * 0.05f
        val ropeX = w * 0.5f
        val headCenterY = h * 0.25f
        val bodyTopY = h * 0.35f
        val bodyBottomY = h * 0.6f
        if (wrong > 0) {
            drawLine(Color.White, Offset(w * 0.1f, baseY), Offset(w * 0.9f, baseY), 8f)
        }
        if (wrong > 1) {
            drawLine(Color.White, Offset(poleX, baseY), Offset(poleX, topY), 8f)
        }
        if (wrong > 2) {
            drawLine(Color.White, Offset(poleX, topY), Offset(ropeX, topY), 8f)
        }
        if (wrong > 3) {
            drawLine(Color.White, Offset(ropeX, topY), Offset(ropeX, headCenterY - h * 0.05f), 4f)
        }
        if (wrong > 4) {
            drawCircle(
                Color.White,
                radius = h * 0.07f,
                center = Offset(ropeX, headCenterY + h * 0.02f),
                style = Stroke(width = 4f)
            )
        }
        if (wrong > 5) {
            drawLine(Color.White, Offset(ropeX, bodyTopY), Offset(ropeX, bodyBottomY), 4f)
        }
        if (wrong > 6) {
            drawLine(Color.White, Offset(ropeX, h * 0.4f), Offset(ropeX - w * 0.15f, h * 0.5f), 4f)
        }
        if (wrong > 7) {
            drawLine(Color.White, Offset(ropeX, h * 0.4f), Offset(ropeX + w * 0.15f, h * 0.5f), 4f)
        }
        if (wrong > 8) {
            drawLine(Color.White, Offset(ropeX, bodyBottomY), Offset(ropeX - w * 0.15f, h * 0.8f), 4f)
        }
        if (wrong > 9) {
            drawLine(Color.White, Offset(ropeX, bodyBottomY), Offset(ropeX + w * 0.15f, h * 0.8f), 4f)
        }
    }
}


@Preview
@Composable
private fun HangmanCanvasPrev() {
    HangmanCanvas(wrong = 10)
}