package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Floating Neumorphic Tooltip speech bubble popover component.
 *
 * @param text Tooltip hint message string.
 * @param modifier Custom modifier.
 * @param onDismiss Optional callback when tooltip close icon is clicked.
 * @param shape Tooltip shape.
 * @param style Surface visual style.
 * @param elevation Shadow displacement depth.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specifications.
 */
@Composable
public fun NeoTooltip(
    text: String,
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)? = null,
    shape: Shape = NeoTheme.shapes.small,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
) {
    NeoCard(
        modifier = modifier,
        shape = shape,
        style = style,
        elevation = elevation,
        colors = colors,
        lightSource = lightSource,
        contentPadding =
            androidx.compose.foundation.layout.PaddingValues(
                horizontal = NeoTheme.spacing.medium,
                vertical = NeoTheme.spacing.small,
            ),
        animationSpec = animationSpec,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = text,
                style = NeoTheme.typography.caption,
                color = colors.textPrimary,
            )

            if (onDismiss != null) {
                Spacer(Modifier.width(NeoTheme.spacing.small))
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Tooltip",
                    tint = colors.textSecondary,
                    modifier =
                        Modifier
                            .padding(start = NeoTheme.spacing.extraSmall)
                            .clickable { onDismiss() },
                )
            }
        }
    }
}

@Preview(name = "NeoTooltip Preview")
@Composable
private fun NeoTooltipPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoTooltip(
                text = "Tap button to toggle Neumorphic elevation depth",
                onDismiss = {},
            )
        }
    }
}
