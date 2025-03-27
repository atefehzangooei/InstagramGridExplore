package com.appcoding.loadinganimation

import android.graphics.Paint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun SpiralLoader() {
    val transition = rememberInfiniteTransition(label = "")

    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        ), label = ""
    )

    Canvas(modifier = Modifier.size(100.dp)) {
        val paint = Paint().apply {
            // color = Color.Magenta
            strokeWidth = 4f
        }
        val center = Offset(50f, 50f)
        val radius = 40f

        // Draw spiral effect
        for (i in 0..10) {
            val angle = (i * 36 + rotation) % 360
            val x = center.x + radius * cos(Math.toRadians(angle.toDouble())).toFloat()
            val y = center.y + radius * sin(Math.toRadians(angle.toDouble())).toFloat()
            drawCircle(color = Color.Cyan, radius = 8f, center = Offset(x, y))
        }
    }
}
