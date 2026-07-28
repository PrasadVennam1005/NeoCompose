package prasad.vennam.neo.core

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.cos
import kotlin.math.sin

/**
 * Calculates directional surface lighting gradients for Neumorphic Concave and Convex surfaces.
 */
public object NeoSurfaceGradient {
    /**
     * Creates a surface lighting brush aligned with the incoming light angle.
     *
     * @param style Surface style ([NeoStyle.Concave] or [NeoStyle.Convex]).
     * @param lightSource Light vector configuration.
     * @param baseColor Base surface background color.
     * @param lightColor Light highlight shadow color.
     * @param darkColor Dark shadow color.
     * @param size Component size bounds.
     */
    public fun createSurfaceBrush(
        style: NeoStyle,
        lightSource: NeoLightSource,
        baseColor: Color,
        lightColor: Color,
        darkColor: Color,
        size: Size,
    ): Brush {
        val angleRad = Math.toRadians(lightSource.angleDegrees.toDouble())
        val startOffset =
            Offset(
                x = (size.width / 2f) - (cos(angleRad) * size.width / 2f).toFloat(),
                y = (size.height / 2f) - (sin(angleRad) * size.height / 2f).toFloat(),
            )
        val endOffset =
            Offset(
                x = (size.width / 2f) + (cos(angleRad) * size.width / 2f).toFloat(),
                y = (size.height / 2f) + (sin(angleRad) * size.height / 2f).toFloat(),
            )

        // Subtle highlight and shadow tints for realistic 3D surface curvature
        val lightTint = Color.White.copy(alpha = 0.35f)
        val darkTint = Color.Black.copy(alpha = 0.12f)

        val colors =
            when (style) {
                is NeoStyle.Concave -> listOf(darkTint, baseColor, lightTint)
                is NeoStyle.Convex -> listOf(lightTint, baseColor, darkTint)
                else -> listOf(baseColor, baseColor)
            }

        return Brush.linearGradient(
            colors = colors,
            start = startOffset,
            end = endOffset,
        )
    }
}
