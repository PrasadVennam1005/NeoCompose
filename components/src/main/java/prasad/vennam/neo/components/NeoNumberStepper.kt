package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Interactive Neumorphic number stepper counter control matching Dribbble designs.
 *
 * @param value Current numerical integer value.
 * @param onValueChange Callback when value is incremented or decremented.
 * @param modifier Custom modifier.
 * @param enabled Whether control is interactive.
 * @param range Minimum and maximum allowable integer bounds.
 * @param step Amount to increment/decrement per click.
 * @param shape Outer pill container shape.
 * @param style Base inset groove style.
 * @param elevation Shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specs.
 */
@Composable
public fun NeoNumberStepper(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    range: IntRange = 0..99,
    step: Int = 1,
    shape: Shape = NeoTheme.shapes.pill,
    style: NeoStyle = NeoStyle.Inset,
    elevation: Dp = NeoTheme.elevation.level2,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
) {
    val canDecrement = enabled && value - step >= range.first
    val canIncrement = enabled && value + step <= range.last

    Box(
        modifier =
            modifier
                .neoStyle(
                    style = style,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = lightSource,
                )
                .padding(NeoTheme.spacing.extraSmall),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
        ) {
            // Decrement Button (-)
            NeoIconButton(
                onClick = { if (canDecrement) onValueChange(value - step) },
                enabled = canDecrement,
                size = NeoTheme.size.controlSmall,
                lightSource = lightSource,
            ) {
                Text(
                    text = "−",
                    style = NeoTheme.typography.title,
                    color = if (canDecrement) colors.primary else colors.textSecondary.copy(alpha = 0.5f),
                )
            }

            // Value Display
            Text(
                text = value.toString(),
                style = NeoTheme.typography.title,
                color = colors.textPrimary,
                modifier = Modifier.padding(horizontal = NeoTheme.spacing.small),
            )

            // Increment Button (+)
            NeoIconButton(
                onClick = { if (canIncrement) onValueChange(value + step) },
                enabled = canIncrement,
                size = NeoTheme.size.controlSmall,
                lightSource = lightSource,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increment",
                    tint = if (canIncrement) colors.primary else colors.textSecondary.copy(alpha = 0.5f),
                )
            }
        }
    }
}

@Preview(name = "NeoNumberStepper Preview")
@Composable
private fun NeoNumberStepperPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoNumberStepper(
                value = 5,
                onValueChange = {},
            )
        }
    }
}
