package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.presentation.components.CustomButton
import com.revan.hanged.presentation.components.CustomWhiteButton
import com.revan.hanged.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameResultBottomSheet(
    modifier: Modifier = Modifier,
    headerText: String,
    contentText: String,
    word: String?,
    onClick: () -> Unit
) {
    ModalBottomSheet(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .navigationBarsPadding()
            .padding(bottom = 8.dp),
        onDismissRequest = { onClick() },
        shape = RoundedCornerShape(16.dp),
        contentColor = Color(0xFF2E3740),
        content = {
            GameResultBottomSheetContent(
                headerText = headerText,
                contentText = contentText,
                onClick = onClick,
                word = word
            )
        }
    )
}


@Composable
fun GameResultBottomSheetContent(
    headerText: String,
    contentText: String,
    word: String?,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(bottom = 15.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = headerText,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = LightGray,
            textAlign = TextAlign.Center
        )
        Text(
            text = contentText,
            fontSize = 15.sp,
            color = LightGray,
            modifier = Modifier.padding(horizontal = 60.dp),
            textAlign = TextAlign.Center
        )
        word?.let {
            Text(
                text = stringResource(R.string.word, it),
                fontSize = 15.sp,
                color = LightGray,
                modifier = Modifier.padding(horizontal = 60.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        }
        Column(
            modifier = Modifier.height(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                text = "Exit",
                roundedCornerShape = 15.dp,
                onClick = {
                    onClick()
                },
                fontWeight = FontWeight.SemiBold,
                isButtonEnabled = true,
                textVerticalPadding = 14.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)

            )
            Spacer(Modifier.height(8.dp))
            CustomWhiteButton(
                text = stringResource(R.string.view_result),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                fontWeight = FontWeight.SemiBold,
                textVerticalPadding = 14.dp,
                onClick = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun GameBottomSheetPrev(
) {
    GameResultBottomSheetContent(
        headerText = "Congrats",
        contentText = "You guessed the word correctly and won this round!",
        onClick = {},
        word = "Butterfly"
    )
}