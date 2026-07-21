package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
 * Neumorphic Floating Action Button (FAB).
 *
 * @param onClick Event callback on click.
 * @param modifier Custom modifier.
 * @param enabled Whether FAB is enabled.
 * @param shape Component shape (defaults to [CircleShape]).
 * @param style Base Neumorphic surface style.
 * @param elevation High elevation depth.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specs.
 * @param interactionSource Interaction stream.
 * @param content FAB icon/label composable content.
 */
@Composable
public fun NeoFAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level4,
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
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}
