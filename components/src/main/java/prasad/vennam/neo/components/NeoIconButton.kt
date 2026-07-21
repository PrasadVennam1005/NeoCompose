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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.animation.animateNeoElevationAsState
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.foundation.rememberNeoInteractionStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Interactive Neumorphic icon button component with size derived from design tokens.
 *
 * @param onClick Event callback on click.
 * @param modifier Custom modifier.
 * @param enabled Whether button is enabled.
 * @param shape Component shape (defaults to [CircleShape]).
 * @param size Button diameter size derived from design tokens (defaults to [NeoTheme.size.controlMedium]).
 * @param style Base Neumorphic style.
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specifications.
 * @param interactionSource Interaction stream.
 * @param content Inner icon composable.
 */
@Composable
public fun NeoIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    size: Dp = NeoTheme.size.controlMedium,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
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
                lightSource = NeoTheme.lighting.lightSource
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
