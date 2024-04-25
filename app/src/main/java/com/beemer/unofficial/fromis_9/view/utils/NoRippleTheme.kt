package com.beemer.unofficial.fromis_9.view.utils

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import com.beemer.unofficial.fromis_9.ui.theme.Transparent

object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(0F, 0F, 0F, 0F)
}