package prasad.vennam.neo.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
 * Interactive Neumorphic toggle switch with directional light source control.
 *
 * @param checked Whether switch is toggled ON.
 * @param onCheckedChange Callback on toggle.
 * @param modifier Custom modifier.
 * @param enabled Whether control is interactive.
 * @param shape Track shape (defaults to [CircleShape]).
 * @param style Base track style ([NeoStyle.Inset] when unchecked, [NeoStyle.Raised] when checked).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source (defaults to [NeoTheme.lighting.lightSource]).
 * @param animationSpec Custom animation specifications.
 * @param interactionSource Interaction stream.
 */
@Composable
public fun NeoSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    style: NeoStyle = if (checked) NeoStyle.Raised else NeoStyle.Inset,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val trackWidth = NeoTheme.size.controlLarge
    val trackHeight = NeoTheme.spacing.extraLarge
    val thumbSize = NeoTheme.size.thumbSizeMedium
    val internalPadding = NeoTheme.spacing.extraSmall
    val maxTravelDistance = trackWidth - thumbSize - (internalPadding * 2)

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) maxTravelDistance else Dp(0f),
        animationSpec = animationSpec.elevationSpec,
        label = "NeoSwitchThumbAnimation"
    )

    val animatedTrackBg by animateColorAsState(
        targetValue = if (checked) colors.primary else colors.surface,
        animationSpec = animationSpec.colorSpec,
        label = "NeoSwitchTrackBgAnimation"
    )

    val toggleModifier = if (onCheckedChange != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            role = Role.Switch,
            onClick = { onCheckedChange(!checked) }
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .size(width = trackWidth, height = trackHeight)
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = animatedTrackBg,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = lightSource
            )
            .then(toggleModifier)
            .padding(internalPadding),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(thumbSize)
                .neoStyle(
                    style = NeoStyle.Raised,
                    shape = CircleShape,
                    backgroundColor = colors.onPrimary,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = NeoTheme.elevation.level2,
                    lightSource = lightSource
                )
        )
    }
}

@Preview(name = "NeoSwitch Preview - ON & OFF")
@Composable
private fun NeoSwitchPreview() {
    NeoTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NeoSwitch(checked = true, onCheckedChange = {})
            Spacer(Modifier.width(8.dp))
            Text("ON", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)

            Spacer(Modifier.width(16.dp))

            NeoSwitch(checked = false, onCheckedChange = {})
            Spacer(Modifier.width(8.dp))
            Text("OFF", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)
        }
    }
}
