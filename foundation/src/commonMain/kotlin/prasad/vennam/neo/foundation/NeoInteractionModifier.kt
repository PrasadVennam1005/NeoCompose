package prasad.vennam.neo.foundation

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoTheme

/**
 * An interoperability modifier that observes click, focus, and hover interaction states
 * to dynamically apply Neumorphic raised or pressed shadows and styles onto custom or
 * standard Material 3 layouts.
 *
 * To render properly, standard Material 3 components should have their default container/surface
 * colors set to transparent or matching backgrounds, and their default elevation/shadows disabled.
 *
 * @param interactionSource Source of interactions to observe.
 * @param shape Shape of the component.
 * @param backgroundColor Surface background color. Defaults to [NeoTheme.colors.surface] if unspecified.
 * @param lightColor Light highlight shadow color. Defaults to [NeoTheme.colors.lightShadow] if unspecified.
 * @param darkColor Dark shadow color. Defaults to [NeoTheme.colors.darkShadow] if unspecified.
 * @param elevation Base displacement distance for the shadow.
 * @param lightSource Directional light source configuration.
 */
public fun Modifier.neoInteraction(
    interactionSource: InteractionSource,
    shape: Shape,
    backgroundColor: Color = Color.Unspecified,
    lightColor: Color = Color.Unspecified,
    darkColor: Color = Color.Unspecified,
    elevation: Dp = 6.dp,
    lightSource: NeoLightSource = NeoLightSource.TopLeft,
): Modifier =
    this.composed {
        val isPressed by interactionSource.collectIsPressedAsState()
        val isFocused by interactionSource.collectIsFocusedAsState()
        val isHovered by interactionSource.collectIsHoveredAsState()

        val colors = NeoTheme.colors
        val resolvedBg = if (backgroundColor == Color.Unspecified) colors.surface else backgroundColor
        val resolvedLight = if (lightColor == Color.Unspecified) colors.lightShadow else lightColor
        val resolvedDark = if (darkColor == Color.Unspecified) colors.darkShadow else darkColor

        // Transition styles based on interaction
        val style =
            when {
                isPressed -> NeoStyle.Pressed
                else -> NeoStyle.Raised
            }

        val activeElevation = if (isFocused || isHovered) elevation * 1.25f else elevation

        this.neoStyle(
            style = style,
            shape = shape,
            backgroundColor = resolvedBg,
            lightColor = resolvedLight,
            darkColor = resolvedDark,
            elevation = activeElevation,
            lightSource = lightSource,
        )
    }
