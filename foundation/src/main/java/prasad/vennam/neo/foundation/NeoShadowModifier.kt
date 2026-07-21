package prasad.vennam.neo.foundation

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoCanvasRenderer.drawInnerShadows
import prasad.vennam.neo.core.NeoCanvasRenderer.drawOuterShadows
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle

/**
 * Draws Neumorphic shadows (outer dual shadows or soft inner shadows) behind a composables bounds
 * using GPU-accelerated Canvas rendering.
 *
 * @param style Visual Neumorphic surface style ([NeoStyle.Raised], [NeoStyle.Pressed], [NeoStyle.Basin], etc.).
 * @param shape Component geometric shape outlining shadows.
 * @param lightColor Color of light highlight facing light source.
 * @param darkColor Color of dark shadow cast away from light source.
 * @param elevation Base shadow displacement distance.
 * @param lightSource Directional light vector configuration.
 */
public fun Modifier.neoShadow(
    style: NeoStyle = NeoStyle.Raised,
    shape: Shape,
    lightColor: Color,
    darkColor: Color,
    elevation: Dp = 6.dp,
    lightSource: NeoLightSource = NeoLightSource.TopLeft
): Modifier = this.drawBehind {
    val distancePx = elevation.toPx()
    val blurRadiusPx = distancePx * 1.5f

    val darkOffset = lightSource.calculateDarkShadowOffset(distancePx)
    val lightOffset = lightSource.calculateLightShadowOffset(distancePx)

    val outline: Outline = shape.createOutline(size, layoutDirection, this)
    val shapePath = Path().apply {
        addOutline(outline)
    }

    when (style) {
        is NeoStyle.Raised, is NeoStyle.Concave, is NeoStyle.Convex -> {
            drawOuterShadows(
                path = shapePath,
                lightColor = lightColor,
                darkColor = darkColor,
                lightOffset = lightOffset,
                darkOffset = darkOffset,
                blurRadiusPx = blurRadiusPx
            )
        }
        is NeoStyle.Pressed, is NeoStyle.Inset -> {
            drawInnerShadows(
                shapePath = shapePath,
                lightColor = lightColor,
                darkColor = darkColor,
                lightOffset = lightOffset,
                darkOffset = darkOffset,
                blurRadiusPx = blurRadiusPx,
                size = size
            )
        }
        is NeoStyle.Basin -> {
            drawOuterShadows(
                path = shapePath,
                lightColor = lightColor,
                darkColor = darkColor,
                lightOffset = lightOffset,
                darkOffset = darkOffset,
                blurRadiusPx = blurRadiusPx
            )
            drawInnerShadows(
                shapePath = shapePath,
                lightColor = lightColor,
                darkColor = darkColor,
                lightOffset = lightOffset,
                darkOffset = darkOffset,
                blurRadiusPx = blurRadiusPx * 0.75f,
                size = size
            )
        }
        is NeoStyle.Flat -> {
            if (distancePx > 0f) {
                drawOuterShadows(
                    path = shapePath,
                    lightColor = lightColor.copy(alpha = lightColor.alpha * 0.5f),
                    darkColor = darkColor.copy(alpha = darkColor.alpha * 0.5f),
                    lightOffset = lightOffset * 0.5f,
                    darkOffset = darkOffset * 0.5f,
                    blurRadiusPx = blurRadiusPx * 0.5f
                )
            }
        }
    }
}
