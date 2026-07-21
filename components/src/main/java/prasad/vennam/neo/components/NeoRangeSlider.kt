package prasad.vennam.neo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme
import kotlin.math.roundToInt

/**
 * Dual-thumb Neumorphic range slider component for minimum/maximum range selection.
 *
 * @param values Current selected range value interval.
 * @param onValuesChange Callback when start or end range value changes.
 * @param modifier Custom modifier.
 * @param enabled Whether range slider is interactive.
 * @param valueRange Overall total allowable range bounds.
 * @param shape Track shape.
 * @param style Base track style (defaults to [NeoStyle.Inset]).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specifications.
 */
@Composable
public fun NeoRangeSlider(
    values: ClosedFloatingPointRange<Float>,
    onValuesChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    shape: Shape = CircleShape,
    style: NeoStyle = NeoStyle.Inset,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec()
) {
    val totalSpan = valueRange.endInclusive - valueRange.start
    val startNorm = if (totalSpan > 0f) ((values.start - valueRange.start) / totalSpan).coerceIn(0f, 1f) else 0f
    val endNorm = if (totalSpan > 0f) ((values.endInclusive - valueRange.start) / totalSpan).coerceIn(0f, 1f) else 1f

    val density = LocalDensity.current
    val thumbSizeDp = NeoTheme.size.thumbSizeLarge
    val trackHeightDp = NeoTheme.size.trackHeightSlim
    val containerHeightDp = NeoTheme.size.trackHeightMedium

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(containerHeightDp),
        contentAlignment = Alignment.CenterStart
    ) {
        val totalWidthPx = constraints.maxWidth.toFloat()
        val thumbSizePx = with(density) { thumbSizeDp.toPx() }
        val maxOffsetPx = (totalWidthPx - thumbSizePx).coerceAtLeast(0f)

        val startPx = startNorm * maxOffsetPx
        val endPx = endNorm * maxOffsetPx

        // Recessed track background groove
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(trackHeightDp)
                .neoStyle(
                    style = style,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = lightSource,
                    borderWidth = NeoTheme.size.borderThin,
                    borderColor = colors.border.copy(alpha = 0.4f)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            // Active filled segment between start and end thumbs
            val activeWidthDp = with(density) { (endPx - startPx + thumbSizePx).toDp() }
            val activeOffsetDp = with(density) { startPx.toDp() }

            Box(
                modifier = Modifier
                    .offset(x = activeOffsetDp)
                    .width(activeWidthDp)
                    .fillMaxHeight()
                    .clip(shape)
                    .background(colors.primary.copy(alpha = 0.85f))
            )
        }

        // Start Thumb
        Box(
            modifier = Modifier
                .offset { IntOffset(startPx.roundToInt(), 0) }
                .size(thumbSizeDp)
                .neoStyle(
                    style = NeoStyle.Raised,
                    shape = CircleShape,
                    backgroundColor = colors.primary,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = NeoTheme.elevation.level3,
                    lightSource = lightSource,
                    borderWidth = NeoTheme.size.borderMedium,
                    borderColor = colors.onPrimary.copy(alpha = 0.5f)
                )
                .pointerInput(enabled, valueRange, maxOffsetPx) {
                    if (enabled && maxOffsetPx > 0f) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            val deltaNorm = dragAmount.x / maxOffsetPx
                            val newStartNorm = (startNorm + deltaNorm).coerceIn(0f, endNorm)
                            val newStartVal = valueRange.start + newStartNorm * totalSpan
                            onValuesChange(newStartVal..values.endInclusive)
                        }
                    }
                }
        )

        // End Thumb
        Box(
            modifier = Modifier
                .offset { IntOffset(endPx.roundToInt(), 0) }
                .size(thumbSizeDp)
                .neoStyle(
                    style = NeoStyle.Raised,
                    shape = CircleShape,
                    backgroundColor = colors.primary,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = NeoTheme.elevation.level3,
                    lightSource = lightSource,
                    borderWidth = NeoTheme.size.borderMedium,
                    borderColor = colors.onPrimary.copy(alpha = 0.5f)
                )
                .pointerInput(enabled, valueRange, maxOffsetPx) {
                    if (enabled && maxOffsetPx > 0f) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            val deltaNorm = dragAmount.x / maxOffsetPx
                            val newEndNorm = (endNorm + deltaNorm).coerceIn(startNorm, 1f)
                            val newEndVal = valueRange.start + newEndNorm * totalSpan
                            onValuesChange(values.start..newEndVal)
                        }
                    }
                }
        )
    }
}

@Preview(name = "NeoRangeSlider Preview")
@Composable
private fun NeoRangeSliderPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoRangeSlider(
                values = 0.2f..0.8f,
                onValuesChange = {}
            )
        }
    }
}
