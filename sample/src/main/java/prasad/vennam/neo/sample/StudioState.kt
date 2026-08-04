package prasad.vennam.neo.sample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import prasad.vennam.neo.core.NeoStyle

public enum class CatalogScreen(val title: String, val description: String) {
    Index("Catalog Index", ""),
    NeoSurface("1. NeoSurface (Standard & Dynamic Descriptors)", "Raised, pressed, inset, concave, convex, basin, flat, glass, glow, clay, metallic, and custom styles."),
    Buttons("2. Buttons & Icon Buttons", "Neumorphic button variants, circular icon buttons, and custom interactions."),
    FormInputs("3. Form Inputs & Select Controls", "Neumorphic text fields, dropdown menus, checkboxes, switches, and radio buttons."),
    Pickers("4. Date & Time Pickers", "Fully functional calendar date selector and analog hour/minute time picker."),
    Navigation("5. Navigation Bars, TopBar, Rail & Drawer", "Top app bars, bottom navigation bars, navigation rails, navigation drawers, segmented controls & tabs."),
    Sliders("6. Range Slider & Number Stepper", "Interactive dual-thumb range sliders and numeric counter stepper controls."),
    AudioPlayer("7. Media & Audio Player Controller", "A media controller with basin play buttons and tracking timelines."),
    Banners("8. Tooltips, Banners & Bottom Sheets", "Neumorphic toast alerts, floating speech bubble tooltips, and bottom sheets."),
    Avatars("9. Chips, Badges & User Avatars", "Selectable chips, notification badge counts, and user profile indicators."),
    Progress("10. Slider & Progress Indicators", "Standard progress bar tracks and circular ring status loader indicators."),
    Material3("11. Material 3 Interoperability", "Auto-draping M3 buttons and cards using the interaction modifier."),
    Advanced("12. Advanced Controls (v2.1.0)", "Compose Multiplatform custom circular sliders, dial gauges, and card carousels."),
    SpeedDial("13. Floating Action Speed Dial", "Floating action menu expanding vertically on hover/tap with multiple speed dial items."),
    IconToggleButton("14. Icon Toggle Button (v2.1.0)", "Interactive checked/unchecked icon state morphing with neumorphic style transitions."),
    Glassmorphism("15. Glassmorphism Studio (v2.2.0)", "Interactive frosted glass cards, translucent translucency tuning, and glowing glass UI controls."),
    NeoImage("17. NeoImage & NeoAsyncImage (v2.4.0)", "Neumorphic image frames with async loading states, specular highlights, and custom shapes.")
}

public class StudioState {
    public var selectedCategoryTab: Int by mutableIntStateOf(0)
    public var selectedComponentRoute: CatalogScreen by mutableStateOf(CatalogScreen.Index)
    public val styleOptions: List<String> = listOf(
        "Raised", "Pressed", "Inset", "Concave", "Convex", "Basin", "Flat",
        "Glassmorphism", "Neon Glow", "Pastel Clay", "Brushed Metal", "Carbon Fiber"
    )
    public var selectedStyleIndex: Int by mutableIntStateOf(0)

    // Interactive Glassmorphism Showcase State
    public var glassAlpha: Float by mutableFloatStateOf(0.45f)
    public var glassBlurRadiusDp: Float by mutableFloatStateOf(12f)
    public var glassFrosted: Boolean by mutableStateOf(true)

    // Component ViewState Demo States
    public var demoButtonViewState: prasad.vennam.neo.core.NeoButtonViewState by mutableStateOf(
        prasad.vennam.neo.core.NeoButtonViewState(label = "ViewState Action", isEnabled = true)
    )
    public var demoTextFieldViewState: prasad.vennam.neo.core.NeoTextFieldViewState by mutableStateOf(
        prasad.vennam.neo.core.NeoTextFieldViewState(text = "sample@domain.com", placeholder = "Enter email address")
    )
    public var demoSwitchViewState: prasad.vennam.neo.core.NeoSwitchViewState by mutableStateOf(
        prasad.vennam.neo.core.NeoSwitchViewState(isChecked = true)
    )



