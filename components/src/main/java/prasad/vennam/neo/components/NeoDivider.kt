package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

public enum class NeoDividerOrientation {
    Horizontal,
    Vertical
}

/**
 * Neumorphic line separator groove component (Inset or Raised).
 *
 * @param modifier Custom modifier.
 * @param orientation Horizontal or Vertical line orientation.
 * @param thickness Line thickness dimension (defaults to [NeoTheme.size.borderMedium]).
 * @param style Visual groove style ([NeoStyle.Inset] or [NeoStyle.Raised]).
 * @param shape Line end shape (defaults to [CircleShape]).
 * @param elevation Shadow displacement depth.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 */
@Composable
public fun NeoDivider(
    modifier: Modifier = Modifier,
    orientation: NeoDividerOrientation = NeoDividerOrientation.Horizontal,
    thickness: Dp = NeoTheme.size.borderMedium,
    style: NeoStyle = NeoStyle.Inset,
    shape: Shape = CircleShape,
    elevation: Dp = NeoTheme.elevation.level1,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource
) {
    val sizeModifier = if (orientation == NeoDividerOrientation.Horizontal) {
        Modifier.fillMaxWidth().height(thickness)
    } else {
        Modifier.fillMaxHeight().width(thickness)
    }

    Box(
        modifier = modifier
            .then(sizeModifier)
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = lightSource
            )
    )
}

@Preview(name = "NeoDivider Preview")
@Composable
private fun NeoDividerPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoDivider(thickness = 3.dp)
        }
    }
}
