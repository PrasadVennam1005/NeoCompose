package prasad.vennam.neo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme
import kotlin.math.roundToInt

/**
 * High-visibility interactive Neumorphic range slider component with active progress fill bar.
 *
 * @param value Current slider value.
 * @param onValueChange Callback when slider value changes.
 * @param modifier Custom modifier.
 * @param enabled Whether slider is interactive.
 * @param valueRange Value range bounds.
 * @param shape Track shape.
 * @param style Base track style (defaults to [NeoStyle.Inset]).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specifications.
 * @param interactionSource Interaction stream.
 */
@Composable
public fun NeoSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    shape: Shape = CircleShape,
    style: NeoStyle = NeoStyle.Inset,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val rangeLength = valueRange.endInclusive - valueRange.start
    val normalizedValue = if (rangeLength > 0f) {
        ((value - valueRange.start) / rangeLength).coerceIn(0f, 1f)
    } else 0f

    val density = LocalDensity.current

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(28.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        val totalWidthPx = constraints.maxWidth.toFloat()
        val thumbSizePx = with(density) { 26.dp.toPx() }
        val maxOffsetPx = (totalWidthPx - thumbSizePx).coerceAtLeast(0f)
        val currentOffsetPx = normalizedValue * maxOffsetPx

        // Recessed track background groove
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .neoStyle(
                    style = style,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = NeoTheme.lighting.lightSource,
                    borderWidth = 1.dp,
                    borderColor = colors.border.copy(alpha = 0.4f)
                )
                .pointerInput(enabled, valueRange, maxOffsetPx) {
                    if (enabled && maxOffsetPx > 0f) {
                        detectTapGestures { offset ->
                            val newNormalized = (offset.x / maxOffsetPx).coerceIn(0f, 1f)
                            onValueChange(valueRange.start + newNormalized * rangeLength)
                        }
                    }
                },
            contentAlignment = Alignment.CenterStart
        ) {
            // Active progress fill bar inside track
            val fillWidthDp = with(density) { (currentOffsetPx + thumbSizePx / 2f).toDp() }
            Box(
                modifier = Modifier
                    .width(fillWidthDp)
                    .fillMaxHeight()
                    .clip(shape)
                    .background(colors.primary.copy(alpha = 0.85f))
            )
        }

        // Draggable elevated thumb
        Box(
            modifier = Modifier
                .offset { IntOffset(currentOffsetPx.roundToInt(), 0) }
                .size(26.dp)
                .neoStyle(
                    style = NeoStyle.Raised,
                    shape = CircleShape,
                    backgroundColor = colors.primary,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = NeoTheme.elevation.level3,
                    lightSource = NeoTheme.lighting.lightSource,
                    borderWidth = 1.5.dp,
                    borderColor = colors.onPrimary.copy(alpha = 0.5f)
                )
                .pointerInput(enabled, valueRange, maxOffsetPx) {
                    if (enabled && maxOffsetPx > 0f) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            val deltaNormalized = dragAmount.x / maxOffsetPx
                            val currentNormalized = ((value - valueRange.start) / rangeLength).coerceIn(0f, 1f)
                            val newNormalized = (currentNormalized + deltaNormalized).coerceIn(0f, 1f)
                            onValueChange(valueRange.start + newNormalized * rangeLength)
                        }
                    }
                }
        )
    }
}
