package prasad.vennam.neo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Circular Neumorphic avatar container component with online status indicator dot.
 *
 * @param modifier Custom modifier.
 * @param size Avatar diameter derived from design tokens.
 * @param isOnline Whether online status indicator dot is visible.
 * @param imageVector Optional vector icon asset for default avatar state.
 * @param style Surface visual style ([NeoStyle.Raised] or [NeoStyle.Basin]).
 * @param elevation Shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 */
@Composable
public fun NeoAvatar(
    modifier: Modifier = Modifier,
    size: Dp = NeoTheme.size.controlLarge,
    isOnline: Boolean = false,
    imageVector: ImageVector = Icons.Default.Person,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        // Neumorphic avatar container
        Box(
            modifier = Modifier
                .size(size)
                .neoStyle(
                    style = style,
                    shape = CircleShape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = lightSource
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "Avatar",
                tint = colors.primary,
                modifier = Modifier.size(size * 0.5f)
            )
        }

        // Online status dot
        if (isOnline) {
            val badgeSize = size * 0.28f
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 2.dp, y = 2.dp)
                    .size(badgeSize)
                    .neoStyle(
                        style = NeoStyle.Raised,
                        shape = CircleShape,
                        backgroundColor = Color(0xFF4CAF50), // Active Green
                        lightColor = colors.lightShadow,
                        darkColor = colors.darkShadow,
                        elevation = NeoTheme.elevation.level1,
                        lightSource = lightSource,
                        borderWidth = NeoTheme.size.borderThin,
                        borderColor = colors.surface
                    )
            )
        }
    }
}

@Preview(name = "NeoAvatar Preview")
@Composable
private fun NeoAvatarPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoAvatar(isOnline = true)
        }
    }
}
