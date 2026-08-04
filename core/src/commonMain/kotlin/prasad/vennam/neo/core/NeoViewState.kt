package prasad.vennam.neo.core

import androidx.compose.runtime.Immutable

/**
 * Standard interaction and visual state enumeration for Neumorphic components.
 */
@Immutable
public enum class NeoInteractionState {
    Default,
    Pressed,
    Focused,
    Hovered,
    Disabled,
    Loading,
    Error,
    Success,
}

/**
 * ViewState data model for Action Buttons.
 */
@Immutable
public data class NeoButtonViewState(
    val label: String = "",
    val isEnabled: Boolean = true,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val interactionState: NeoInteractionState = when {
        !isEnabled -> NeoInteractionState.Disabled
        isLoading -> NeoInteractionState.Loading
        isError -> NeoInteractionState.Error
        else -> NeoInteractionState.Default
    },
)

/**
 * ViewState data model for Input Fields.
 */
@Immutable
public data class NeoTextFieldViewState(
    val text: String = "",
    val placeholder: String = "",
    val isEnabled: Boolean = true,
    val isFocused: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val interactionState: NeoInteractionState = when {
        !isEnabled -> NeoInteractionState.Disabled
        isError -> NeoInteractionState.Error
        isFocused -> NeoInteractionState.Focused
        else -> NeoInteractionState.Default
    },
)

/**
 * ViewState data model for Switches & Toggles.
 */
@Immutable
public data class NeoSwitchViewState(
    val isChecked: Boolean = false,
    val isEnabled: Boolean = true,
    val isLoading: Boolean = false,
    val interactionState: NeoInteractionState = when {
        !isEnabled -> NeoInteractionState.Disabled
        isLoading -> NeoInteractionState.Loading
        else -> NeoInteractionState.Default
    },
)

/**
 * ViewState data model for Checkboxes.
 */
@Immutable
public data class NeoCheckboxViewState(
    val isChecked: Boolean = false,
    val isEnabled: Boolean = true,
    val isError: Boolean = false,
    val interactionState: NeoInteractionState = when {
        !isEnabled -> NeoInteractionState.Disabled
        isError -> NeoInteractionState.Error
        else -> NeoInteractionState.Default
    },
)

/**
 * ViewState data model for Progress & Status Indicators.
 */
@Immutable
public data class NeoProgressViewState(
    val progress: Float = 0f,
    val isIndeterminate: Boolean = false,
    val isError: Boolean = false,
    val label: String? = null,
    val interactionState: NeoInteractionState = when {
        isError -> NeoInteractionState.Error
        isIndeterminate -> NeoInteractionState.Loading
        else -> NeoInteractionState.Default
    },
)
