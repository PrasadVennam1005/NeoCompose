package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoPreviewParams
import prasad.vennam.neo.theme.NeoPreviewParamsProvider
import prasad.vennam.neo.theme.NeoTheme

/**
 * Neumorphic Card container for grouping content sections with light source direction support.
 *
 * @param modifier Custom modifier.
 * @param shape Card shape.
 * @param style Surface visual style ([NeoStyle.Raised], [NeoStyle.Concave], [NeoStyle.Convex], [NeoStyle.Basin]).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source (defaults to [NeoTheme.lighting.lightSource]).
 * @param contentPadding Internal card content padding values derived from design tokens.
 * @param animationSpec Custom animation specifications.
 * @param content Card inner composable content.
 */
@Composable
public fun NeoCard(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    contentPadding: PaddingValues = PaddingValues(NeoTheme.spacing.medium),
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    specularHighlight: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    NeoSurface(
        modifier = modifier,
        shape = shape,
        style = style,
        elevation = elevation,
        colors = colors,
        lightSource = lightSource,
        animationSpec = animationSpec,
        contentAlignment = Alignment.TopStart,
        specularHighlight = specularHighlight
    ) {
        Box(
            modifier = Modifier.padding(contentPadding),
            content = content
        )
    }
}

@Preview(name = "NeoCard - Parameterized Previews")
@Composable
private fun NeoCardParameterizedPreview(
    @PreviewParameter(NeoPreviewParamsProvider::class) params: NeoPreviewParams
) {
    NeoTheme(colors = params.colors) {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoCard(style = params.style) {
                Text("Neumorphic Card Section", style = NeoTheme.typography.body, color = NeoTheme.colors.textPrimary)
            }
        }
    }
}

