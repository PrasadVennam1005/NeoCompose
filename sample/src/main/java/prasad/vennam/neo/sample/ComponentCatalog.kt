package prasad.vennam.neo.sample

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import prasad.vennam.neo.components.NeoCircularSlider
import prasad.vennam.neo.components.NeoDialGauge
import prasad.vennam.neo.components.NeoCardCarousel
import prasad.vennam.neo.components.NeoIconToggleButton
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
            "NeoButton" -> {
                """
                NeoButton(
                    onClick = { /* Handle Click */ },
                    style = NeoStyle.$sandboxStyleName,
                    enabled = ${state.sandboxButtonEnabled}
                ) {
                    Text("${state.sandboxButtonLabel}")
                }
                """.trimIndent()
            }
            "NeoCard" -> {
                """
                NeoCard(
                    style = NeoStyle.$sandboxStyleName,
                    elevation = ${state.sandboxCardElevation}.dp,
                    specularHighlight = ${state.sandboxCardSpecular}
                ) {
                    Text("Preview Card")
                }
                """.trimIndent()
            }
            "NeoSwitch" -> {
                """
                NeoSwitch(
                    checked = ${state.sandboxSwitchChecked},
                    onCheckedChange = { isChecked -> },
                    style = NeoStyle.$sandboxStyleName
                )
                """.trimIndent()
            }
            else -> {
                """
                NeoSlider(
                    value = ${state.sandboxSliderValue},
                    onValueChange = { value -> }
                )
                """.trimIndent()
            }
        }

    LaunchedEffect(state.sandboxSelectedComponent, state.sandboxStyleIndex, state.sandboxButtonLabel, state.sandboxButtonEnabled, state.sandboxCardElevation, state.sandboxCardSpecular, state.sandboxSwitchChecked, state.sandboxSliderValue) {
        state.sandboxCodeCopied = false
    }

    NeoCard(style = state.selectedStyle) {
        Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
            Text(
                text = "Interactive Customizer Sandbox",
                style = NeoTheme.typography.title,
                color = NeoTheme.colors.textPrimary,
            )

            // Selector Control
            Text(
                text = "Select Component Type",
                style = NeoTheme.typography.label,
                color = NeoTheme.colors.textSecondary,
            )
            NeoSegmentedControl(
                items = componentOptions,
                selectedIndex = componentOptions.indexOf(state.sandboxSelectedComponent),
                onOptionSelected = { state.sandboxSelectedComponent = componentOptions[it] },
            )

            Text(
                text = "Select Neumorphic Style Profile",
                style = NeoTheme.typography.label,
                color = NeoTheme.colors.textSecondary,
            )
            NeoDropdownMenu(
                selectedOption = sandboxStyleName,
                options = state.styleOptions,
                onOptionSelected = { state.sandboxStyleIndex = state.styleOptions.indexOf(it) },
            )

            // Conditional parameters
            when (state.sandboxSelectedComponent) {
                "NeoButton" -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        NeoTextField(
                            value = state.sandboxButtonLabel,
                            onValueChange = { state.sandboxButtonLabel = it },
                            placeholder = "Button Text",
                            modifier = Modifier.weight(1f),
                        )
                        Spacer(Modifier.width(NeoTheme.spacing.medium))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            NeoCheckbox(
                                checked = state.sandboxButtonEnabled,
                                onCheckedChange = { state.sandboxButtonEnabled = it },
                            )
                            Spacer(Modifier.width(NeoTheme.spacing.extraSmall))
                            Text("Enabled", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)
                        }
                    }
                }
                "NeoCard" -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "Elevation Level: ${state.sandboxCardElevation.roundToInt()}dp",
                                style = NeoTheme.typography.body,
                                color = NeoTheme.colors.textPrimary,
                            )
                            NeoSlider(
                                value = state.sandboxCardElevation / 12f,
                                onValueChange = { state.sandboxCardElevation = (it * 12f).coerceIn(1f, 12f) },
                            )
                        }
                        Spacer(Modifier.width(NeoTheme.spacing.medium))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            NeoCheckbox(
                                checked = state.sandboxCardSpecular,
                                onCheckedChange = { state.sandboxCardSpecular = it },
                            )
                            Spacer(Modifier.width(NeoTheme.spacing.extraSmall))
                            Text("Specular Shine", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)
                        }
                    }
                }
            }

            // Interactive Preview Area
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                contentAlignment = Alignment.Center,
            ) {
                // Background shadow plate
                NeoSurface(
                    modifier = Modifier.fillMaxSize(),
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
                                    onClick = { },
                                    style = sandboxStyle,
                                    enabled = state.sandboxButtonEnabled,
                                ) {
                                    Text(
                                        text = state.sandboxButtonLabel,
                                        style = NeoTheme.typography.label,
                                        color = if (state.sandboxButtonEnabled) NeoTheme.colors.textPrimary else NeoTheme.colors.textSecondary.copy(alpha = 0.5f),
                                    )
                                }
                            }
                            "NeoCard" -> {
                                NeoCard(
                                    style = sandboxStyle,
                                    elevation = state.sandboxCardElevation.dp,
                                    specularHighlight = state.sandboxCardSpecular,
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
    var circularValue by remember { androidx.compose.runtime.mutableStateOf(0.45f) }
    val route = state.selectedComponentRoute

    // Intercept system back press when viewing component details
    if (route != CatalogScreen.Index) {
        BackHandler {
            state.selectedComponentRoute = CatalogScreen.Index
        }
    }

    AnimatedContent(
        targetState = route,
        transitionSpec = {
            if (targetState == CatalogScreen.Index) {
                (slideInHorizontally { -it } + fadeIn()) togetherWith (slideOutHorizontally { it } + fadeOut())
            } else {
                (slideInHorizontally { it } + fadeIn()) togetherWith (slideOutHorizontally { -it } + fadeOut())
            }
        },
        label = "CatalogNavigationTransition"
    ) { targetRoute ->
        if (targetRoute == CatalogScreen.Index) {
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

                Text(
                    text = "Explore Component Suites",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )

                CatalogNavigationTiles(state)
            }
        } else {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.large),
            ) {
                // Header Top Bar with Back button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)
                ) {
                    NeoIconButton(
                        onClick = { state.selectedComponentRoute = CatalogScreen.Index },
                        style = NeoStyle.Raised,
                        size = 40.dp
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Catalog Index",
                            tint = NeoTheme.colors.primary
                        )
                    }
                    Text(
                        text = targetRoute.title,
                        style = NeoTheme.typography.title,
                        color = NeoTheme.colors.textPrimary
                    )
                }

                // Display active route component showcase
                when (targetRoute) {
                    CatalogScreen.Index -> {} // Handled
                    CatalogScreen.NeoSurface -> NeoSurfaceShowcase(state)
                    CatalogScreen.Buttons -> ButtonsShowcase(state)
                    CatalogScreen.FormInputs -> FormInputsShowcase(state)
                    CatalogScreen.Pickers -> PickersShowcase(state)
                    CatalogScreen.Navigation -> NavigationShowcase(state)
                    CatalogScreen.Sliders -> SlidersShowcase(state)
                    CatalogScreen.AudioPlayer -> AudioPlayerShowcase(state)
                    CatalogScreen.Banners -> BannersShowcase(state)
                    CatalogScreen.Avatars -> AvatarsShowcase(state)
                    CatalogScreen.Progress -> ProgressShowcase(state)
                    CatalogScreen.Material3 -> Material3Showcase(state)
                    CatalogScreen.Advanced -> AdvancedShowcase(state, circularValue) { circularValue = it }
                    CatalogScreen.SpeedDial -> SpeedDialShowcase()
                    CatalogScreen.IconToggleButton -> IconToggleButtonShowcase(state)
                    CatalogScreen.Glassmorphism -> GlassmorphismShowcase(state)
                }
            }
        }
    }
}

