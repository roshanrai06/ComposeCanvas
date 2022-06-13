package com.example.composecanvas.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt
import kotlin.random.Random

@Composable
fun BallClicker(
    radius: Float = 100f,
    enable: Boolean = false,
    ballColor: Color = Color.Red,
    onBallClick: () -> Unit = {}
) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        var ballPosition by remember {
            mutableStateOf(
                randomOffset(
                    radius = radius,
                    width = constraints.maxWidth,
                    height = constraints.maxHeight
                )
            )
        }
        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(enable) {
                if (!enable) {
                    return@pointerInput
                }
                detectTapGestures {
                    val distance =
                        sqrt((it.x - ballPosition.x).pow(2) + (it.y - ballPosition.y).pow(2))
                    if (distance < radius) {
                        ballPosition = randomOffset(
                            radius = radius,
                            width = constraints.maxWidth,
                            height = constraints.maxHeight
                        )
                        onBallClick()
                    }
                }
            }) {
            drawCircle(
                color = ballColor,
                radius = radius,
                center = ballPosition
            )

        }
    }

}

private fun randomOffset(radius: Float, width: Int, height: Int): Offset {
    return Offset(
        x = Random.nextInt(radius.roundToInt(), width - radius.roundToInt()).toFloat(),
        y = Random.nextInt(radius.roundToInt(), height - radius.roundToInt()).toFloat()
    )
}