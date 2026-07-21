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

    public var textInputValue: String by mutableStateOf("Neumorphism Studio")
    public var isCheckboxChecked: Boolean by mutableStateOf(true)
    public var isSwitchChecked: Boolean by mutableStateOf(true)
    public var isRadioSelected: Boolean by mutableStateOf(true)
    public var segmentedIndex: Int by mutableIntStateOf(0)
    public var chipSelected: Boolean by mutableStateOf(true)
    public var tabBarIndex: Int by mutableIntStateOf(0)
    public var sliderValue: Float by mutableFloatStateOf(0.6f)
    public var progressValue: Float by mutableFloatStateOf(0.75f)

    // Smart Home Dashboard State
    public var isAcOn: Boolean by mutableStateOf(true)
    public var isLightsOn: Boolean by mutableStateOf(true)
    public var isSecurityOn: Boolean by mutableStateOf(false)
    public var roomTemperature: Float by mutableFloatStateOf(22f)
}
