package prasad.vennam.neo.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.components.NeoBadge
import prasad.vennam.neo.components.NeoButton
import prasad.vennam.neo.components.NeoCard
import prasad.vennam.neo.components.NeoCheckbox
import prasad.vennam.neo.components.NeoChip
import prasad.vennam.neo.components.NeoCircularProgressIndicator
import prasad.vennam.neo.components.NeoFAB
import prasad.vennam.neo.components.NeoIconButton
import prasad.vennam.neo.components.NeoLinearProgressIndicator
import prasad.vennam.neo.components.NeoRadioButton
import prasad.vennam.neo.components.NeoSegmentedControl
import prasad.vennam.neo.components.NeoSlider
import prasad.vennam.neo.components.NeoSurface
import prasad.vennam.neo.components.NeoSwitch
import prasad.vennam.neo.components.NeoTabBar
import prasad.vennam.neo.components.NeoTextField
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoTheme

@Composable
public fun ComponentCatalog(
    state: StudioState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Component Suite Catalog",
            style = NeoTheme.typography.display,
            color = NeoTheme.colors.textPrimary
        )

        // 1. NeoSurface Showcase
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("1. NeoSurface (Concave & Convex)", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    NeoSurface(
                        modifier = Modifier.weight(1f).height(60.dp),
                        style = NeoStyle.Concave
                    ) {
                        Text("Concave", style = NeoTheme.typography.label, color = NeoTheme.colors.textSecondary)
                    }
                    NeoSurface(
                        modifier = Modifier.weight(1f).height(60.dp),
                        style = NeoStyle.Convex
                    ) {
                        Text("Convex", style = NeoTheme.typography.label, color = NeoTheme.colors.textSecondary)
                    }
                }
            }
        }

        // 2. Buttons & Icon Buttons
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("2. Buttons & Icon Buttons", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NeoButton(onClick = { }) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, tint = NeoTheme.colors.primary)
                        Spacer(Modifier.width(8.dp))
                        Text("Primary Action", style = NeoTheme.typography.label, color = NeoTheme.colors.textPrimary)
                    }
                    NeoIconButton(onClick = { }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Heart", tint = NeoTheme.colors.primary)
                    }
                }
            }
        }

        // 3. Inputs, Checkbox & Radio Buttons
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("3. Inputs & Selection Controls", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

                NeoTextField(
                    value = state.textInputValue,
                    onValueChange = { state.textInputValue = it },
                    placeholder = "Type something..."
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NeoCheckbox(
                            checked = state.isCheckboxChecked,
                            onCheckedChange = { state.isCheckboxChecked = it }
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Checkbox", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NeoRadioButton(
                            selected = state.isRadioSelected,
                            onClick = { state.isRadioSelected = !state.isRadioSelected }
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Radio", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)
                    }

                    NeoSwitch(
                        checked = state.isSwitchChecked,
                        onCheckedChange = { state.isSwitchChecked = it }
                    )
                }
            }
        }

        // 4. Segmented Control & Navigation Tabs
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("4. Segmented Control & Navigation Bar", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

                NeoSegmentedControl(
                    items = listOf("Day", "Week", "Month"),
                    selectedIndex = state.segmentedIndex,
                    onOptionSelected = { state.segmentedIndex = it }
                )

                NeoTabBar(
                    tabs = listOf("Home", "Explore", "Profile"),
                    selectedIndex = state.tabBarIndex,
                    onTabSelected = { state.tabBarIndex = it }
                )
            }
        }

        // 5. Chips & Badges
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("5. Chips & Counter Badges", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NeoChip(
                        label = "Design Token",
                        selected = state.chipSelected,
                        onClick = { state.chipSelected = !state.chipSelected }
                    )

                    NeoBadge(count = "+99")
                }
            }
        }

        // 6. Slider & Progress Indicators
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("6. Slider & Progress Indicators", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

                NeoSlider(
                    value = state.progressValue,
                    onValueChange = { state.progressValue = it }
                )

                NeoLinearProgressIndicator(progress = state.progressValue)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Circular Progress", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)
                    NeoCircularProgressIndicator(progress = state.progressValue)
                }
            }
        }

        // 7. Floating Action Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            NeoFAB(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = NeoTheme.colors.primary)
                Spacer(Modifier.width(8.dp))
                Text("Create New", style = NeoTheme.typography.label, color = NeoTheme.colors.textPrimary)
            }
        }
    }
}
