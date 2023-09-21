package com.praveen.spacexapp.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedCircularProgressIndicator(
    currentValue: Int,
    maxValue: Int,
    progressBackgroundColor: Color,
    progressIndicatorColor: Color,
    completedColor: Color,
    modifier: Modifier = Modifier.padding(5.dp)) {

    val stroke = with(LocalDensity.current) {
        Stroke(width = 25.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Miter)
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center)
    {
        ProgressStatus(
            currentValue = currentValue,
            maxValue = maxValue,
            progressBackgroundColor = progressBackgroundColor,
            progressIndicatorColor = progressIndicatorColor,
            completedColor = completedColor
        )

        val animateFloat = remember { Animatable(0f) }
        LaunchedEffect(animateFloat) {
            animateFloat.animateTo(
                targetValue = currentValue / maxValue.toFloat(),
                animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
            )
        }
        val gradientColors: List<Color> = listOf(
        Color(0xFF6ce0c4),
        Color(0xFF40c7e7),
        Color(0xFF6ce0c4),
        Color(0xFF40c7e7)
        )
        val animateNumber = animateFloatAsState(
            targetValue = 50f,
            animationSpec = tween(
                durationMillis = 2000,
                delayMillis = 0
            )
        )



        Canvas(
            Modifier
                .progressSemantics(currentValue / maxValue.toFloat())
                .size(150.dp)
        ) {
            // Start at 12 O'clock
            val startAngle = 270f
            val sweep: Float = animateFloat.value * 360f
            val diameterOffset = stroke.width / 2

            val progress = (animateNumber.value / 100) * size.width // size.width returns the width of the canvas

            drawCircle(
                color = progressBackgroundColor,
                style = stroke,
                radius = size.minDimension / 2.0f - diameterOffset
            )

            drawLine(
                color = progressBackgroundColor,
                cap = StrokeCap.Round,
                strokeWidth = 12.dp.toPx(),
                start = Offset(x = size.width/2, y = 0f),
                end = Offset(x = size.width/2, y = size.height)
            )

            drawLine(
                /*brush = Brush.linearGradient(
                    colors = gradientColors
                ),
                */cap = StrokeCap.Round,
                strokeWidth =  12.dp.toPx(),//size.height,
                start = Offset(x = size.width/2, y = 0f),
                end = Offset(x = size.width/2, y = progress),
            color=progressIndicatorColor
            )


            drawCircularProgressIndicator(startAngle, sweep, progressIndicatorColor, stroke)
            /*if (currentValue == maxValue) {
                drawCircle(
                    color = completedColor,
                    style = stroke,
                    radius = size.minDimension / 2.0f - diameterOffset
                )
            }*/
        }
    }
}

@Composable
private fun ProgressStatus(
    currentValue: Int,
    maxValue: Int,
    progressBackgroundColor: Color,
    progressIndicatorColor: Color,
    completedColor: Color, modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = buildAnnotatedString {
        val emphasisSpan =
            MaterialTheme.typography.titleLarge.copy(color = if (currentValue == maxValue) completedColor else progressIndicatorColor)
                .toSpanStyle()
        val defaultSpan =
            MaterialTheme.typography.bodyMedium.copy(color = progressBackgroundColor).toSpanStyle()
        append(AnnotatedString("$currentValue", spanStyle = emphasisSpan))
        append(AnnotatedString(text = "/", spanStyle = defaultSpan))
        append(AnnotatedString(text = "$maxValue", spanStyle = defaultSpan))
    }
    )
}
private fun DrawScope.drawCircularProgressIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke) {
    // To draw this circle we need a rect with edges that line up with the midpoint of the stroke.
    // To do this we need to remove half the stroke width from the total diameter for both sides.
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke
    )
}