@Composable
private fun CatalogNavigationTiles(state: StudioState) {
    val screens = CatalogScreen.entries.filter { it != CatalogScreen.Index }

    Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
        screens.forEach { screen ->
            NeoCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { state.selectedComponentRoute = screen },
                style = NeoStyle.Raised,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(NeoTheme.spacing.medium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = screen.title,
                            style = NeoTheme.typography.title,
                            color = NeoTheme.colors.textPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = screen.description,
                            style = NeoTheme.typography.body,
                            color = NeoTheme.colors.textSecondary
                        )
                    }
                    Spacer(modifier = Modifier.width(NeoTheme.spacing.medium))
                    NeoIconButton(
                        onClick = { state.selectedComponentRoute = screen },
                        size = 36.dp,
                        style = NeoStyle.Raised,
                        elevation = NeoTheme.elevation.level2
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Navigate to " + screen.name,
                            tint = NeoTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NeoSurfaceShowcase(state: StudioState) {
    NeoCard(style = state.selectedStyle) {
        Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
            Text(
                "1. NeoSurface (Standard & Dynamic Descriptors Showcase)",
                style = NeoTheme.typography.title,
                color = NeoTheme.colors.textPrimary,
            )

            Text("Core Neumorphic Archetypes", style = NeoTheme.typography.caption, color = NeoTheme.colors.textSecondary)
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
                        modifier = Modifier.weight(1f).height(NeoTheme.size.controlMedium),
                        style = style,
                    ) {
                        Text(label, style = NeoTheme.typography.label, color = NeoTheme.colors.textSecondary)
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
                        modifier = Modifier.weight(1f).height(NeoTheme.size.controlMedium),
                        style = style,
                    ) {
                        Text(label, style = NeoTheme.typography.label, color = NeoTheme.colors.textSecondary)
                    }
                }
            }

            Text("Dynamic Extensible Descriptors", style = NeoTheme.typography.caption, color = NeoTheme.colors.textSecondary)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
            ) {
                listOf(
                    NeoStyle.Glass() to "Glass",
                    NeoStyle.Glow(glowColor = Color(0xFF00E5FF)) to "Neon Glow",
                    NeoStyle.Clay(primaryColor = Color(0xFFFFB74D)) to "Pastel Clay",
                ).forEach { (style, label) ->
                    NeoSurface(
                        modifier = Modifier.weight(1f).height(NeoTheme.size.controlMedium),
                        style = style,
                    ) {
                        Text(label, style = NeoTheme.typography.label, color = NeoTheme.colors.textPrimary)
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
            ) {
                listOf(
                    NeoStyle.Metallic(finish = NeoStyle.Metallic.Finish.Brushed) to "Brushed Metal",
                    NeoStyle.Metallic(finish = NeoStyle.Metallic.Finish.Chrome) to "Chrome",
                    NeoStyle.Metallic(finish = NeoStyle.Metallic.Finish.Carbon) to "Carbon Fiber",
                ).forEach { (style, label) ->
                    NeoSurface(
                        modifier = Modifier.weight(1f).height(NeoTheme.size.controlMedium),
                        style = style,
                    ) {
                        Text(label, style = NeoTheme.typography.label, color = NeoTheme.colors.textPrimary)
                    }
                }
            }
        }
    }
}


