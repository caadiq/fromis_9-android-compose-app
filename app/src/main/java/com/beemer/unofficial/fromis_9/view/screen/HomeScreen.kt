package com.beemer.unofficial.fromis_9.view.screen

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.beemer.unofficial.fromis_9.R
import com.beemer.unofficial.fromis_9.ui.theme.DarkGray
import com.beemer.unofficial.fromis_9.ui.theme.NanumSquareRoundExtraBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "fromis_9",
                color = DarkGray,
                fontSize = 26.sp,
                fontFamily = NanumSquareRoundExtraBold
            )
        },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_clover),
                    contentDescription = "앱 정보"
                )
            }
        }
    )
}