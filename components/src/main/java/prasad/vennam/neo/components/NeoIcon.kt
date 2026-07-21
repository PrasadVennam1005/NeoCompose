package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageVector
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Standardized Neumorphic Icon component supporting optional background surface styling.
 *
 * @param imageVector Vector graphic image asset.
 * @param contentDescription Accessibility description.
 * @param modifier Custom modifier.
 * @param tint Icon tint color (defaults to [NeoTheme.colors.primary]).
 * @param size Icon dimension derived from design tokens (defaults to [NeoTheme.icons.medium]).
 * @param style Optional Neumorphic background style ([NeoStyle.Raised], [NeoStyle.Inset], etc.).
 * @param elevation Shadow displacement when style is provided.
 * @param colors Color palette tokens.
 */
@Composable
public fun NeoIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = NeoTheme.colors.primary,
    size: Dp = NeoTheme.icons.medium,
    style: NeoStyle? = null,
    elevation: Dp = NeoTheme.elevation.level2,
    colors: NeoColors = NeoTheme.colors
) {
    if (style != null) {
        val containerPadding = NeoTheme.spacing.small
        val containerSize = size + (containerPadding * 2)

        Box(
            modifier = modifier
                .size(containerSize)
                .neoStyle(
                    style = style,
                    shape = CircleShape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = NeoTheme.lighting.lightSource
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = tint,
                modifier = Modifier.size(size)
            )
        }
    } else {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint,
            modifier = modifier.size(size)
        )
    }
}

/**
 * Standardized Neumorphic Painter Icon component.
 *
 * @param painter Painter graphic asset.
 * @param contentDescription Accessibility description.
 * @param modifier Custom modifier.
 * @param tint Icon tint color.
 * @param size Icon dimension derived from design tokens.
 * @param style Optional Neumorphic background style.
 * @param elevation Shadow displacement when style is provided.
 * @param colors Color palette tokens.
 */
@Composable
public fun NeoIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = NeoTheme.colors.primary,
    size: Dp = NeoTheme.icons.medium,
    style: NeoStyle? = null,
    elevation: Dp = NeoTheme.elevation.level2,
    colors: NeoColors = NeoTheme.colors
) {
    if (style != null) {
        val containerPadding = NeoTheme.spacing.small
        val containerSize = size + (containerPadding * 2)

        Box(
            modifier = modifier
                .size(containerSize)
                .neoStyle(
                    style = style,
                    shape = CircleShape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = NeoTheme.lighting.lightSource
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painter,
                contentDescription = contentDescription,
                tint = tint,
                modifier = Modifier.size(size)
            )
        }
    } else {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = tint,
            modifier = modifier.size(size)
        )
    }
}

@Preview(name = "NeoIcon Preview")
@Composable
private fun NeoIconPreview() {
    NeoTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NeoIcon(imageVector = Icons.Default.Favorite, contentDescription = "Heart", style = NeoStyle.Raised)
            Spacer(Modifier.width(16.dp))
            NeoIcon(imageVector = Icons.Default.Star, contentDescription = "Star", style = NeoStyle.Inset)
        }
    }
}
