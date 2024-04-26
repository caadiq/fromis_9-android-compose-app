package com.beemer.unofficial.fromis_9.view.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.beemer.unofficial.fromis_9.R
import com.beemer.unofficial.fromis_9.ui.theme.Gray
import com.beemer.unofficial.fromis_9.ui.theme.NanumSquareRoundExtraBold
import com.beemer.unofficial.fromis_9.ui.theme.Primary
import com.beemer.unofficial.fromis_9.ui.theme.Surface
import com.beemer.unofficial.fromis_9.ui.theme.Transparent
import com.beemer.unofficial.fromis_9.view.utils.NoRippleTheme

@Composable
fun AlbumListScreen() {
    val items = listOf("발매일", "타이틀", "분류")
    val (isChecked, setChecked) = remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth()
                    .height(44.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                MaterialButtonToggleGroup(
                    items = items,
                    onClick = { index ->

                    }
                )
                IconToggleButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .weight(1f),
                    checked = isChecked,
                    onCheckedChange = { setChecked(!isChecked) }
                ) {
                    Icon(
                        painter = if (isChecked) painterResource(id = R.drawable.icon_ascending) else painterResource(id = R.drawable.icon_descending),
                        contentDescription = "정렬",
                        tint = Gray
                    )
                }
            }
        }
    }
}

@Composable
fun MaterialButtonToggleGroup(
    items: List<String>,
    onClick: (index: Int) -> Unit = {}
) {
    val cornerRadius = 4.dp
    val (selectedIndex, onIndexSelected) = remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier.height(56.dp)
    ) {
        items.forEachIndexed { index, item ->
            OutlinedButton(
                modifier = when (index) {
                    0 -> Modifier
                            .width(88.dp)
                            .fillMaxHeight()
                            .offset(0.dp, 0.dp)
                            .zIndex(if (selectedIndex == index) 1f else 0f)
                    1 -> Modifier
                            .width(88.dp)
                            .fillMaxHeight()
                            .offset((-1).dp, 0.dp)
                            .zIndex(if (selectedIndex == index) 1f else 0f)
                    else -> Modifier
                            .width(88.dp)
                            .fillMaxHeight()
                            .offset((-2.2).dp, 0.dp)
                            .zIndex(if (selectedIndex == index) 1f else 0f)
                },
                onClick = {
                    onIndexSelected(index)
                    onClick(index)
                },
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStart = cornerRadius,
                        topEnd = 0.dp,
                        bottomStart = cornerRadius,
                        bottomEnd = 0.dp
                    )
                    items.size - 1 -> RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = cornerRadius,
                        bottomStart = 0.dp,
                        bottomEnd = cornerRadius
                    )
                    else -> RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                },
                border = BorderStroke(
                    1.dp, if (selectedIndex == index) {
                        Primary
                    } else {
                        Gray
                    }
                ),
                colors = if (selectedIndex == index) {
                    ButtonDefaults.outlinedButtonColors(
                        containerColor = Transparent,
                        contentColor = Primary
                    )
                } else {
                    ButtonDefaults.outlinedButtonColors(
                        containerColor = Surface,
                        contentColor = Primary
                    )
                },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = item,
                    fontSize = 14.sp,
                    fontFamily = NanumSquareRoundExtraBold,
                    color = if (selectedIndex == index) {
                        Primary
                    } else {
                        Gray
                    }
                )
            }
        }
    }
}