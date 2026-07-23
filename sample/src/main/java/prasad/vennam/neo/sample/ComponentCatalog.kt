package prasad.vennam.neo.sample

import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
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
import prasad.vennam.neo.foundation.neoInteraction
import prasad.vennam.neo.theme.NeoTheme
import kotlin.math.roundToInt
import kotlinx.coroutines.delay

@Composable
fun ComponentSandbox(state: StudioState) {
    val clipboardManager = LocalClipboardManager.current
    val componentOptions = listOf("NeoButton", "NeoCard", "NeoSwitch", "NeoSlider")

    val sandboxStyle =
        when (state.sandboxStyleIndex) {
            0 -> NeoStyle.Raised
            1 -> NeoStyle.Pressed
            2 -> NeoStyle.Inset
            3 -> NeoStyle.Flat
            4 -> NeoStyle.Concave
            5 -> NeoStyle.Convex
            else -> NeoStyle.Basin
        }

    val sandboxStyleName = state.styleOptions[state.sandboxStyleIndex]

    // Generate code snippet dynamically
    val codeSnippet =
        when (state.sandboxSelectedComponent) {
            "NeoButton" ->
                """
                NeoButton(
                    onClick = { /* action */ },
                    style = NeoStyle.$sandboxStyleName,
                    enabled = ${state.sandboxButtonEnabled}
                ) {
                    Text(
                        text = "${state.sandboxButtonLabel}",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.textPrimary
                    )
                }
                """.trimIndent()
            "NeoCard" ->
                """
                NeoCard(
                    style = NeoStyle.$sandboxStyleName,
                    elevation = ${state.sandboxCardElevation.roundToInt()}.dp,
                    specularHighlight = ${state.sandboxCardSpecular}
                ) {
                    Text(
                        text = "Card Content",
                        style = NeoTheme.typography.body,
                        color = NeoTheme.colors.textPrimary
                    )
                }
                """.trimIndent()
            "NeoSwitch" ->
                """
                NeoSwitch(
                    checked = ${state.sandboxSwitchChecked},
                    onCheckedChange = { checked -> /* handle */ },
                    style = NeoStyle.$sandboxStyleName
                )
                """.trimIndent()
            else ->
                """
                NeoSlider(
                    value = ${state.sandboxSliderValue}f,
                    onValueChange = { value -> /* handle */ },
                    enabled = true
                )
                """.trimIndent()
        }

    if (state.sandboxCodeCopied) {
        LaunchedEffect(Unit) {
            delay(1500)
            state.sandboxCodeCopied = false
        }
    }

    NeoCard(
        modifier = Modifier.fillMaxWidth(),
        style = NeoStyle.Raised,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
            Text(
                text = "Interactive Sandbox",
                style = NeoTheme.typography.title,
                color = NeoTheme.colors.primary,
            )

            Text(
                text = "Tweak neumorphic style attributes in real-time and copy the exact Jetpack Compose code directly.",
                style = NeoTheme.typography.body,
                color = NeoTheme.colors.textSecondary,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium),
            ) {
                // Component selector
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall),
                ) {
                    Text(
                        text = "Select Component",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.textSecondary,
                    )
                    NeoDropdownMenu(
                        selectedOption = state.sandboxSelectedComponent,
                        options = componentOptions,
                        onOptionSelected = { state.sandboxSelectedComponent = it },
                    )
                }

                // Style selector
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall),
                ) {
                    Text(
                        text = "Style Variant",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.textSecondary,
                    )
                    NeoDropdownMenu(
                        selectedOption = state.styleOptions[state.sandboxStyleIndex],
                        options = state.styleOptions,
                        onOptionSelected = { selectedName ->
                            val index = state.styleOptions.indexOf(selectedName)
                            if (index >= 0) {
                                state.sandboxStyleIndex = index
                            }
                        },
                    )
                }
            }

            // Component Specific Controls
            when (state.sandboxSelectedComponent) {
                "NeoButton" -> {
                    Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small)) {
                        NeoTextField(
                            value = state.sandboxButtonLabel,
                            onValueChange = { state.sandboxButtonLabel = it },
                            placeholder = "Button Label",
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Enabled",
                                style = NeoTheme.typography.body,
                                color = NeoTheme.colors.textPrimary,
                            )
                            NeoSwitch(
                                checked = state.sandboxButtonEnabled,
                                onCheckedChange = { state.sandboxButtonEnabled = it },
                            )
                        }
                    }
                }
                "NeoCard" -> {
                    Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Specular Shine",
                                style = NeoTheme.typography.body,
                                color = NeoTheme.colors.textPrimary,
                            )
                            NeoSwitch(
                                checked = state.sandboxCardSpecular,
                                onCheckedChange = { state.sandboxCardSpecular = it },
                            )
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                            Text(
                                text = "Card Elevation: ${state.sandboxCardElevation.roundToInt()} dp",
                                style = NeoTheme.typography.label,
                                color = NeoTheme.colors.textSecondary,
                            )
                            NeoSlider(
                                value = state.sandboxCardElevation,
                                onValueChange = { state.sandboxCardElevation = it },
                                valueRange = 1f..16f,
                            )
                        }
                    }
                }
                "NeoSwitch" -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Checked State",
                            style = NeoTheme.typography.body,
                            color = NeoTheme.colors.textPrimary,
                        )
                        NeoSwitch(
                            checked = state.sandboxSwitchChecked,
                            onCheckedChange = { state.sandboxSwitchChecked = it },
                        )
                    }
                }
                "NeoSlider" -> {
                    Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                        Text(
                            text = "Slider Value: ${(state.sandboxSliderValue * 100).roundToInt()}%",
                            style = NeoTheme.typography.label,
                            color = NeoTheme.colors.textSecondary,
                        )
                        NeoSlider(
                            value = state.sandboxSliderValue,
                            onValueChange = { state.sandboxSliderValue = it },
                            valueRange = 0f..1f,
                        )
                    }
                }
            }

            // Preview Box
            Text(
                text = "Live Preview",
                style = NeoTheme.typography.label,
                color = NeoTheme.colors.textSecondary,
            )
            NeoSurface(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                style = NeoStyle.Inset,
                shape = NeoTheme.shapes.medium,
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    when (state.sandboxSelectedComponent) {
                        "NeoButton" -> {
                            NeoButton(
                                onClick = {},
                                style = sandboxStyle,
                                enabled = state.sandboxButtonEnabled,
                            ) {
                                Text(
                                    text = state.sandboxButtonLabel,
                                    style = NeoTheme.typography.label,
                                    color =
                                        if (state.sandboxButtonEnabled) {
                                            NeoTheme.colors.textPrimary
                                        } else {
                                            NeoTheme.colors.textSecondary.copy(
                                                alpha = 0.5f,
                                            )
                                        },
                                )
                            }
                        }
                        "NeoCard" -> {
                            NeoCard(
                                style = sandboxStyle,
                                elevation = state.sandboxCardElevation.dp,
                                specularHighlight = state.sandboxCardSpecular,
                                modifier = Modifier.width(200.dp),
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxWidth().padding(NeoTheme.spacing.medium),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = "Preview Card",
                                        style = NeoTheme.typography.body,
                                        color = NeoTheme.colors.textPrimary,
                                    )
                                }
                            }
                        }
                        "NeoSwitch" -> {
                            NeoSwitch(
                                checked = state.sandboxSwitchChecked,
                                onCheckedChange = { state.sandboxSwitchChecked = it },
                                style = sandboxStyle,
                            )
                        }
                        "NeoSlider" -> {
                            NeoSlider(
                                value = state.sandboxSliderValue,
                                onValueChange = { state.sandboxSliderValue = it },
                                modifier = Modifier.fillMaxWidth(0.8f),
                            )
                        }
                    }
                }
            }

            // Code Output
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Source Code",
                    style = NeoTheme.typography.label,
                    color = NeoTheme.colors.textSecondary,
                )
                NeoButton(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(codeSnippet))
                        state.sandboxCodeCopied = true
                    },
                    style = if (state.sandboxCodeCopied) NeoStyle.Pressed else NeoStyle.Raised,
                ) {
                    Text(
                        text = if (state.sandboxCodeCopied) "Copied!" else "Copy Code",
                        style = NeoTheme.typography.label,
                        color = if (state.sandboxCodeCopied) NeoTheme.colors.primary else NeoTheme.colors.textPrimary,
                    )
                }
            }

            NeoSurface(
                modifier = Modifier.fillMaxWidth(),
                style = NeoStyle.Inset,
                shape = NeoTheme.shapes.medium,
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(NeoTheme.spacing.medium),
                ) {
                    SelectionContainer {
                        Text(
                            text = codeSnippet,
                            style =
                                NeoTheme.typography.body.copy(
                                    fontFamily = FontFamily.Monospace,
                                ),
                            color = NeoTheme.colors.textPrimary,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun ComponentCatalog(
    state: StudioState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.large),
    ) {
        Text(
            text = "Component Suite Catalog",
            style = NeoTheme.typography.display,
            color = NeoTheme.colors.textPrimary,
        )

        ComponentSandbox(state = state)

        // 1. NeoSurface Showcase
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small + NeoTheme.spacing.extraSmall)) {
                Text(
                    "1. NeoSurface (All 7 Variants Showcase)",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
                ) {
                    listOf(
                        NeoStyle.Raised to "Raised",
                        NeoStyle.Pressed to "Pressed",
                        NeoStyle.Inset to "Inset",
                        NeoStyle.Flat to "Flat",
                    ).forEach { (style, label) ->
                        NeoSurface(
                            modifier =
                                Modifier
                                    .weight(1f)
                                    .height(NeoTheme.size.controlMedium),
                            style = style,
                        ) {
                            Text(
                                label,
                                style = NeoTheme.typography.label,
                                color = NeoTheme.colors.textSecondary,
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
                ) {
                    listOf(
                        NeoStyle.Concave to "Concave",
                        NeoStyle.Convex to "Convex",
                        NeoStyle.Basin to "Basin",
                    ).forEach { (style, label) ->
                        NeoSurface(
                            modifier =
                                Modifier
                                    .weight(1f)
                                    .height(NeoTheme.size.controlMedium),
                            style = style,
                        ) {
                            Text(
                                label,
                                style = NeoTheme.typography.label,
                                color = NeoTheme.colors.textSecondary,
                            )
                        }
                    }
                }
            }
        }

        // 2. Buttons & Icon Buttons
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small + NeoTheme.spacing.extraSmall)) {
                Text(
                    "2. Buttons & Icon Buttons",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    NeoButton(onClick = { }) {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = NeoTheme.colors.primary,
                        )
                        Spacer(Modifier.width(NeoTheme.spacing.small))
                        Text(
                            "Primary Action",
                            style = NeoTheme.typography.label,
                            color = NeoTheme.colors.textPrimary,
                        )
                    }
                    NeoIconButton(onClick = { }) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Heart",
                            tint = NeoTheme.colors.primary,
                        )
                    }
                }
            }
        }

        // 3. Form Inputs & Selection Controls
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
                Text(
                    "3. Form Inputs & Select Controls",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )

                NeoSearchField(
                    query = state.searchQuery,
                    onQueryChange = { state.searchQuery = it },
                )

                NeoTextField(
                    value = state.textInputValue,
                    onValueChange = { state.textInputValue = it },
                    placeholder = "Type email...",
                )

                NeoDropdownMenu(
                    selectedOption = state.selectedCountry,
                    options = listOf("Argentina", "Armenia", "Australia"),
                    onOptionSelected = { state.selectedCountry = it },
                )

                NeoDivider(thickness = NeoTheme.size.borderThick)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NeoCheckbox(
                            checked = state.isCheckboxChecked,
                            onCheckedChange = { state.isCheckboxChecked = it },
                        )
                        Spacer(Modifier.width(NeoTheme.spacing.small))
                        Text(
                            "Checkbox",
                            style = NeoTheme.typography.body,
                            color = NeoTheme.colors.textPrimary,
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NeoRadioButton(
                            selected = state.isRadioSelected,
                            onClick = { state.isRadioSelected = !state.isRadioSelected },
                        )
                        Spacer(Modifier.width(NeoTheme.spacing.small))
                        Text(
                            "Radio",
                            style = NeoTheme.typography.body,
                            color = NeoTheme.colors.textPrimary,
                        )
                    }

                    NeoSwitch(
                        checked = state.isSwitchChecked,
                        onCheckedChange = { state.isSwitchChecked = it },
                    )
                }
            }
        }

        // 4. Date & Time Pickers
        Text(
            "4. Date & Time Pickers",
            style = NeoTheme.typography.title,
            color = NeoTheme.colors.textPrimary,
        )
        NeoDatePicker(
            selectedDay = state.selectedDay,
            onDaySelected = { state.selectedDay = it },
        )

        NeoTimePicker(
            hour = state.pickerHour,
            minute = state.pickerMinute,
            isAm = state.isPickerAm,
            onHourChange = { state.pickerHour = it },
            onMinuteChange = { state.pickerMinute = it },
            onAmPmToggle = { state.isPickerAm = it },
        )

        // 5. Segmented Control & Navigation Tabs
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
                Text(
                    "5. Segmented Control & Navigation Bar",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )

                NeoSegmentedControl(
                    items = listOf("Day", "Week", "Month"),
                    selectedIndex = state.segmentedIndex,
                    onOptionSelected = { state.segmentedIndex = it },
                )

                NeoTabBar(
                    tabs = listOf("Home", "Explore", "Profile"),
                    selectedIndex = state.tabBarIndex,
                    onTabSelected = { state.tabBarIndex = it },
                )
            }
        }

        // 6. Dual-Thumb Range Slider & Stepper
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
                Text(
                    "6. Range Slider & Number Stepper",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )

                Text(
                    "Dual-Thumb Range Slider",
                    style = NeoTheme.typography.label,
                    color = NeoTheme.colors.textSecondary,
                )
                NeoRangeSlider(
                    values = state.rangeSliderValues,
                    onValuesChange = { state.rangeSliderValues = it },
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Counter Stepper",
                        style = NeoTheme.typography.body,
                        color = NeoTheme.colors.textPrimary,
                    )
                    NeoNumberStepper(
                        value = state.stepperValue,
                        onValueChange = { state.stepperValue = it },
                    )
                }
            }
        }

        // 7. Media & Audio Player
        Text(
            "7. Media & Audio Player Controller",
            style = NeoTheme.typography.title,
            color = NeoTheme.colors.textPrimary,
        )
        NeoAudioPlayerBar(
            trackTitle = "Midnight Resonance",
            artistName = "Synthesizer Wave",
            isPlaying = state.isAudioPlaying,
            onPlayPauseToggle = { state.isAudioPlaying = !state.isAudioPlaying },
            progress = state.audioProgress,
            onProgressChange = { state.audioProgress = it },
            onSkipPrevious = { },
            onSkipNext = { },
        )

        // 8. Tooltips, Banners, Sheets & Dialogs
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
                Text(
                    "8. Tooltips, Banners & Bottom Sheets",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )

                if (state.showTooltip) {
                    NeoTooltip(
                        text = "Design tokens update ready to sync",
                        onDismiss = { state.showTooltip = false },
                    )
                }

                if (state.showBanner) {
                    NeoBanner(
                        title = "System Update Ready",
                        message = "Neumorphic design system v1.2 tokens compiled",
                        icon = Icons.Default.Notifications,
                        actionLabel = "Dismiss",
                        onActionClick = { state.showBanner = false },
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small + NeoTheme.spacing.extraSmall),
                ) {
                    NeoButton(
                        onClick = { state.showBottomSheet = true },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            "Bottom Sheet",
                            style = NeoTheme.typography.label,
                            color = NeoTheme.colors.primary,
                        )
                    }

                    NeoButton(
                        onClick = { state.showDialog = true },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            "Modal Dialog",
                            style = NeoTheme.typography.label,
                            color = NeoTheme.colors.primary,
                        )
                    }
                }
            }
        }

        if (state.showBottomSheet) {
            NeoBottomSheet(onDismissRequest = { state.showBottomSheet = false }) {
                Text(
                    "Neumorphic Bottom Sheet Drawer",
                    style = NeoTheme.typography.display,
                    color = NeoTheme.colors.textPrimary,
                )
                Spacer(Modifier.height(NeoTheme.spacing.small))
                Text(
                    "Contains fully styled Neumorphic options and actions.",
                    style = NeoTheme.typography.body,
                    color = NeoTheme.colors.textSecondary,
                )
                Spacer(Modifier.height(NeoTheme.spacing.medium))
                NeoButton(
                    onClick = { state.showBottomSheet = false },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        "Close Sheet",
                        style = NeoTheme.typography.label,
                        color = NeoTheme.colors.primary,
                    )
                }
            }
        }

        if (state.showDialog) {
            NeoDialog(
                onDismissRequest = { state.showDialog = false },
                title = "Confirm Deployment",
                message = "Are you ready to publish NeoCompose v1.3.0 components to JitPack & Maven Central?",
                confirmButtonLabel = "Confirm",
                onConfirmClick = { state.showDialog = false },
                dismissButtonLabel = "Cancel",
                onDismissClick = { state.showDialog = false },
            )
        }

        // 9. Chips, Badges & Avatars
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
                Text(
                    "9. Chips, Badges & User Avatars",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    NeoChip(
                        label = "Design Token",
                        selected = state.chipSelected,
                        onClick = { state.chipSelected = !state.chipSelected },
                    )

                    NeoBadge(count = "+99")

                    NeoAvatar(isOnline = true)
                }
            }
        }

        // 10. Slider & Progress Indicators
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
                Text(
                    "10. Slider & Progress Indicators",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )

                NeoSlider(
                    value = state.progressValue,
                    onValueChange = { state.progressValue = it },
                )

                NeoLinearProgressIndicator(progress = state.progressValue)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Circular Progress",
                        style = NeoTheme.typography.body,
                        color = NeoTheme.colors.textPrimary,
                    )
                    NeoCircularProgressIndicator(progress = state.progressValue)
                }
            }
        }

        // 11. Material 3 Interoperability
        NeoCard(style = state.selectedStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
                Text(
                    text = "11. Material 3 Interoperability",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )

                Text(
                    text = "Observe how standard Material 3 components dynamically adapt their neumorphic shadow states (e.g., Raised to Pressed) via Modifier.neoInteraction.",
                    style = NeoTheme.typography.body,
                    color = NeoTheme.colors.textSecondary,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium),
                ) {
                    val m3ButtonInteractionSource = remember { MutableInteractionSource() }
                    Button(
                        onClick = {},
                        interactionSource = m3ButtonInteractionSource,
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = NeoTheme.colors.primary,
                            ),
                        elevation = null,
                        shape = NeoTheme.shapes.pill,
                        modifier =
                            Modifier
                                .weight(1f)
                                .neoInteraction(
                                    interactionSource = m3ButtonInteractionSource,
                                    shape = NeoTheme.shapes.pill,
                                ),
                    ) {
                        Text("M3 Button", style = NeoTheme.typography.label)
                    }

                    val m3CardInteractionSource = remember { MutableInteractionSource() }
                    Card(
                        onClick = {},
                        interactionSource = m3CardInteractionSource,
                        colors =
                            CardDefaults.cardColors(
                                containerColor = Color.Transparent,
                                contentColor = NeoTheme.colors.textPrimary,
                            ),
                        elevation =
                            CardDefaults.cardElevation(
                                defaultElevation = 0.dp,
                                pressedElevation = 0.dp,
                                focusedElevation = 0.dp,
                                hoveredElevation = 0.dp,
                            ),
                        shape = NeoTheme.shapes.medium,
                        modifier =
                            Modifier
                                .weight(1f)
                                .neoInteraction(
                                    interactionSource = m3CardInteractionSource,
                                    shape = NeoTheme.shapes.medium,
                                ),
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(NeoTheme.size.controlMedium),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text("M3 Card (Click)", style = NeoTheme.typography.label)
                        }
                    }
                }
            }
        }

        // 12. Floating Action Speed Dial
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            NeoSpeedDial(
                items =
                    listOf(
                        NeoSpeedDialItem("1", "New Message", Icons.Default.Notifications) {},
                        NeoSpeedDialItem("2", "Share Design", Icons.Default.Share) {},
                    ),
            )
        }
    }
}
