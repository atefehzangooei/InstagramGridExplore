package com.appcoding.loadinganimation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun OrbitingDotsLoader() {
    val transition = rememberInfiniteTransition(label = "")

    val angles = List(3) { index ->
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(1200, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ), label = ""
        )
    }

    Box(
        modifier = Modifier.size(60.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color.Gray, shape = CircleShape)
        )
        angles.forEachIndexed { index, angle ->
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .offset(
                        x = (25 * cos(Math.toRadians(angle.value + index * 120.0))).dp,
                        y = (25 * sin(Math.toRadians(angle.value + index * 120.0))).dp
                    )
                    .background(Color.Green, shape = CircleShape)
            )
        }
    }
}
