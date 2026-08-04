package prasad.vennam.neo.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import prasad.vennam.neo.components.NeoAudioPlayerBar
import prasad.vennam.neo.components.NeoAvatar
import prasad.vennam.neo.components.NeoBadge
import prasad.vennam.neo.components.NeoButton
import prasad.vennam.neo.components.NeoCard
import prasad.vennam.neo.components.NeoChip
import prasad.vennam.neo.components.NeoCircularSlider
import prasad.vennam.neo.components.NeoIconButton
import prasad.vennam.neo.components.NeoLinearProgressIndicator
import prasad.vennam.neo.components.NeoSegmentedControl
import prasad.vennam.neo.components.NeoSlider
import prasad.vennam.neo.components.NeoSurface
import prasad.vennam.neo.components.NeoSwitch
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoTheme
import kotlin.math.roundToInt

@Composable
public fun SmartHomeDashboard(
    state: StudioState,
    modifier: Modifier = Modifier,
) {
    var selectedRoomIndex by remember { mutableIntStateOf(0) }
    val rooms = listOf("Living Room", "Bedroom", "Kitchen", "Office")

    var selectedSceneIndex by remember { mutableIntStateOf(0) }
    val scenes = listOf("Relax", "Movie", "Focus", "Sleep")

    var isPurifierOn by remember { mutableStateOf(true) }
    var isWifiOnline by remember { mutableStateOf(true) }
    var lightBrightness by remember { mutableStateOf(0.75f) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium),
    ) {
        // 1. User Header & System Status
        NeoCard(style = state.selectedStyle) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    NeoAvatar(
                        isOnline = true,
                    )
                    Spacer(Modifier.width(NeoTheme.spacing.medium))
                    Column {
                        Text(
                            text = "Welcome Home, Alex",
                            style = NeoTheme.typography.title,
                            color = NeoTheme.colors.textPrimary,
                        )
                        Text(
                            text = "Smart Residence • 4 Active Zones",
                            style = NeoTheme.typography.caption,
                            color = NeoTheme.colors.textSecondary,
                        )
                    }
                }

                NeoBadge(count = "ONLINE")
            }
        }

        // 2. Room Selector Tabs
        NeoSegmentedControl(
            items = rooms,
            selectedIndex = selectedRoomIndex,
            onOptionSelected = { selectedRoomIndex = it },
        )

        // 3. Quick Scene Preset Chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Scenes:",
                style = NeoTheme.typography.caption,
                color = NeoTheme.colors.textSecondary,
                modifier = Modifier.padding(end = NeoTheme.spacing.extraSmall),
            )
            scenes.forEachIndexed { index, sceneName ->
                NeoChip(
                    label = sceneName,
                    selected = index == selectedSceneIndex,
                    onClick = { selectedSceneIndex = index },
                )
            }
        }

        // 4. Interactive Circular Climate Control Dial
        NeoCard(style = state.selectedStyle) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NeoIconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Thermostat,
                                contentDescription = null,
                                tint = NeoTheme.colors.primary,
                            )
                        }
                        Spacer(Modifier.width(NeoTheme.spacing.small))
                        Column {
                            Text(
                                text = "${rooms[selectedRoomIndex]} Climate",
                                style = NeoTheme.typography.title,
                                color = NeoTheme.colors.textPrimary,
                            )
                            Text(
                                text = if (state.isAcOn) "HVAC Cooling Active" else "System Idle",
                                style = NeoTheme.typography.caption,
                                color = NeoTheme.colors.textSecondary,
                            )
                        }
                    }

                    NeoSwitch(
                        checked = state.isAcOn,
                        onCheckedChange = { state.isAcOn = it },
                    )
                }

                // Interactive Circular Slider Gauge
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(vertical = NeoTheme.spacing.small),
                ) {
                    NeoCircularSlider(
                        value = (state.roomTemperature - 16f) / 14f,
                        onValueChange = { state.roomTemperature = 16f + (it * 14f) },
                        modifier = Modifier.height(200.dp),
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${state.roomTemperature.roundToInt()}°C",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = NeoTheme.colors.textPrimary,
                        )
                        Text(
                            text = "Target Temp",
                            style = NeoTheme.typography.caption,
                            color = NeoTheme.colors.textSecondary,
                        )
                    }
                }
            }
        }

        // 5. Grid Device Tiles (Lighting, Security, Air, Wi-Fi)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium),
        ) {
            // Lighting Control Tile
            NeoSurface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { state.isLightsOn = !state.isLightsOn },
                style = if (state.isLightsOn) NeoStyle.Inset else NeoStyle.Raised,
                shape = NeoTheme.shapes.large,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(NeoTheme.spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = if (state.isLightsOn) NeoTheme.colors.primary else NeoTheme.colors.textSecondary,
                        )
                        NeoBadge(count = if (state.isLightsOn) "75%" else "OFF")
                    }
                    Text(
                        text = "Main Lighting",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.textPrimary,
                    )
                    if (state.isLightsOn) {
                        NeoSlider(
                            value = lightBrightness,
                            onValueChange = { lightBrightness = it },
                        )
                    }
                }
            }

            // Security Lock Tile
            NeoSurface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { state.isSecurityOn = !state.isSecurityOn },
                style = if (state.isSecurityOn) NeoStyle.Inset else NeoStyle.Raised,
                shape = NeoTheme.shapes.large,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(NeoTheme.spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = if (state.isSecurityOn) NeoTheme.colors.primary else NeoTheme.colors.textSecondary,
                        )
                        NeoBadge(count = if (state.isSecurityOn) "ARMED" else "OFF")
                    }
                    Text(
                        text = "Security Gate",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.textPrimary,
                    )
                    Text(
                        text = if (state.isSecurityOn) "All Doors Locked" else "Disarmed",
                        style = NeoTheme.typography.caption,
                        color = NeoTheme.colors.textSecondary,
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium),
        ) {
            // Air Purifier Tile
            NeoSurface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { isPurifierOn = !isPurifierOn },
                style = if (isPurifierOn) NeoStyle.Inset else NeoStyle.Raised,
                shape = NeoTheme.shapes.large,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(NeoTheme.spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Air,
                            contentDescription = null,
                            tint = if (isPurifierOn) NeoTheme.colors.primary else NeoTheme.colors.textSecondary,
                        )
                        NeoBadge(count = if (isPurifierOn) "AQI 12" else "OFF")
                    }
                    Text(
                        text = "Air Purifier",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.textPrimary,
                    )
                    Text(
                        text = if (isPurifierOn) "HEPA Filter Active" else "Standby Mode",
                        style = NeoTheme.typography.caption,
                        color = NeoTheme.colors.textSecondary,
                    )
                }
            }

            // Wi-Fi Mesh Tile
            NeoSurface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { isWifiOnline = !isWifiOnline },
                style = if (isWifiOnline) NeoStyle.Inset else NeoStyle.Raised,
                shape = NeoTheme.shapes.large,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(NeoTheme.spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Wifi,
                            contentDescription = null,
                            tint = if (isWifiOnline) NeoTheme.colors.primary else NeoTheme.colors.textSecondary,
                        )
                        NeoBadge(count = if (isWifiOnline) "1.2 Gbps" else "OFF")
                    }
                    Text(
                        text = "Wi-Fi 7 Mesh",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.textPrimary,
                    )
                    Text(
                        text = if (isWifiOnline) "18 Devices Linked" else "Disconnected",
                        style = NeoTheme.typography.caption,
                        color = NeoTheme.colors.textSecondary,
                    )
                }
            }
        }

        // 6. Whole-Home Ambient Audio Player
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small)) {
                Text(
                    text = "Whole-Home Ambient Audio",
                    style = NeoTheme.typography.label,
                    color = NeoTheme.colors.textSecondary,
                )
                NeoAudioPlayerBar(
                    trackTitle = "Midnight Resonance",
                    artistName = "Lo-Fi Acoustic Ambient",
                    isPlaying = state.isAudioPlaying,
                    progress = state.audioProgress,
                    onPlayPauseToggle = { state.isAudioPlaying = !state.isAudioPlaying },
                    onProgressChange = { state.audioProgress = it },
                )
            }
        }

        // 7. Energy Consumption & Peak Stats
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Daily Power Usage (3.8 kWh)",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.textSecondary,
                    )
                    Text(
                        text = "Peak: 850W",
                        style = NeoTheme.typography.caption,
                        color = NeoTheme.colors.primary,
                    )
                }
                NeoLinearProgressIndicator(progress = 0.58f)
            }
        }
    }
}
