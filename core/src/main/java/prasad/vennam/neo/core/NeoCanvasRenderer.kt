package prasad.vennam.neo.core

import android.graphics.BlurMaskFilter
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb

/**
 * Low-level Canvas rendering engine for Neumorphic outer dual shadows, soft inner shadows,
 * hardware-accelerated blur mask filters, and surface lighting gradients.
 */
public object NeoCanvasRenderer {
    private val shadowPaint: Paint =
        Paint().apply {
            style = PaintingStyle.Fill
        }

    /**
     * Renders dual outer drop shadows (light highlight + dark shadow) around a [Path].
     */
    public fun DrawScope.drawOuterShadows(
        path: Path,
        lightColor: Color,
        darkColor: Color,
        lightOffset: Offset,
        darkOffset: Offset,
        blurRadiusPx: Float,
    ) {
        if (blurRadiusPx <= 0f) return

        drawIntoCanvas { canvas ->
            // Draw Light Outer Shadow
            drawShadowPath(canvas, path, lightColor, lightOffset, blurRadiusPx)
            // Draw Dark Outer Shadow
            drawShadowPath(canvas, path, darkColor, darkOffset, blurRadiusPx)
        }
    }

    /**
     * Renders dual inner shadows (light highlight + dark shadow) clipped within a [Path].
     */
    public fun DrawScope.drawInnerShadows(
        shapePath: Path,
        lightColor: Color,
        darkColor: Color,
        lightOffset: Offset,
        darkOffset: Offset,
        blurRadiusPx: Float,
        size: Size,
    ) {
        if (blurRadiusPx <= 0f) return

        drawIntoCanvas { canvas ->
            canvas.save()
            canvas.clipPath(shapePath)

            // Inner Dark Shadow
            drawInnerShadowPath(canvas, shapePath, darkColor, darkOffset, blurRadiusPx, size)

            // Inner Light Shadow
            drawInnerShadowPath(canvas, shapePath, lightColor, lightOffset, blurRadiusPx, size)

            canvas.restore()
        }
    }

    private fun drawShadowPath(
        canvas: Canvas,
        path: Path,
        color: Color,
        offset: Offset,
        blurRadiusPx: Float,
    ) {
        val nativePaint: android.graphics.Paint = shadowPaint.asFrameworkPaint()
        nativePaint.reset()
        nativePaint.color = color.toArgb()

        applyBlurToPaint(nativePaint, blurRadiusPx)

        canvas.save()
        canvas.translate(offset.x, offset.y)
        canvas.drawPath(path, shadowPaint)
        canvas.restore()
    }

    private fun drawInnerShadowPath(
        canvas: Canvas,
        shapePath: Path,
        color: Color,
        offset: Offset,
        blurRadiusPx: Float,
        size: Size,
    ) {
        val outerBoundsPath =
            Path().apply {
                addRect(
                    androidx.compose.ui.geometry.Rect(
                        -blurRadiusPx * 2,
                        -blurRadiusPx * 2,
                        size.width + blurRadiusPx * 2,
                        size.height + blurRadiusPx * 2,
                    ),
                )
            }

        val maskPath =
            Path().apply {
                op(outerBoundsPath, shapePath, PathOperation.Difference)
            }

        val nativePaint: android.graphics.Paint = shadowPaint.asFrameworkPaint()
        nativePaint.reset()
        nativePaint.color = color.toArgb()

        applyBlurToPaint(nativePaint, blurRadiusPx)

        canvas.save()
        canvas.translate(offset.x, offset.y)
        canvas.drawPath(maskPath, shadowPaint)
        canvas.restore()
    }

    private fun applyBlurToPaint(
        nativePaint: NativePaint,
        blurRadiusPx: Float,
    ) {
        if (blurRadiusPx > 0f) {
            nativePaint.maskFilter = BlurMaskFilter(blurRadiusPx, BlurMaskFilter.Blur.NORMAL)
        }
    }
}
