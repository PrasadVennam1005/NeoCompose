package prasad.vennam.neo.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * Interactive Neumorphic Circular Slider / Dial component.
 *
 * @param value Current slider value.
 * @param onValueChange Callback when the value changes during drag or tap.
 * @param modifier Custom modifier.
 * @param enabled Whether interaction is active.
 * @param valueRange Bounds of the slider value.
 * @param style Base track style (defaults to [NeoStyle.Inset]).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Spec for animated rendering transitions.
 * @param interactionSource Interaction stream.
 */
@Composable
public fun NeoCircularSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    style: NeoStyle = NeoStyle.Inset,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val density = LocalDensity.current
    val haptic = LocalHapticFeedback.current

    // Sizes
    val controlSize = NeoTheme.size.controlLarge * 2.5f
    val thumbSize = NeoTheme.size.thumbSizeLarge
    val trackStrokeWidth = NeoTheme.spacing.extraSmall

    val rangeLength = valueRange.endInclusive - valueRange.start
    val normalizedValue = if (rangeLength > 0f) {
        ((value - valueRange.start) / rangeLength).coerceIn(0f, 1f)
    } else {
        0f
    }

    // Dynamic haptic feedback
    val stepSize = 0.05f
    var lastTickStep by remember { mutableIntStateOf((normalizedValue / stepSize).roundToInt()) }
    val currentStep = (normalizedValue / stepSize).roundToInt()
    if (enabled && currentStep != lastTickStep) {
        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
        lastTickStep = currentStep
    }

    val animatedValue by animateFloatAsState(
        targetValue = normalizedValue,
        animationSpec = animationSpec.lightSourceSpec,
        label = "NeoCircularSliderAnimation"
    )

    BoxWithConstraints(
        modifier = modifier
            .size(controlSize)
            .neoStyle(
                style = style,
                shape = CircleShape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = lightSource,
            ),
        contentAlignment = Alignment.Center
    ) {
        val sizePx = constraints.maxWidth.toFloat()
        val radiusPx = sizePx / 2f
        val thumbSizePx = with(density) { thumbSize.toPx() }
        val paddingPx = with(density) { NeoTheme.spacing.small.toPx() }
        val activeRadius = radiusPx - (thumbSizePx / 2f) - paddingPx

        // Progress Track Arc drawing
        Canvas(modifier = Modifier.size(controlSize)) {
            val stroke = Stroke(width = trackStrokeWidth.toPx(), cap = StrokeCap.Round)
            val arcSize = sizePx - (thumbSizePx + paddingPx * 2)
            drawArc(
                color = colors.primary.copy(alpha = 0.3f),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(thumbSizePx / 2f + paddingPx, thumbSizePx / 2f + paddingPx),
                size = androidx.compose.ui.geometry.Size(arcSize, arcSize),
                style = stroke
            )
            drawArc(
                color = colors.primary,
                startAngle = -90f,
                sweepAngle = animatedValue * 360f,
                useCenter = false,
                topLeft = Offset(thumbSizePx / 2f + paddingPx, thumbSizePx / 2f + paddingPx),
                size = androidx.compose.ui.geometry.Size(arcSize, arcSize),
                style = stroke
            )
        }

        // Draggable Thumb Container
        val interactionModifier = if (enabled) {
            Modifier.pointerInput(valueRange) {
                fun handleTouch(position: Offset) {
                    val angleRad = atan2(position.y - radiusPx, position.x - radiusPx)
                    // Convert -PI..PI range to 0..2*PI starting from -PI/2 (top)
                    var angle = angleRad + (PI / 2f).toFloat()
                    if (angle < 0f) {
                        angle += (2f * PI).toFloat()
                    }
                    val progress = (angle / (2f * PI).toFloat()).coerceIn(0f, 1f)
                    val newValue = valueRange.start + progress * rangeLength
                    onValueChange(newValue)
                }

                detectTapGestures(onPress = { offset -> handleTouch(offset) })
            }.pointerInput(valueRange) {
                detectDragGestures { change, _ ->
                    change.consume()
                    val position = change.position
                    val angleRad = atan2(position.y - radiusPx, position.x - radiusPx)
                    var angle = angleRad + (PI / 2f).toFloat()
                    if (angle < 0f) {
                        angle += (2f * PI).toFloat()
                    }
                    val progress = (angle / (2f * PI).toFloat()).coerceIn(0f, 1f)
                    val newValue = valueRange.start + progress * rangeLength
                    onValueChange(newValue)
                }
            }
        } else {
            Modifier
        }

        // Calculate thumb coordinates
        val currentAngle = -PI / 2f + animatedValue * 2f * PI
        val thumbOffsetXPx = radiusPx + activeRadius * cos(currentAngle) - (thumbSizePx / 2f)
        val thumbOffsetYPx = radiusPx + activeRadius * sin(currentAngle) - (thumbSizePx / 2f)

        Box(
            modifier = interactionModifier
                .size(controlSize)
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(thumbOffsetXPx.roundToInt(), thumbOffsetYPx.roundToInt()) }
                    .size(thumbSize)
                    .neoStyle(
                        style = NeoStyle.Raised,
                        shape = CircleShape,
                        backgroundColor = colors.surface,
                        lightColor = colors.lightShadow,
                        darkColor = colors.darkShadow,
                        elevation = elevation * 0.75f,
                        lightSource = lightSource,
                    )
            )
        }
    }
}
