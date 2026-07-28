package prasad.vennam.neo.foundation

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import prasad.vennam.neo.core.NeoLightSource
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

public actual fun Modifier.neoSpecular(
    lightSource: NeoLightSource,
    intensity: Float,
    shininess: Float,
    enabled: Boolean,
): Modifier =
    if (enabled) {
        this.drawWithCache {
            val angleRad = (lightSource.angleDegrees * Math.PI / 180.0)
            val startOffset =
                Offset(
                    x = (size.width / 2f) - (size.width * 0.35f) * cos(angleRad).toFloat(),
                    y = (size.height / 2f) - (size.height * 0.35f) * sin(angleRad).toFloat(),
                )

            onDrawWithContent {
                drawContent()
                drawCircle(
                    brush =
                        Brush.radialGradient(
                            colors = listOf(Color.White.copy(alpha = intensity * 0.5f), Color.Transparent),
                            center = startOffset,
                            radius = max(size.width, size.height) * 0.4f,
                        ),
                    radius = max(size.width, size.height) * 0.4f,
                    center = startOffset,
                )
            }
        }
    } else {
        this
    }
