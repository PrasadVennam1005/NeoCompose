package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.animation.animateNeoElevationAsState
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.foundation.rememberNeoInteractionStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * An interactive checkable Neumorphic icon toggle button (ideal for Favorites/Bookmarks/Likes).
 *
 * Automatically transitions visual state between Checked Style (Pressed) and Unchecked Style (Raised).
 *
 * @param checked Whether the button is currently in checked state.
 * @param onCheckedChange Callback when checked state changes.
 * @param modifier Custom modifier.
 * @param enabled Whether interaction is active.
 * @param shape Component shape (defaults to [CircleShape]).
 * @param size Button diameter size (defaults to [NeoTheme.size.controlMedium]).
 * @param uncheckedStyle Neumorphic style when unchecked (defaults to [NeoStyle.Raised]).
 * @param checkedStyle Neumorphic style when checked (defaults to [NeoStyle.Pressed]).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Spec for animated rendering transitions.
 * @param interactionSource Interaction stream.
 * @param content Composable displaying the icon based on checking state.
 */
@Composable
public fun NeoIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    size: Dp = NeoTheme.size.controlMedium,
    uncheckedStyle: NeoStyle = NeoStyle.Raised,
    checkedStyle: NeoStyle = NeoStyle.Pressed,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable (isChecked: Boolean) -> Unit,
) {
    val defaultStyle = if (checked) checkedStyle else uncheckedStyle
    val pressedStyle = if (checked) NeoStyle.Inset else NeoStyle.Pressed

    val currentStyle by rememberNeoInteractionStyle(
        interactionSource = interactionSource,
        defaultStyle = defaultStyle,
        pressedStyle = pressedStyle,
    )

    val targetElevation = if (checked || currentStyle is NeoStyle.Pressed || currentStyle is NeoStyle.Inset) {
        elevation * 0.3f
    } else {
        elevation
    }

    val animatedElevation by animateNeoElevationAsState(
        targetElevation = targetElevation,
        animationSpec = animationSpec.elevationSpec,
    )

    val haptic = LocalHapticFeedback.current

    Box(
        modifier = modifier
            .size(size)
            .neoStyle(
                style = currentStyle,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = animatedElevation,
                lightSource = lightSource,
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Checkbox,
                onClick = {
                    if (enabled) {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onCheckedChange(!checked)
                    }
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        content(checked)
    }
}
