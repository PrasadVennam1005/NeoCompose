package prasad.vennam.neo.foundation

import android.util.LruCache
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoCanvasRenderer.drawInnerShadows
import prasad.vennam.neo.core.NeoCanvasRenderer.drawOuterShadows
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max

/**
 * Cache key representing all constraints defining a unique Neumorphic shadow.
 */
public data class ShadowCacheKey(
    val width: Int,
    val height: Int,
    val style: NeoStyle,
    val shape: Shape,
    val lightColor: Color,
    val darkColor: Color,
    val elevationDp: Float,
    val lightSource: NeoLightSource
)

// Thread-safe shadow cache with a limit of 50 computed bitmaps.
private val shadowCache = LruCache<ShadowCacheKey, ImageBitmap>(50)

/**
 * Draws Neumorphic shadows (outer dual shadows or soft inner shadows) behind a composable's bounds
 * using GPU-accelerated Canvas rendering and offscreen hardware-accelerated caching.
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
    val width = size.width.toInt()
    val height = size.height.toInt()
    if (width <= 0 || height <= 0) return@drawBehind

    val distancePx = elevation.toPx()
    val blurRadiusPx = distancePx * 1.5f

    // Calculate dynamic padding to prevent shadow boundary clipping
    val darkOffset = lightSource.calculateDarkShadowOffset(distancePx)
    val lightOffset = lightSource.calculateLightShadowOffset(distancePx)
    val maxOffset = max(
        max(abs(darkOffset.x), abs(darkOffset.y)),
        max(abs(lightOffset.x), abs(lightOffset.y))
    )
    val padding = ceil(blurRadiusPx * 2f + maxOffset).toInt()

    val cacheKey = ShadowCacheKey(
        width = width,
        height = height,
        style = style,
        shape = shape,
        lightColor = lightColor,
        darkColor = darkColor,
        elevationDp = elevation.value,
        lightSource = lightSource
    )

    var cachedBitmap = shadowCache.get(cacheKey)
    if (cachedBitmap == null) {
        val bitmapWidth = width + padding * 2
        val bitmapHeight = height + padding * 2
        val imageBitmap = ImageBitmap(bitmapWidth, bitmapHeight)
        val canvas = Canvas(imageBitmap)
        val drawScope = CanvasDrawScope()

        val componentSize = Size(width.toFloat(), height.toFloat())

        drawScope.draw(
            density = this,
            layoutDirection = layoutDirection,
            canvas = canvas,
            size = Size(bitmapWidth.toFloat(), bitmapHeight.toFloat())
        ) {
            translate(padding.toFloat(), padding.toFloat()) {
                val outline: Outline = shape.createOutline(componentSize, layoutDirection, this)
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
                            size = componentSize
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
                            size = componentSize
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
        }
        cachedBitmap = imageBitmap
        shadowCache.put(cacheKey, cachedBitmap)
    }

    drawImage(
        image = cachedBitmap,
        topLeft = Offset(-padding.toFloat(), -padding.toFloat())
    )
}
