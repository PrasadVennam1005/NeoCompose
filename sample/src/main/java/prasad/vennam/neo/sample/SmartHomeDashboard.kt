package prasad.vennam.neo.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.components.NeoBadge
import prasad.vennam.neo.components.NeoCard
import prasad.vennam.neo.components.NeoChip
import prasad.vennam.neo.components.NeoIconButton
import prasad.vennam.neo.components.NeoLinearProgressIndicator
import prasad.vennam.neo.components.NeoSlider
import prasad.vennam.neo.components.NeoSurface
import prasad.vennam.neo.components.NeoSwitch
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoTheme
import kotlin.math.roundToInt

@Composable
public fun SmartHomeDashboard(
    state: StudioState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Welcome Card
        NeoCard(style = NeoStyle.Raised) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Smart Home Hub",
                        style = NeoTheme.typography.title,
                        color = NeoTheme.colors.textPrimary
                    )
                    Text(
                        text = "3 devices connected",
                        style = NeoTheme.typography.caption,
                        color = NeoTheme.colors.textSecondary
                    )
                }

                NeoBadge(count = "ONLINE")
            }
        }

        // Climate Thermostat Control
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NeoIconButton(onClick = { }) {
                            Icon(Icons.Default.Thermostat, contentDescription = null, tint = NeoTheme.colors.primary)
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Climate Control",
                                style = NeoTheme.typography.title,
                                color = NeoTheme.colors.textPrimary
                            )
                            Text(
                                text = "${state.roomTemperature.roundToInt()}°C Target Temp",
                                style = NeoTheme.typography.caption,
                                color = NeoTheme.colors.textSecondary
                            )
                        }
                    }

                    NeoSwitch(
                        checked = state.isAcOn,
                        onCheckedChange = { state.isAcOn = it }
                    )
                }

                NeoSlider(
                    value = state.roomTemperature,
                    onValueChange = { state.roomTemperature = it },
                    valueRange = 16f..30f
                )
            }
        }

        // Quick Controls Grid
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Lighting Control
            NeoSurface(
                modifier = Modifier.weight(1f).height(120.dp),
                style = if (state.isLightsOn) NeoStyle.Inset else NeoStyle.Raised
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = if (state.isLightsOn) NeoTheme.colors.primary else NeoTheme.colors.textSecondary
                    )
                    Text(
                        text = if (state.isLightsOn) "Lights ON" else "Lights OFF",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.textPrimary
                    )
                }
            }

            // Security Control
            NeoSurface(
                modifier = Modifier.weight(1f).height(120.dp),
                style = if (state.isSecurityOn) NeoStyle.Inset else NeoStyle.Raised
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = if (state.isSecurityOn) NeoTheme.colors.primary else NeoTheme.colors.textSecondary
                    )
                    Text(
                        text = if (state.isSecurityOn) "Security ARMED" else "Security OFF",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.textPrimary
                    )
                }
            }
        }

        // Power Consumption Indicator
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Daily Power Usage (2.4 kWh)",
                    style = NeoTheme.typography.label,
                    color = NeoTheme.colors.textSecondary
                )
                NeoLinearProgressIndicator(progress = 0.65f)
            }
        }
    }
}
