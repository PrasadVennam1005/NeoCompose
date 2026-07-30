package prasad.vennam.neo.foundation

import androidx.compose.foundation.background
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
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
import prasad.vennam.neo.theme.NeoTheme

/**
 * CompositionLocal providing a dynamic shadow alpha scaling multiplier,
 * typically driven by the ambient light sensor.
 */
public val LocalShadowAlphaMultiplier: ProvidableCompositionLocal<Float> = staticCompositionLocalOf { 1.0f }

/**
 * Convenient high-level modifier applying Neumorphism shadows, shape clipping, background surface gradients, and borders.
 *
 * @param style Visual Neumorphic surface style or dynamic parametric descriptor.
 * @param shape Component shape.
 * @param backgroundColor Surface background color.
 * @param lightColor Light highlight shadow color.
 * @param darkColor Dark shadow color.
 * @param elevation Elevation distance.
 * @param lightSource Directional light source.
 * @param borderWidth Optional border stroke width.
 * @param borderColor Optional border stroke color.
 * @param specularHighlight Whether to enable specular light source reflection.
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
    borderColor: Color = Color.Transparent,
    specularHighlight: Boolean = false,
): Modifier =
    this.composed {
        val colors = NeoTheme.colors
        val isHighContrast = colors.isHighContrast

        val activeBorderWidth = when {
            isHighContrast && borderWidth == 0.dp -> 1.dp
            style is NeoStyle.Glass -> if (borderWidth == 0.dp) 1.dp else borderWidth
            else -> borderWidth
        }

        val activeBorderColor = when {
            isHighContrast && borderWidth == 0.dp -> colors.textSecondary
            style is NeoStyle.Glass && borderColor == Color.Transparent -> Color.White.copy(alpha = style.borderAlpha)
            else -> borderColor
        }

        val shadowAlphaMultiplier = LocalShadowAlphaMultiplier.current
        val scaledLightColor = lightColor.copy(alpha = (lightColor.alpha * shadowAlphaMultiplier).coerceIn(0f, 1f))
        val scaledDarkColor = darkColor.copy(alpha = (darkColor.alpha * shadowAlphaMultiplier).coerceIn(0f, 1f))

        val useGradientBackground = style is NeoStyle.Concave ||
                style is NeoStyle.Convex ||
                style is NeoStyle.Basin ||
                style is NeoStyle.Metallic ||
                style is NeoStyle.Glass ||
                style is NeoStyle.Clay ||
                (style is NeoStyle.Custom && style.customBrush != null)

        val activeBackgroundColor = when (style) {
            is NeoStyle.Clay -> style.primaryColor
            is NeoStyle.Glass -> Color.White.copy(alpha = style.alpha)
            is NeoStyle.Custom -> if (style.overlayColor != Color.Unspecified) style.overlayColor else backgroundColor
            else -> backgroundColor
        }

        this
            .neoShadow(
                style = style,
                shape = shape,
                lightColor = scaledLightColor,
                darkColor = scaledDarkColor,
                elevation = elevation,
                lightSource = lightSource,
            )
            .clip(shape)
            .then(
                if (useGradientBackground) {
                    Modifier.drawBehind {
                        val brush = NeoSurfaceGradient.createSurfaceBrush(
                            style = if (style is NeoStyle.Basin) NeoStyle.Concave else style,
                            lightSource = lightSource,
                            baseColor = activeBackgroundColor,
                            lightColor = lightColor,
                            darkColor = darkColor,
                            size = size,
                        )
                        val outline: Outline = shape.createOutline(size, layoutDirection, this)
                        val path = Path().apply { addOutline(outline) }
                        drawPath(path, brush = brush)
                    }
                } else {
                    Modifier.background(activeBackgroundColor, shape)
                },
            )
            .neoSpecular(
                lightSource = lightSource,
                enabled = specularHighlight || style is NeoStyle.Metallic,
            )
            .then(
                if (activeBorderWidth > 0.dp && activeBorderColor != Color.Transparent) {
                    Modifier.neoBorder(width = activeBorderWidth, color = activeBorderColor, shape = shape)
                } else {
                    Modifier
                },
            )
    }