    public val selectedStyle: NeoStyle
        get() =
            when (selectedStyleIndex) {
                0 -> NeoStyle.Raised
                1 -> NeoStyle.Pressed
                2 -> NeoStyle.Inset
                3 -> NeoStyle.Concave
                4 -> NeoStyle.Convex
                5 -> NeoStyle.Basin
                6 -> NeoStyle.Flat
                7 -> NeoStyle.Glass()
                8 -> NeoStyle.Glow(glowColor = Color(0xFF00E5FF))
                9 -> NeoStyle.Clay(primaryColor = Color(0xFFFFB74D))
                10 -> NeoStyle.Metallic(finish = NeoStyle.Metallic.Finish.Brushed)
                11 -> NeoStyle.Metallic(finish = NeoStyle.Metallic.Finish.Carbon)
                else -> NeoStyle.Raised
            }

    public var isDarkTheme: Boolean by mutableStateOf(false)
    public var isAccessibilityMode: Boolean by mutableStateOf(false)
    public var isSensorLightingEnabled: Boolean by mutableStateOf(false)
    public var isGlossyShineEnabled: Boolean by mutableStateOf(false)
    public var isAmbientContrastEnabled: Boolean by mutableStateOf(false)
    public var isParallaxTiltEnabled: Boolean by mutableStateOf(false)
    public var lightAngle: Float by mutableFloatStateOf(315f)
    public var elevationDp: Float by mutableFloatStateOf(6f)

    public var textInputValue: String by mutableStateOf("hello@gmail.com")
    public var searchQuery: String by mutableStateOf("Neumorphism")
    public var selectedCountry: String by mutableStateOf("Australia")
    public var isCheckboxChecked: Boolean by mutableStateOf(true)
    public var isSwitchChecked: Boolean by mutableStateOf(true)
    public var isRadioSelected: Boolean by mutableStateOf(true)
    public var segmentedIndex: Int by mutableIntStateOf(0)
    public var chipSelected: Boolean by mutableStateOf(true)
    public var tabBarIndex: Int by mutableIntStateOf(0)
    public var sliderValue: Float by mutableFloatStateOf(0.6f)
    public var rangeSliderValues: ClosedFloatingPointRange<Float> by mutableStateOf(0.25f..0.75f)
    public var stepperValue: Int by mutableIntStateOf(3)
    public var progressValue: Float by mutableFloatStateOf(0.75f)

    // Audio Player State
    public var isAudioPlaying: Boolean by mutableStateOf(true)
    public var audioProgress: Float by mutableFloatStateOf(0.42f)

    // Banner, Dialog & Sheet State
    public var showBanner: Boolean by mutableStateOf(true)
    public var showDialog: Boolean by mutableStateOf(false)
    public var showBottomSheet: Boolean by mutableStateOf(false)
    public var showTooltip: Boolean by mutableStateOf(true)

    // Date & Time Picker State
    public var selectedDay: Int by mutableIntStateOf(21)
    public var pickerHour: Int by mutableIntStateOf(10)
    public var pickerMinute: Int by mutableIntStateOf(30)
    public var isPickerAm: Boolean by mutableStateOf(true)

    // Smart Home Dashboard State
    public var isAcOn: Boolean by mutableStateOf(true)
    public var isLightsOn: Boolean by mutableStateOf(true)
    public var isSecurityOn: Boolean by mutableStateOf(false)
    public var roomTemperature: Float by mutableFloatStateOf(22f)

    // Interactive Sandbox State
    public var sandboxSelectedComponent: String by mutableStateOf("NeoButton")
    public var sandboxStyleIndex: Int by mutableIntStateOf(0)
    public var sandboxButtonLabel: String by mutableStateOf("Neo Action")
    public var sandboxButtonEnabled: Boolean by mutableStateOf(true)
    public var sandboxCardElevation: Float by mutableFloatStateOf(6f)
    public var sandboxCardSpecular: Boolean by mutableStateOf(false)
    public var sandboxSwitchChecked: Boolean by mutableStateOf(true)
    public var sandboxSliderValue: Float by mutableFloatStateOf(0.5f)
    public var sandboxCodeCopied: Boolean by mutableStateOf(false)
    public var textSizeMultiplier: Float by mutableFloatStateOf(1.0f)
}
