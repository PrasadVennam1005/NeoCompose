package prasad.vennam.neo.foundation

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import prasad.vennam.neo.core.NeoStyle

/**
 * Derives the active [NeoStyle] from user press gestures on an [InteractionSource].
 *
 * @param interactionSource Stream of interaction events.
 * @param defaultStyle Base style when component is idle.
 * @param pressedStyle Style when component is pressed (defaults to [NeoStyle.Pressed]).
 * @return [State] providing the current effective [NeoStyle].
 */
@Composable
public fun rememberNeoInteractionStyle(
    interactionSource: InteractionSource,
    defaultStyle: NeoStyle = NeoStyle.Raised,
    pressedStyle: NeoStyle = NeoStyle.Pressed,
): State<NeoStyle> {
    val isPressed = interactionSource.collectIsPressedAsState()
    return remember(defaultStyle, pressedStyle) {
        derivedStateOf {
            if (isPressed.value) pressedStyle else defaultStyle
        }
    }
}
