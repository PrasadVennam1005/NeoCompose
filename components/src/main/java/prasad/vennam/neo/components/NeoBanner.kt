package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Floating Neumorphic Notification Banner / Toast Card component matching Dribbble designs.
 *
 * @param title Banner title text string.
 * @param message Subtitle or message description.
 * @param modifier Custom modifier.
 * @param icon Leading alert icon.
 * @param actionLabel Optional action button text (e.g., "Undo" or "View").
 * @param onActionClick Optional callback when action is clicked.
 * @param shape Card shape.
 * @param style Surface visual style.
 * @param elevation Shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specifications.
 */
@Composable
public fun NeoBanner(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Info,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
    shape: Shape = NeoTheme.shapes.medium,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level4,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
) {
    NeoCard(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        style = style,
        elevation = elevation,
        colors = colors,
        lightSource = lightSource,
        animationSpec = animationSpec,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NeoIcon(
                imageVector = icon,
                contentDescription = "Banner Icon",
                style = NeoStyle.Inset,
                tint = colors.primary,
                lightSource = lightSource,
            )

            Spacer(Modifier.width(NeoTheme.spacing.medium))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = NeoTheme.typography.title,
                    color = colors.textPrimary,
                )
                Text(
                    text = message,
                    style = NeoTheme.typography.caption,
                    color = colors.textSecondary,
                )
            }

            if (actionLabel != null && onActionClick != null) {
                Spacer(Modifier.width(NeoTheme.spacing.small))
                Text(
                    text = actionLabel,
                    style = NeoTheme.typography.label,
                    color = colors.primary,
                    modifier = Modifier.clickable { onActionClick() },
                )
            }
        }
    }
}

@Preview(name = "NeoBanner Preview")
@Composable
private fun NeoBannerPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoBanner(
                title = "New Message",
                message = "You received a new design token update",
                actionLabel = "View",
                onActionClick = {},
            )
        }
    }
}