@Composable
private fun ButtonsShowcase(state: StudioState) {
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
}

@Composable
private fun FormInputsShowcase(state: StudioState) {
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
}

@Composable
private fun PickersShowcase(state: StudioState) {
    Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.large)) {
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
    }
}

@Composable
private fun NavigationShowcase(state: StudioState) {
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
}

@Composable
private fun SlidersShowcase(state: StudioState) {
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
}

@Composable
private fun AudioPlayerShowcase(state: StudioState) {
    Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BannersShowcase(state: StudioState) {
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
}

@Composable
private fun AvatarsShowcase(state: StudioState) {
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
}

@Composable
private fun ProgressShowcase(state: StudioState) {
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
}

@Composable
private fun Material3Showcase(state: StudioState) {
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
}

@Composable
private fun AdvancedShowcase(
    state: StudioState,
    circularValue: Float,
    onCircularValueChange: (Float) -> Unit
) {
    var localCircularValue = circularValue
    NeoCard(style = state.selectedStyle) {
        Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
            Text(
                text = "12. Advanced Controls (v2.1.0)",
                style = NeoTheme.typography.title,
                color = NeoTheme.colors.textPrimary,
            )

            Text(
                text = "Showcasing custom circular controls and page carousels built with Compose Multiplatform. Interact with the circular slider to drive the dial gauge value.",
                style = NeoTheme.typography.body,
                color = NeoTheme.colors.textSecondary,
            )

            // Circular Controls Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.large),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // NeoCircularSlider
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small)
                ) {
                    Text("NeoCircularSlider", style = NeoTheme.typography.label)
                    NeoCircularSlider(
                        value = localCircularValue,
                        onValueChange = onCircularValueChange,
                        colors = NeoTheme.colors,
                        lightSource = NeoTheme.lighting.lightSource
                    )
                    Text("${(localCircularValue * 100).roundToInt()}%", style = NeoTheme.typography.body)
                }

                // NeoDialGauge
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small)
                ) {
                    Text("NeoDialGauge", style = NeoTheme.typography.label)
                    NeoDialGauge(
                        value = localCircularValue,
                        colors = NeoTheme.colors,
                        lightSource = NeoTheme.lighting.lightSource
                    ) {
                        Text(
                            text = "${(localCircularValue * 100).roundToInt()}",
                            style = NeoTheme.typography.title,
                            color = NeoTheme.colors.primary
                        )
                    }
                    Text("Live Gauge Dial", style = NeoTheme.typography.body)
                }
            }

            // NeoCardCarousel
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small)
            ) {
                Text("NeoCardCarousel", style = NeoTheme.typography.label)
                val items = remember { listOf("Material 3 Interop", "Pre-rendered Shadows", "120fps Elevation Physics", "Compose Multiplatform") }
                NeoCardCarousel(
                    items = items,
                    cardHeight = 120.dp,
                    colors = NeoTheme.colors,
                    lightSource = NeoTheme.lighting.lightSource
                ) { item ->
                    Box(
                        modifier = Modifier.fillMaxSize().padding(NeoTheme.spacing.medium),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item,
                            style = NeoTheme.typography.title,
                            color = NeoTheme.colors.textPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SpeedDialShowcase() {
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

@Composable
private fun IconToggleButtonShowcase(state: StudioState) {
    NeoCard(style = state.selectedStyle) {
        Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
            Text(
                text = "14. Icon Toggle Button (v2.1.0)",
                style = NeoTheme.typography.title,
                color = NeoTheme.colors.textPrimary,
            )

            Text(
                text = "Interactive checkable icon buttons that toggle between Checked (Pressed) and Unchecked (Raised) neumorphic styles with animated transitions.",
                style = NeoTheme.typography.body,
                color = NeoTheme.colors.textSecondary,
            )

            // Favorite Heart
            var isFavorite by remember { androidx.compose.runtime.mutableStateOf(false) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                    Text("Favorite Heart", style = NeoTheme.typography.label)
                    Text(
                        text = if (isFavorite) "Checked — Pressed" else "Unchecked — Raised",
                        style = NeoTheme.typography.caption,
                        color = NeoTheme.colors.textSecondary
                    )
                }
                NeoIconToggleButton(
                    checked = isFavorite,
                    onCheckedChange = { isFavorite = it },
                    colors = NeoTheme.colors,
                    lightSource = NeoTheme.lighting.lightSource
                ) { isChecked ->
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = if (isChecked) NeoTheme.colors.primary else NeoTheme.colors.textSecondary.copy(alpha = 0.5f)
                    )
                }
            }

            // Notification Bell
            var isNotification by remember { androidx.compose.runtime.mutableStateOf(false) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                    Text("Notification Bell", style = NeoTheme.typography.label)
                    Text(
                        text = if (isNotification) "Alerts On" else "Alerts Off",
                        style = NeoTheme.typography.caption,
                        color = NeoTheme.colors.textSecondary
                    )
                }
                NeoIconToggleButton(
                    checked = isNotification,
                    onCheckedChange = { isNotification = it },
                    colors = NeoTheme.colors,
                    lightSource = NeoTheme.lighting.lightSource
                ) { isChecked ->
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = if (isChecked) NeoTheme.colors.primary else NeoTheme.colors.textSecondary.copy(alpha = 0.5f)
                    )
                }
            }

            // Share Bookmark
            var isStarred by remember { androidx.compose.runtime.mutableStateOf(true) }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                    Text("Bookmark Share", style = NeoTheme.typography.label)
                    Text(
                        text = if (isStarred) "Bookmarked" else "Not Bookmarked",
                        style = NeoTheme.typography.caption,
                        color = NeoTheme.colors.textSecondary
                    )
                }
                NeoIconToggleButton(
                    checked = isStarred,
                    onCheckedChange = { isStarred = it },
                    colors = NeoTheme.colors,
                    lightSource = NeoTheme.lighting.lightSource
                ) { isChecked ->
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = if (isChecked) NeoTheme.colors.primary else NeoTheme.colors.textSecondary.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}

@Composable
private fun GlassmorphismShowcase(state: StudioState) {
    val glassStyle = NeoStyle.Glass(
        blurRadius = state.glassBlurRadiusDp.dp,
        alpha = state.glassAlpha,
        borderAlpha = (state.glassAlpha * 0.7f).coerceIn(0.1f, 0.8f),
        frosted = state.glassFrosted
    )

    Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
        // Interactive Controls Card
        NeoCard(style = NeoStyle.Raised) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small)) {
                Text("Glassmorphism Studio Customizer", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)

                // Translucency Alpha Slider
                Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                    Text("Opacity / Translucency Alpha: ${(state.glassAlpha * 100).toInt()}%", style = NeoTheme.typography.label)
                    NeoSlider(
                        value = state.glassAlpha,
                        onValueChange = { state.glassAlpha = it },
                        valueRange = 0.1f..0.9f
                    )
                }

                // Blur Radius Slider
                Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                    Text("Frosted Blur Radius: ${state.glassBlurRadiusDp.toInt()} dp", style = NeoTheme.typography.label)
                    NeoSlider(
                        value = state.glassBlurRadiusDp,
                        onValueChange = { state.glassBlurRadiusDp = it },
                        valueRange = 4f..28f
                    )
                }

                // Frosted Specular Rim Switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Frosted Specular Rim Highlight", style = NeoTheme.typography.body)
                    NeoSwitch(
                        checked = state.glassFrosted,
                        onCheckedChange = { state.glassFrosted = it }
                    )
                }
            }
        }

        // Glassmorphism Live UI Preview Card
        NeoCard(style = glassStyle) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Live Frosted Glass Surface", style = NeoTheme.typography.title, color = NeoTheme.colors.textPrimary)
                        Text("Translucent glass background with specular border rim", style = NeoTheme.typography.caption, color = NeoTheme.colors.textSecondary)
                    }
                    NeoBadge(count = "GLASS")
                }

                NeoSearchField(
                    query = state.searchQuery,
                    onQueryChange = { state.searchQuery = it },
                    placeholder = "Search in glass UI...",
                    style = glassStyle
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small)
                ) {
                    NeoButton(
                        onClick = {},
                        style = glassStyle,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Glass Action")
                    }

                    NeoButton(
                        onClick = {},
                        style = NeoStyle.Glow(glowColor = Color(0xFF00E5FF)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cyan Glow")
                    }
                }
            }
        }
    }
}

