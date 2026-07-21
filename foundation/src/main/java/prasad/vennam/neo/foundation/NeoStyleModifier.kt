package prasad.vennam.neo.foundation

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.core.NeoSurfaceGradient

/**
 * Convenient high-level modifier applying Neumorphism shadows, shape clipping, background surface gradients, and borders.
 *
 * @param style Visual Neumorphic surface style.
 * @param shape Component shape.
 * @param backgroundColor Surface background color.
 * @param lightColor Light highlight shadow color.
 * @param darkColor Dark shadow color.
 * @param elevation Elevation distance.
 * @param lightSource Directional light source.
 * @param borderWidth Optional border stroke width.
 * @param borderColor Optional border stroke color.
 */
public fun Modifier.neoStyle(
    style: NeoStyle = NeoStyle.Raised,
    shape: Shape,
    backgroundColor: Color,
    lightColor: Color,
    darkColor: Color,
    elevation: Dp = 6.dp,
    lightSource: NeoLightSource = NeoLightSource.TopLeft,
    borderWidth: Dp = 0.dp,
    borderColor: Color = Color.Transparent
): Modifier = this
    .neoShadow(
        style = style,
        shape = shape,
        lightColor = lightColor,
        darkColor = darkColor,
        elevation = elevation,
        lightSource = lightSource
    )
    .clip(shape)
    .then(
        if (style is NeoStyle.Concave || style is NeoStyle.Convex || style is NeoStyle.Basin) {
            Modifier.drawBehind {
                val brush = NeoSurfaceGradient.createSurfaceBrush(
                    style = if (style is NeoStyle.Basin) NeoStyle.Concave else style,
                    lightSource = lightSource,
                    baseColor = backgroundColor,
                    lightColor = lightColor,
                    darkColor = darkColor,
                    size = size
                )
                val outline: Outline = shape.createOutline(size, layoutDirection, this)
                val path = Path().apply { addOutline(outline) }
                drawPath(path, brush = brush)
            }
        } else {
            Modifier.background(backgroundColor, shape)
        }
    )
    .then(
        if (borderWidth > 0.dp && borderColor != Color.Transparent) {
            Modifier.neoBorder(width = borderWidth, color = borderColor, shape = shape)
        } else {
            Modifier
        }
    )
