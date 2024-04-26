package com.beemer.unofficial.fromis_9.view.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beemer.unofficial.fromis_9.R
import com.beemer.unofficial.fromis_9.ui.theme.DarkGray
import com.beemer.unofficial.fromis_9.ui.theme.Gray
import com.beemer.unofficial.fromis_9.ui.theme.LightGray
import com.beemer.unofficial.fromis_9.ui.theme.LighterGray
import com.beemer.unofficial.fromis_9.ui.theme.NanumSquareRoundBold
import com.beemer.unofficial.fromis_9.ui.theme.NanumSquareRoundExtraBold
import com.beemer.unofficial.fromis_9.ui.theme.Primary
import com.beemer.unofficial.fromis_9.ui.theme.White

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SettingsAppIcon(modifier = Modifier.align(Alignment.CenterHorizontally))
        SettingsInfo()
        SettingsEtc()
    }
}

@Composable
fun SettingsAppIcon(modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(top = 40.dp)
            .size(104.dp)
            .background(color = Primary, shape = RoundedCornerShape(40.dp))
    ) {
        Image(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.icon_fromis9),
            contentDescription = ""
        )
    }
}

@Composable
fun SettingsInfo() {
    SettingsText(
        text = "정보",
        color = DarkGray,
        modifier = Modifier.padding(start = 48.dp, top = 48.dp)
    )

    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 12.dp)
            .fillMaxWidth()
            .background(color = LighterGray, shape = RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
        ) {
            SettingsText(
                text = "버전",
                color = DarkGray,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
            )
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                color = LightGray,
                thickness = 1.dp
            )
            SettingsText(
                text = "1.0.0",
                color = Gray,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .weight(1f)
            )
            SettingsButton(
                text = "업데이트",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(width = 72.dp, height = 36.dp)
                    .align(Alignment.CenterVertically),
                enabled = false,
                onClick = {}
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            color = LightGray,
            thickness = 1.dp
        )

        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
        ) {
            SettingsText(
                text = "캐시",
                color = DarkGray,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
            )
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                color = LightGray,
                thickness = 1.dp
            )
            SettingsText(
                text = "12.34MB",
                color = Gray,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .weight(1f)
            )
            SettingsButton(
                text = "캐시 삭제",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(width = 72.dp, height = 36.dp)
                    .align(Alignment.CenterVertically),
                enabled = true,
                onClick = {}
            )
        }
    }
}

@Composable
fun SettingsEtc() {
    SettingsText(
        text = "기타",
        color = DarkGray,
        modifier = Modifier.padding(start = 48.dp, top = 48.dp)
    )

    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 12.dp)
            .fillMaxWidth()
            .background(color = LighterGray, shape = RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = {})
        ) {
            SettingsText(
                text = "라이선스",
                color = DarkGray,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 20.dp),
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            color = LightGray,
            thickness = 1.dp
        )

        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable(onClick = {})
        ) {
            SettingsText(
                text = "체인지로그",
                color = DarkGray,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
            )
        }
    }
}

@Composable
fun SettingsText(text: String, color: Color, modifier: Modifier) {
    Text(
        text = text,
        color = color,
        fontSize = 20.sp,
        fontFamily = NanumSquareRoundExtraBold,
        modifier = modifier
    )
}

@Composable
fun SettingsButton(text: String, enabled: Boolean, modifier: Modifier, onClick: () -> Unit) {
    val color = if (enabled) Primary else Gray

    Button(
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(4.dp),
        border = (
            BorderStroke(
                width = 1.dp,
                color = color,
            )
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = White,
            contentColor = Primary,
            disabledContainerColor = White,
            disabledContentColor = Gray
        ),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            color = color,
            fontSize = 12.sp,
            fontFamily = NanumSquareRoundBold
        )
    }
}