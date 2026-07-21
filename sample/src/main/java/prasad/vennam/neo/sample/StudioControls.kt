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
        style = NeoStyle.Raised
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
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

            // Light Angle Slider
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Light Vector Angle: ${state.lightAngle.roundToInt()}°",
                    style = NeoTheme.typography.label,
                    color = NeoTheme.colors.textSecondary
                )
                NeoSlider(
                    value = state.lightAngle,
                    onValueChange = { state.lightAngle = it },
                    valueRange = 0f..360f
                )
            }

            // Elevation Depth Slider
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
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
