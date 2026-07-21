package prasad.vennam.neo.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.components.NeoAudioPlayerBar
import prasad.vennam.neo.components.NeoAvatar
import prasad.vennam.neo.components.NeoBadge
import prasad.vennam.neo.components.NeoBanner
import prasad.vennam.neo.components.NeoBottomSheet
import prasad.vennam.neo.components.NeoButton
import prasad.vennam.neo.components.NeoCard
import prasad.vennam.neo.components.NeoCheckbox
import prasad.vennam.neo.components.NeoChip
import prasad.vennam.neo.components.NeoCircularProgressIndicator
import prasad.vennam.neo.components.NeoDatePicker
import prasad.vennam.neo.components.NeoDialog
import prasad.vennam.neo.components.NeoDivider
import prasad.vennam.neo.components.NeoDropdownMenu
import prasad.vennam.neo.components.NeoFAB
import prasad.vennam.neo.components.NeoIconButton
import prasad.vennam.neo.components.NeoLinearProgressIndicator
import prasad.vennam.neo.components.NeoNumberStepper
import prasad.vennam.neo.components.NeoRadioButton
import prasad.vennam.neo.components.NeoRangeSlider
import prasad.vennam.neo.components.NeoSearchField
import prasad.vennam.neo.components.NeoSegmentedControl
import prasad.vennam.neo.components.NeoSlider
import prasad.vennam.neo.components.NeoSpeedDial
import prasad.vennam.neo.components.NeoSpeedDialItem
import prasad.vennam.neo.components.NeoSurface
import prasad.vennam.neo.components.NeoSwitch
import prasad.vennam.neo.components.NeoTabBar
import prasad.vennam.neo.components.NeoTextField
import prasad.vennam.neo.components.NeoTimePicker
import prasad.vennam.neo.components.NeoTooltip
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoTheme

@OptIn(ExperimentalMaterial3Api::class)
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
                Text("1. NeoSurface (Concave, Convex & Basin)", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
                    NeoSurface(
                        modifier = Modifier.weight(1f).height(60.dp),
                        style = NeoStyle.Basin
                    ) {
                        Text("Basin", style = NeoTheme.typography.label, color = NeoTheme.colors.textSecondary)
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

        // 3. Form Inputs & Selection Controls
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("3. Form Inputs & Select Controls", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

                NeoSearchField(
                    query = state.searchQuery,
                    onQueryChange = { state.searchQuery = it }
                )

                NeoTextField(
                    value = state.textInputValue,
                    onValueChange = { state.textInputValue = it },
                    placeholder = "Type email..."
                )

                NeoDropdownMenu(
                    selectedOption = state.selectedCountry,
                    options = listOf("Argentina", "Armenia", "Australia"),
                    onOptionSelected = { state.selectedCountry = it }
                )

                NeoDivider(thickness = 2.dp)

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

        // 4. Date & Time Pickers
        Text("4. Date & Time Pickers", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)
        NeoDatePicker(
            selectedDay = state.selectedDay,
            onDaySelected = { state.selectedDay = it }
        )

        NeoTimePicker(
            hour = state.pickerHour,
            minute = state.pickerMinute,
            isAm = state.isPickerAm,
            onHourChange = { state.pickerHour = it },
            onMinuteChange = { state.pickerMinute = it },
            onAmPmToggle = { state.isPickerAm = it }
        )

        // 5. Segmented Control & Navigation Tabs
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("5. Segmented Control & Navigation Bar", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

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

        // 6. Dual-Thumb Range Slider & Stepper
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("6. Range Slider & Number Stepper", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

                Text("Dual-Thumb Range Slider", style = NeoTheme.typography.label, color = NeoTheme.colors.textSecondary)
                NeoRangeSlider(
                    values = state.rangeSliderValues,
                    onValuesChange = { state.rangeSliderValues = it }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Counter Stepper", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)
                    NeoNumberStepper(
                        value = state.stepperValue,
                        onValueChange = { state.stepperValue = it }
                    )
                }
            }
        }

        // 7. Media & Audio Player
        Text("7. Media & Audio Player Controller", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)
        NeoAudioPlayerBar(
            trackTitle = "Midnight Resonance",
            artistName = "Synthesizer Wave",
            isPlaying = state.isAudioPlaying,
            onPlayPauseToggle = { state.isAudioPlaying = !state.isAudioPlaying },
            progress = state.audioProgress,
            onProgressChange = { state.audioProgress = it },
            onSkipPrevious = { },
            onSkipNext = { }
        )

        // 8. Tooltips, Banners, Sheets & Dialogs
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("8. Tooltips, Banners & Bottom Sheets", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

                if (state.showTooltip) {
                    NeoTooltip(
                        text = "Design tokens update ready to sync",
                        onDismiss = { state.showTooltip = false }
                    )
                }

                if (state.showBanner) {
                    NeoBanner(
                        title = "System Update Ready",
                        message = "Neumorphic design system v1.2 tokens compiled",
                        icon = Icons.Default.Notifications,
                        actionLabel = "Dismiss",
                        onActionClick = { state.showBanner = false }
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    NeoButton(
                        onClick = { state.showBottomSheet = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Bottom Sheet", style = NeoTheme.typography.label, color = NeoTheme.colors.primary)
                    }

                    NeoButton(
                        onClick = { state.showDialog = true },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Modal Dialog", style = NeoTheme.typography.label, color = NeoTheme.colors.primary)
                    }
                }
            }
        }

        if (state.showBottomSheet) {
            NeoBottomSheet(onDismissRequest = { state.showBottomSheet = false }) {
                Text("Neumorphic Bottom Sheet Drawer", style = NeoTheme.typography.display, color = NeoTheme.colors.textPrimary)
                Spacer(Modifier.height(8.dp))
                Text("Contains fully styled Neumorphic options and actions.", style = NeoTheme.typography.body, color = NeoTheme.colors.textSecondary)
                Spacer(Modifier.height(16.dp))
                NeoButton(
                    onClick = { state.showBottomSheet = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Close Sheet", style = NeoTheme.typography.label, color = NeoTheme.colors.primary)
                }
            }
        }

        if (state.showDialog) {
            NeoDialog(
                onDismissRequest = { state.showDialog = false },
                title = "Confirm Deployment",
                message = "Are you ready to publish NeoCompose v1.2 components to JitPack & Maven Central?",
                confirmButtonLabel = "Confirm",
                onConfirmClick = { state.showDialog = false },
                dismissButtonLabel = "Cancel",
                onDismissClick = { state.showDialog = false }
            )
        }

        // 9. Chips, Badges & Avatars
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("9. Chips, Badges & User Avatars", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

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

                    NeoAvatar(isOnline = true)
                }
            }
        }

        // 10. Slider & Progress Indicators
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("10. Slider & Progress Indicators", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

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

        // 11. Floating Action Speed Dial
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            NeoSpeedDial(
                items = listOf(
                    NeoSpeedDialItem("1", "New Message", Icons.Default.Notifications) {},
                    NeoSpeedDialItem("2", "Share Design", Icons.Default.Share) {}
                )
            )
        }
    }
}
