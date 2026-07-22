package prasad.vennam.neo.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.components.NeoCard
import prasad.vennam.neo.components.NeoSlider
import prasad.vennam.neo.components.NeoSwitch
import prasad.vennam.neo.components.NeoDropdownMenu
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoTheme
import kotlin.math.roundToInt

@Composable
public fun StudioControls(
    state: StudioState,
    modifier: Modifier = Modifier
) {
    NeoCard(
        modifier = modifier.fillMaxWidth(),
        style = state.selectedStyle,
        specularHighlight = state.isGlossyShineEnabled
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
            Text(
                text = "Neumorphism Studio Controls",
                style = NeoTheme.typography.title,
                color = NeoTheme.colors.textPrimary
            )

            // Theme Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (state.isDarkTheme) "Dark Mode" else "Light Mode",
                    style = NeoTheme.typography.body,
                    color = NeoTheme.colors.textPrimary
                )
                NeoSwitch(
                    checked = state.isDarkTheme,
                    onCheckedChange = { state.isDarkTheme = it }
                )
            }

            // Accessibility Mode Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Accessibility Contrast Mode",
                    style = NeoTheme.typography.body,
                    color = NeoTheme.colors.textPrimary
                )
                NeoSwitch(
                    checked = state.isAccessibilityMode,
                    onCheckedChange = { state.isAccessibilityMode = it }
                )
            }

            // Sensor Lighting Mode Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sensor Lighting Mode (Accelerometer)",
                    style = NeoTheme.typography.body,
                    color = NeoTheme.colors.textPrimary
                )
                NeoSwitch(
                    checked = state.isSensorLightingEnabled,
                    onCheckedChange = { state.isSensorLightingEnabled = it }
                )
            }

            // Glossy Specular Highlights Switch
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Glossy Specular Highlights",
                    style = NeoTheme.typography.body,
                    color = NeoTheme.colors.textPrimary
                )
                NeoSwitch(
                    checked = state.isGlossyShineEnabled,
                    onCheckedChange = { state.isGlossyShineEnabled = it }
                )
            }

            // Global Surface Style Dropdown
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                Text(
                    text = "Global Surface Style",
                    style = NeoTheme.typography.label,
                    color = NeoTheme.colors.textSecondary
                )
                NeoDropdownMenu(
                    selectedOption = state.styleOptions[state.selectedStyleIndex],
                    options = state.styleOptions,
                    onOptionSelected = { selectedName ->
                        val index = state.styleOptions.indexOf(selectedName)
                        if (index >= 0) {
                            state.selectedStyleIndex = index
                        }
                    }
                )
            }

            // Light Angle Slider
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                Text(
                    text = if (state.isSensorLightingEnabled) "Light Vector Angle: Tilt Device" else "Light Vector Angle: ${state.lightAngle.roundToInt()}°",
                    style = NeoTheme.typography.label,
                    color = if (state.isSensorLightingEnabled) NeoTheme.colors.textSecondary.copy(alpha = 0.5f) else NeoTheme.colors.textSecondary
                )
                NeoSlider(
                    value = state.lightAngle,
                    onValueChange = { state.lightAngle = it },
                    valueRange = 0f..360f,
                    enabled = !state.isSensorLightingEnabled
                )
            }

            // Elevation Depth Slider
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                Text(
                    text = "Elevation Depth: ${state.elevationDp.roundToInt()} dp",
                    style = NeoTheme.typography.label,
                    color = NeoTheme.colors.textSecondary
                )
                NeoSlider(
                    value = state.elevationDp,
                    onValueChange = { state.elevationDp = it },
                    valueRange = 1f..16f
                )
            }
        }
    }
}
