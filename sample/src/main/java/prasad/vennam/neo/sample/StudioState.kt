package prasad.vennam.neo.sample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

public class StudioState {
    public var selectedCategoryTab: Int by mutableIntStateOf(0)
    public var isDarkTheme: Boolean by mutableStateOf(false)
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
}
