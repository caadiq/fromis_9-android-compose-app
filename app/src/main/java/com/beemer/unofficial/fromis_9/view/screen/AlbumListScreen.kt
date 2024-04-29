package com.beemer.unofficial.fromis_9.view.screen

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.beemer.unofficial.fromis_9.R
import com.beemer.unofficial.fromis_9.model.dto.AlbumListDto
import com.beemer.unofficial.fromis_9.ui.theme.DarkGray
import com.beemer.unofficial.fromis_9.ui.theme.Gray
import com.beemer.unofficial.fromis_9.ui.theme.NanumSquareRoundBold
import com.beemer.unofficial.fromis_9.ui.theme.NanumSquareRoundExtraBold
import com.beemer.unofficial.fromis_9.ui.theme.Primary
import com.beemer.unofficial.fromis_9.ui.theme.Surface
import com.beemer.unofficial.fromis_9.ui.theme.Transparent
import com.beemer.unofficial.fromis_9.view.utils.NoRippleTheme
import com.beemer.unofficial.fromis_9.viewmodel.AlbumViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumListScreen() {
    val albumViewModel: AlbumViewModel = hiltViewModel()
    val albumList = albumViewModel.albumList.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        albumViewModel.getAlbumList()
    }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val items = listOf("발매일", "타이틀", "분류")
                        val isDescending = albumViewModel.isDescending.collectAsState().value

                        Spacer(modifier = Modifier.weight(1f))
                        MaterialButtonToggleGroup(
                            items = items,
                            albumViewModel = albumViewModel,
                            onClick = { index ->
                                albumViewModel.setSortBy(index)
                            }
                        )
                        IconToggleButton(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .weight(1f),
                            checked = isDescending,
                            onCheckedChange = { descending ->
                                albumViewModel.setDescending(descending)
                            }
                        ) {
                            Icon(
                                painter = if (isDescending) painterResource(id = R.drawable.icon_descending) else painterResource(id = R.drawable.icon_ascending),
                                contentDescription = "정렬",
                                tint = Gray
                            )
                        }
                    }
                }
                itemsIndexed(
                    items = albumList ?: emptyList(),
                    key = { _, album ->
                        album.albumName
                    }
                ) { _, album ->
                    AlbumListItem(
                        album = album,
                        modifier = Modifier.animateItemPlacement()
                    )
                }
            }
        }
    }
}

@Composable
fun MaterialButtonToggleGroup(
    items: List<String>,
    albumViewModel: AlbumViewModel,
    onClick: (index: Int) -> Unit = {}
) {
    val cornerRadius = 4.dp
    val selectedIndex = albumViewModel.sortBy.collectAsState().value

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

@Composable
fun AlbumListItem(album: AlbumListDto, modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxHeight()
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
                imageModel = { album.cover },
                requestOptions = {
                    RequestOptions()
                        .placeholder(ColorDrawable(Transparent.toArgb()))
                        .sizeMultiplier(0.8f)
                },
                requestBuilder = {
                    Glide.with(LocalContext.current)
                        .asDrawable()
                        .transition(DrawableTransitionOptions.withCrossFade())
                },
            )

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 8.dp, top = 16.dp)
                        .background(
                            color = Color("#${album.colorPrimary}".toColorInt()),
                            shape = RoundedCornerShape(4.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = album.type,
                        fontSize = 12.sp,
                        fontFamily = NanumSquareRoundBold,
                        color = Color("#${album.colorSecondary}".toColorInt()),
                        style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .weight(1f),
                    text = album.albumName,
                    fontSize = 22.sp,
                    fontFamily = NanumSquareRoundExtraBold,
                    color = DarkGray,
                    maxLines = 3,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(8.dp),
                    text = album.release,
                    fontSize = 14.sp,
                    fontFamily = NanumSquareRoundBold,
                    color = Gray,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                )
            }
        }
    }
}