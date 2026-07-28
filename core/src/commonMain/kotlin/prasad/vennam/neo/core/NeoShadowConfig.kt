package prasad.vennam.neo.core

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint

/**
 * Platform-specific configuration for shadow paint, such as blur mask filters.
 */
public expect fun configureShadowPaint(paint: Paint, color: Color, blurRadiusPx: Float)
