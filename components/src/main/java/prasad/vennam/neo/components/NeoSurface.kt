package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.animation.animateNeoElevationAsState
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Base Neumorphic surface container composable.
 *
 * @param modifier Custom modifier.
 * @param shape Component shape (defaults to [NeoTheme.shapes.medium]).
 * @param style Visual Neumorphic surface style ([NeoStyle.Raised], [NeoStyle.Pressed], etc.).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette (defaults to [NeoTheme.colors]).
 * @param lightSource Directional light vector (defaults to [NeoTheme.lighting.lightSource]).
 * @param animationSpec Custom animation specifications.
 * @param contentAlignment Inner box alignment.
 * @param content Composable inner layout content.
 */
@Composable
public fun NeoSurface(
    modifier: Modifier = Modifier,
    shape: Shape = NeoTheme.shapes.medium,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    val animatedElevation by animateNeoElevationAsState(
        targetElevation = elevation,
        animationSpec = animationSpec.elevationSpec
    )

    Box(
        modifier = modifier.neoStyle(
            style = style,
            shape = shape,
            backgroundColor = colors.surface,
            lightColor = colors.lightShadow,
            darkColor = colors.darkShadow,
            elevation = animatedElevation,
            lightSource = lightSource
        ),
        contentAlignment = contentAlignment
    ) {
        content()
    }
}

@Preview(name = "NeoSurface - Raised & Inset Light")
@Composable
private fun NeoSurfaceLightPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoSurface(
                modifier = Modifier.size(100.dp),
                style = NeoStyle.Raised
            ) {
                Text("Raised", style = NeoTheme.typography.label, color = NeoTheme.colors.textPrimary)
            }
        }
    }
}

@Preview(name = "NeoSurface - Dark Theme")
@Composable
private fun NeoSurfaceDarkPreview() {
    NeoTheme(colors = NeoColors.defaultDarkColors()) {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoSurface(
                modifier = Modifier.size(100.dp),
                style = NeoStyle.Concave
            ) {
                Text("Concave", style = NeoTheme.typography.label, color = NeoTheme.colors.textPrimary)
            }
        }
    }
}
