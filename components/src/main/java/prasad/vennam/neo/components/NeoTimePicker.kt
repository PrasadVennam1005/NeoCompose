package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Neumorphic Time Picker component (`[HH] : [MM] [AM/PM]`).
 *
 * @param hour Selected hour integer (1..12).
 * @param minute Selected minute integer (0..59).
 * @param isAm Whether time is AM (true) or PM (false).
 * @param onHourChange Callback when hour changes.
 * @param onMinuteChange Callback when minute changes.
 * @param onAmPmToggle Callback when AM/PM is toggled.
 * @param modifier Custom modifier.
 * @param shape Card shape.
 * @param style Surface visual style.
 * @param elevation Shadow displacement depth.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specifications.
 */
@Composable
public fun NeoTimePicker(
    hour: Int,
    minute: Int,
    isAm: Boolean,
    onHourChange: (Int) -> Unit,
    onMinuteChange: (Int) -> Unit,
    onAmPmToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec()
) {
    NeoCard(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        style = style,
        elevation = elevation,
        colors = colors,
        lightSource = lightSource,
        animationSpec = animationSpec
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Hour Stepper
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Hour", style = NeoTheme.typography.caption, color = colors.textSecondary)
                NeoNumberStepper(
                    value = hour,
                    onValueChange = onHourChange,
                    range = 1..12,
                    lightSource = lightSource
                )
            }

            Text(":", style = NeoTheme.typography.display, color = colors.textPrimary)

            // Minute Stepper
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Minute", style = NeoTheme.typography.caption, color = colors.textSecondary)
                NeoNumberStepper(
                    value = minute,
                    onValueChange = onMinuteChange,
                    range = 0..59,
                    step = 5,
                    lightSource = lightSource
                )
            }

            // AM / PM Segmented Toggle
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Period", style = NeoTheme.typography.caption, color = colors.textSecondary)
                NeoChip(
                    label = if (isAm) "AM" else "PM",
                    selected = true,
                    onClick = { onAmPmToggle(!isAm) },
                    lightSource = lightSource
                )
            }
        }
    }
}

@Preview(name = "NeoTimePicker Preview")
@Composable
private fun NeoTimePickerPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoTimePicker(
                hour = 10,
                minute = 30,
                isAm = true,
                onHourChange = {},
                onMinuteChange = {},
                onAmPmToggle = {}
            )
        }
    }
}
