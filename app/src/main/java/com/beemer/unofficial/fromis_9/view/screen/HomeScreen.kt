package com.beemer.unofficial.fromis_9.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.beemer.unofficial.fromis_9.R
import com.beemer.unofficial.fromis_9.ui.theme.DarkGray
import com.beemer.unofficial.fromis_9.ui.theme.NanumSquareRoundExtraBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
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
                IconButton(
                    onClick = {
                        navController.navigate(Screen.Settings.route)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_clover),
                        contentDescription = "설정"
                    )
                }
            }
        )

        HomeCard(
            imageResource = R.drawable.image_fromis9,
            text = "프로미스나인",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            onClick = {

            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    bottom = 16.dp
                )
        ) {
            HomeCard(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 8.dp
                    )
                    .weight(1f),
                imageResource = R.drawable.image_album,
                text = "앨범",
                onClick = {
                    navController.navigate(Screen.AlbumList.route)
                }
            )

            HomeCard(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 16.dp
                    )
                    .weight(1f),
                imageResource = R.drawable.image_flover,
                text = "응원법",
                onClick = {

                }
            )
        }
    }
}

@Composable
fun HomeCard(imageResource: Int, text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = onClick
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = imageResource),
            contentDescription = null,
        )
        HomeText(text = text)
    }
}

@Composable
fun HomeText(text: String) {
    Text(
        text = text,
        color = DarkGray,
        fontSize = 20.sp,
        fontFamily = NanumSquareRoundExtraBold,
        modifier = Modifier.padding(16.dp)
    )
}