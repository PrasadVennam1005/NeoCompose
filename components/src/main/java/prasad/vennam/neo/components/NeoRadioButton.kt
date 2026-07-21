package prasad.vennam.neo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Interactive Neumorphic Radio Button component.
 *
 * @param selected Whether radio button is selected.
 * @param onClick Event callback when selected.
 * @param modifier Custom modifier.
 * @param enabled Whether control is interactive.
 * @param shape Component shape (defaults to [CircleShape]).
 * @param style Surface style ([NeoStyle.Inset] when selected, [NeoStyle.Raised] when unselected).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specifications.
 * @param interactionSource Interaction stream.
 */
@Composable
public fun NeoRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    style: NeoStyle = if (selected) NeoStyle.Inset else NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level2,
    colors: NeoColors = NeoTheme.colors,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val selectModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            role = Role.RadioButton,
            onClick = onClick
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .size(28.dp)
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = NeoTheme.lighting.lightSource
            )
            .then(selectModifier),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = selected,
            enter = fadeIn(androidx.compose.animation.core.tween(200)),
            exit = fadeOut(androidx.compose.animation.core.tween(200))
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .neoStyle(
                        style = NeoStyle.Raised,
                        shape = CircleShape,
                        backgroundColor = colors.primary,
                        lightColor = colors.lightShadow,
                        darkColor = colors.darkShadow,
                        elevation = NeoTheme.elevation.level1,
                        lightSource = NeoTheme.lighting.lightSource
                    )
            )
        }
    }
}
