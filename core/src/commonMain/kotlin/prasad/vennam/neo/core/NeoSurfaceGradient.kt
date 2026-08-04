package prasad.vennam.neo.core

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.cos
import kotlin.math.sin

/**
 * Calculates directional surface lighting gradients for Neumorphic, Glass, Metallic, and Clay surfaces.
 */
public object NeoSurfaceGradient {
    /**
     * Creates a surface lighting brush aligned with the incoming light angle.
     *
     * @param style Surface style ([NeoStyle]).
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
        val angleRad = lightSource.angleDegrees.toDouble() * (kotlin.math.PI / 180.0)
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

        val lightTint = Color.White.copy(alpha = 0.35f)
        val darkTint = Color.Black.copy(alpha = 0.12f)

        return when (style) {
            is NeoStyle.Concave ->
                Brush.linearGradient(
                    colors = listOf(darkTint, baseColor, lightTint),
                    start = startOffset,
                    end = endOffset,
                )
            is NeoStyle.Convex ->
                Brush.linearGradient(
                    colors = listOf(lightTint, baseColor, darkTint),
                    start = startOffset,
                    end = endOffset,
                )
            is NeoStyle.Metallic -> {
                val metallicColors = when (style.finish) {
                    NeoStyle.Metallic.Finish.Brushed -> listOf(
                        Color(0xFFE0E0E0), Color(0xFFF5F5F5), Color(0xFFBDBDBD), Color(0xFFEEEEEE)
                    )
                    NeoStyle.Metallic.Finish.Polished -> listOf(
                        Color(0xFFD4AF37), Color(0xFFFFF8DC), Color(0xFFAA7C11), Color(0xFFFFD700)
                    )
                    NeoStyle.Metallic.Finish.Chrome -> listOf(
                        Color(0xFFFFFFFF), Color(0xFF888888), Color(0xFFEEEEEE), Color(0xFF444444)
                    )
                    NeoStyle.Metallic.Finish.Carbon -> listOf(
                        Color(0xFF2B2B2B), Color(0xFF1F1F1F), Color(0xFF383838), Color(0xFF141414)
                    )
                }
                Brush.linearGradient(colors = metallicColors, start = startOffset, end = endOffset)
            }
            is NeoStyle.Clay -> {
                val clayTop = style.primaryColor.copy(alpha = 0.95f)
                val clayBottom = style.primaryColor.copy(alpha = 0.85f)
                Brush.linearGradient(colors = listOf(clayTop, clayBottom), start = startOffset, end = endOffset)
            }
            is NeoStyle.Glass -> {
                val glassTop = Color.White.copy(alpha = style.alpha)
                val glassBottom = Color.White.copy(alpha = (style.alpha * 0.4f).coerceIn(0f, 1f))
                Brush.linearGradient(colors = listOf(glassTop, glassBottom), start = startOffset, end = endOffset)
            }
            is NeoStyle.Custom -> {
                style.customBrush ?: Brush.linearGradient(
                    colors = listOf(baseColor, baseColor),
                    start = startOffset,
                    end = endOffset,
                )
            }
            else -> Brush.linearGradient(
                colors = listOf(baseColor, baseColor),
                start = startOffset,
                end = endOffset,
            )
        }
    }
}
