package prasad.vennam.neo.core

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.toArgb
import org.jetbrains.skia.FilterBlurMode
import org.jetbrains.skia.MaskFilter

public actual fun configureShadowPaint(paint: Paint, color: Color, blurRadiusPx: Float) {
    val skiaPaint: org.jetbrains.skia.Paint = paint.asFrameworkPaint()
    skiaPaint.reset()
    skiaPaint.color = color.toArgb()
    if (blurRadiusPx > 0f) {
        skiaPaint.maskFilter = MaskFilter.makeBlur(FilterBlurMode.NORMAL, blurRadiusPx)
    }
}
