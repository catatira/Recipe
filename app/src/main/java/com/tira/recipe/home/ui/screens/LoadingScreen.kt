package com.tira.recipe.home.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import com.tira.recipe.ui.theme.Dimensions
import com.tira.recipe.ui.theme.Dimensions.paddingMedium

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "alphaAnimation"
    )
    val scaleAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.5f,
        animationSpec = tween(durationMillis = 1000),
        label = "scaleAnimation"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .alpha(alphaAnimation),
        contentAlignment = Alignment.TopCenter
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(Dimensions.iconSizeLarge)
                .scale(scaleAnimation)
                .padding(top = paddingMedium),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
