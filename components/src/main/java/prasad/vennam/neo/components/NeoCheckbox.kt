package prasad.vennam.neo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * High-visibility interactive Neumorphic checkbox control using design system tokens.
 *
 * @param checked Whether box is currently checked.
 * @param onCheckedChange Callback on toggle.
 * @param modifier Custom modifier.
 * @param enabled Whether control is interactive.
 * @param shape Box shape.
 * @param size Control box size derived from design tokens.
 * @param style Surface style ([NeoStyle.Inset] when checked, [NeoStyle.Raised] when unchecked).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specifications.
 * @param interactionSource Interaction stream.
 */
@Composable
public fun NeoCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = NeoTheme.shapes.small,
    size: Dp = NeoTheme.size.controlSmall,
    style: NeoStyle = if (checked) NeoStyle.Inset else NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level2,
    colors: NeoColors = NeoTheme.colors,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val toggleModifier = if (onCheckedChange != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            role = Role.Checkbox,
            onClick = { onCheckedChange(!checked) }
        )
    } else {
        Modifier
    }

    val backgroundColor = if (checked) colors.primary else colors.surface
    val borderWidth = if (checked) Dp.Unspecified else NeoTheme.size.borderMedium
    val borderColor = if (checked) colors.primary else colors.border.copy(alpha = 0.5f)

    Box(
        modifier = modifier
            .size(size)
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = backgroundColor,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = NeoTheme.lighting.lightSource,
                borderWidth = borderWidth,
                borderColor = borderColor
            )
            .then(toggleModifier),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = checked,
            enter = fadeIn(androidx.compose.animation.core.tween(durationMillis = 200)),
            exit = fadeOut(androidx.compose.animation.core.tween(durationMillis = 200))
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Checked",
                tint = colors.onPrimary,
                modifier = Modifier.size(NeoTheme.size.iconSmall)
            )
        }
    }
}
