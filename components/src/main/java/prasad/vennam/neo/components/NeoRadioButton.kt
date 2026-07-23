package prasad.vennam.neo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * High-visibility interactive Neumorphic Radio Button component with directional light source control.
 *
 * @param selected Whether radio button is selected.
 * @param onClick Event callback when selected.
 * @param modifier Custom modifier.
 * @param enabled Whether control is interactive.
 * @param shape Component shape (defaults to [CircleShape]).
 * @param size Control outer size derived from design tokens.
 * @param style Surface style ([NeoStyle.Inset] when selected, [NeoStyle.Raised] when unselected).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source (defaults to [NeoTheme.lighting.lightSource]).
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
    size: Dp = NeoTheme.size.controlSmall,
    style: NeoStyle = if (selected) NeoStyle.Inset else NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level2,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val selectModifier =
        if (onClick != null) {
            Modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.RadioButton,
                onClick = onClick,
            )
        } else {
            Modifier
        }

    val borderWidth = if (selected) NeoTheme.size.borderThick else NeoTheme.size.borderMedium
    val borderColor = if (selected) colors.primary else colors.border.copy(alpha = 0.5f)

    Box(
        modifier =
            modifier
                .size(size)
                .neoStyle(
                    style = style,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = lightSource,
                    borderWidth = borderWidth,
                    borderColor = borderColor,
                )
                .then(selectModifier),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = selected,
            enter = fadeIn(androidx.compose.animation.core.tween(200)),
            exit = fadeOut(androidx.compose.animation.core.tween(200)),
        ) {
            Box(
                modifier =
                    Modifier
                        .size(NeoTheme.size.thumbSizeSmall)
                        .neoStyle(
                            style = NeoStyle.Raised,
                            shape = CircleShape,
                            backgroundColor = colors.primary,
                            lightColor = colors.lightShadow,
                            darkColor = colors.darkShadow,
                            elevation = NeoTheme.elevation.level2,
                            lightSource = lightSource,
                        ),
            )
        }
    }
}

@Preview(name = "NeoRadioButton Preview - Selected & Unselected")
@Composable
private fun NeoRadioButtonPreview() {
    NeoTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NeoRadioButton(selected = true, onClick = {})
            Spacer(Modifier.width(8.dp))
            Text("Selected", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)

            Spacer(Modifier.width(16.dp))

            NeoRadioButton(selected = false, onClick = {})
            Spacer(Modifier.width(8.dp))
            Text("Unselected", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)
        }
    }
}
