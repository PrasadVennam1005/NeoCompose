package prasad.vennam.neo.sample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.components.NeoBadge
import prasad.vennam.neo.components.NeoCard
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
        verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium + NeoTheme.spacing.extraSmall)
    ) {
        // Welcome Status Card
        NeoCard(style = state.selectedStyle) {
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

        // Climate Thermostat Control Card
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium + NeoTheme.spacing.extraSmall)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NeoIconButton(onClick = { }) {
                            Icon(Icons.Default.Thermostat, contentDescription = null, tint = NeoTheme.colors.primary)
                        }
                        Spacer(Modifier.width(NeoTheme.spacing.small + NeoTheme.spacing.extraSmall))
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

        // Quick Controls Grid (Lighting & Security)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)
        ) {
            // Lighting Interactive Control Surface
            NeoSurface(
                modifier = Modifier
                    .weight(1f)
                    .height(NeoTheme.size.controlLarge * 2)
                    .clickable { state.isLightsOn = !state.isLightsOn },
                style = if (state.isLightsOn) NeoStyle.Inset else NeoStyle.Raised,
                shape = NeoTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(NeoTheme.spacing.medium),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
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

            // Security Interactive Control Surface
            NeoSurface(
                modifier = Modifier
                    .weight(1f)
                    .height(NeoTheme.size.controlLarge * 2)
                    .clickable { state.isSecurityOn = !state.isSecurityOn },
                style = if (state.isSecurityOn) NeoStyle.Inset else NeoStyle.Raised,
                shape = NeoTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(NeoTheme.spacing.medium),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
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

        // Power Usage Card
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small + NeoTheme.spacing.extraSmall)) {
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
