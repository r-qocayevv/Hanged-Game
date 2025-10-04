package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
fun ConfirmExitBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    exitGame: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { false }
    )

    ModalBottomSheet(
        containerColor = Color(0xFF2E3740),
        sheetState = sheetState,
        shape = RoundedCornerShape(16.dp),
        onDismissRequest = {},
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .navigationBarsPadding()
            .padding(bottom = 8.dp),
    ) {
        ConfirmExitBottomSheetContent(exitGame = exitGame, onDismiss = onDismiss)
    }
}


@Composable
private fun ConfirmExitBottomSheetContent(
    exitGame: () -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.are_you_sure_you_want_to_leave),
            color = LightGray,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = stringResource(R.string.confirm_exit_content),
            color = LightGray,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 48.dp),
            textAlign = TextAlign.Center,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(horizontal = 18.dp)
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            CustomWhiteButton(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.stay),
                onClick = onDismiss
            )

            CustomButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                roundedCornerShape = 15.dp,
                text = stringResource(R.string.exit_game),
                isButtonEnabled = true,
                textVerticalPadding = 10.dp,
                onClick = {
                    exitGame()
                }
            )
        }
    }
}


@Preview
@Composable
private fun ConfirmExitBottomSheetPrev() {
    ConfirmExitBottomSheetContent(exitGame = {}) { }
}
