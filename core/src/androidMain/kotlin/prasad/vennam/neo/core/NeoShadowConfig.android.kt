package prasad.vennam.neo.core

import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.toArgb

public actual fun configureShadowPaint(paint: Paint, color: Color, blurRadiusPx: Float) {
    val nativePaint: android.graphics.Paint = paint.asFrameworkPaint()
    nativePaint.reset()
    nativePaint.color = color.toArgb()
    if (blurRadiusPx > 0f) {
        nativePaint.maskFilter = BlurMaskFilter(blurRadiusPx, BlurMaskFilter.Blur.NORMAL)
    }
}
