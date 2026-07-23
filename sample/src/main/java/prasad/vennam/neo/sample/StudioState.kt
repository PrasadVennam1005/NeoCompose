package prasad.vennam.neo.sample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import prasad.vennam.neo.core.NeoStyle

public class StudioState {
    public var selectedCategoryTab: Int by mutableIntStateOf(0)
    public val styleOptions: List<String> = listOf("Raised", "Pressed", "Inset", "Concave", "Convex", "Basin", "Flat")
    public var selectedStyleIndex: Int by mutableIntStateOf(0)

    public val selectedStyle: NeoStyle
        get() =
            when (selectedStyleIndex) {
                0 -> NeoStyle.Raised
                1 -> NeoStyle.Pressed
                2 -> NeoStyle.Inset
                3 -> NeoStyle.Concave
                4 -> NeoStyle.Convex
                5 -> NeoStyle.Basin
                else -> NeoStyle.Flat
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
}
