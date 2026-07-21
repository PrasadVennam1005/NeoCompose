package prasad.vennam.neo.foundation

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Draws a subtle Neumorphic outline stroke along a component boundary.
 *
 * @param width Stroke thickness.
 * @param color Stroke color.
 * @param shape Component shape.
 */
public fun Modifier.neoBorder(
    width: Dp = 1.dp,
    color: Color = Color.White.copy(alpha = 0.2f),
    shape: Shape
): Modifier = this.drawBehind {
    val widthPx = width.toPx()
    if (widthPx <= 0f) return@drawBehind

    val outline: Outline = shape.createOutline(size, layoutDirection, this)
    val path = Path().apply {
        addOutline(outline)
    }

    drawPath(
        path = path,
        color = color,
        style = Stroke(width = widthPx)
    )
}
