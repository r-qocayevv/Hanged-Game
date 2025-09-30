package com.revan.hanged.presentation.game.components

import android.R.attr.textColor
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.clickWithDarkGrayRipple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmExitBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss : () -> Unit,
    exitGame : () -> Unit
) {
    ModalBottomSheet(
        containerColor = Color(0xFF2E3740),
        onDismissRequest = {
            onDismiss()
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .navigationBarsPadding(),

        ) {
        ConfirmExitBottomSheetContent(exitGame = exitGame,onDismiss = onDismiss)
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

        Row (
            modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp).navigationBarsPadding().padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){



            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickWithDarkGrayRipple(
                        isButtonEnabled = true,
                        onClick = {
                            onDismiss()
                        }
                    )
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(width = 1.dp, color = Red, shape = RoundedCornerShape(10.dp))
                    .padding(vertical = 10.dp, horizontal = 10.dp)


            ) {
                Text(
                    text = stringResource(R.string.stay),
                    modifier = Modifier,
                    color = Red,
                    fontSize = 15.sp,
                )
            }

            CustomButton(
                modifier = Modifier.weight(1f),
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
