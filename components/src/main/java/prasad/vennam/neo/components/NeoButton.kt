package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.animation.animateNeoElevationAsState
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.foundation.rememberNeoInteractionStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Interactive Neumorphic button component with automatic press elevation feedback.
 *
 * @param onClick Event callback when button is clicked.
 * @param modifier Custom modifier.
 * @param enabled Whether button is interactive.
 * @param shape Button corner shape.
 * @param style Base Neumorphic style when unpressed.
 * @param elevation Base shadow displacement elevation.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specifications.
 * @param interactionSource Stream of interaction events.
 * @param content Button label and icon composable content.
 */
@Composable
public fun NeoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = NeoTheme.shapes.medium,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    val currentStyle by rememberNeoInteractionStyle(
        interactionSource = interactionSource,
        defaultStyle = style,
        pressedStyle = NeoStyle.Pressed
    )

    val targetElevation = if (currentStyle is NeoStyle.Pressed) elevation * 0.3f else elevation
    val animatedElevation by animateNeoElevationAsState(
        targetElevation = targetElevation,
        animationSpec = animationSpec.elevationSpec
    )

    Row(
        modifier = modifier
            .neoStyle(
                style = currentStyle,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = animatedElevation,
                lightSource = NeoTheme.lighting.lightSource
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            )
            .padding(horizontal = 24.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}
