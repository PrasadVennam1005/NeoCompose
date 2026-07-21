package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Neumorphic Card container for grouping content sections.
 *
 * @param modifier Custom modifier.
 * @param shape Card shape.
 * @param style Surface visual style ([NeoStyle.Raised], [NeoStyle.Concave], [NeoStyle.Convex]).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
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
    contentPadding: PaddingValues = PaddingValues(NeoTheme.spacing.medium),
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    content: @Composable BoxScope.() -> Unit
) {
    NeoSurface(
        modifier = modifier,
        shape = shape,
        style = style,
        elevation = elevation,
        colors = colors,
        animationSpec = animationSpec,
        contentAlignment = Alignment.TopStart
    ) {
        Box(
            modifier = Modifier.padding(contentPadding),
            content = content
        )
    }
}
