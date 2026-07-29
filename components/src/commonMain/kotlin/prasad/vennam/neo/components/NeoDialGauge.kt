package prasad.vennam.neo.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Concentric Neumorphic Gauge Dial showing a metrics gauge.
 *
 * @param value Current value fraction (0.0f..1.0f).
 * @param modifier Custom modifier.
 * @param startAngle The starting angle of the gauge arc (degrees, 0 is right, 90 is bottom).
 * @param sweepAngle The total sweep angle of the gauge arc (degrees, clockwise).
 * @param outerStyle Style of the outer gauge track container.
 * @param innerStyle Style of the inner dial center.
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Spec for animated rendering transitions.
 * @param content Optional center content to show (e.g. text/numeric value).
 */
@Composable
public fun NeoDialGauge(
    value: Float,
    modifier: Modifier = Modifier,
    startAngle: Float = 135f,
    sweepAngle: Float = 270f,
    outerStyle: NeoStyle = NeoStyle.Inset,
    innerStyle: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    content: @Composable (() -> Unit)? = null,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = value.coerceIn(0f, 1f),
        animationSpec = animationSpec.lightSourceSpec,
        label = "NeoDialGaugeAnimation"
    )

    val controlSize = NeoTheme.size.controlLarge * 2.5f
    val paddingSpace = NeoTheme.spacing.medium

    BoxWithConstraints(
        modifier = modifier
            .size(controlSize)
            .neoStyle(
                style = outerStyle,
                shape = CircleShape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = lightSource,
            ),
        contentAlignment = Alignment.Center
    ) {
        val sizePx = constraints.maxWidth.toFloat()
        val radiusPx = sizePx / 2f
        
        // Arc & Tick Gauge Drawing
        Canvas(modifier = Modifier.fillMaxSize()) {
            val trackStroke = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round)
            val tickStrokeWidth = 2.dp.toPx()
            
            // Adjust dimensions for padding
            val arcSize = sizePx - paddingSpace.toPx() * 2f
            val arcOffset = paddingSpace.toPx()
            
            // Track base arc
            drawArc(
                color = colors.primary.copy(alpha = 0.2f),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(arcOffset, arcOffset),
                size = Size(arcSize, arcSize),
                style = trackStroke
            )
            
            // Active progress arc
            drawArc(
                color = colors.primary,
                startAngle = startAngle,
                sweepAngle = animatedProgress * sweepAngle,
                useCenter = false,
                topLeft = Offset(arcOffset, arcOffset),
                size = Size(arcSize, arcSize),
                style = trackStroke
            )
            
            // Ticks Drawing
            val tickCount = 11 // 10 segments
            val angleStep = sweepAngle / (tickCount - 1)
            val tickLength = 8.dp.toPx()
            val tickStartRadius = radiusPx - paddingSpace.toPx() - 10.dp.toPx()
            
            for (i in 0 until tickCount) {
                val tickAngleDeg = startAngle + i * angleStep
                val angleRad = tickAngleDeg * (PI / 180f)
                
                val tickStart = Offset(
                    x = radiusPx + tickStartRadius * cos(angleRad).toFloat(),
                    y = radiusPx + tickStartRadius * sin(angleRad).toFloat()
                )
                
                val tickEnd = Offset(
                    x = radiusPx + (tickStartRadius - tickLength) * cos(angleRad).toFloat(),
                    y = radiusPx + (tickStartRadius - tickLength) * sin(angleRad).toFloat()
                )
                
                // Highlight active ticks
                val isActive = (i.toFloat() / (tickCount - 1)) <= animatedProgress
                val tickColor = if (isActive) colors.primary else colors.darkShadow.copy(alpha = 0.5f)
                
                drawLine(
                    color = tickColor,
                    start = tickStart,
                    end = tickEnd,
                    strokeWidth = tickStrokeWidth
                )
            }
            
            // Draw Indicator Needle
            val needleLength = radiusPx - paddingSpace.toPx() - 25.dp.toPx()
            val needleAngleDeg = startAngle + animatedProgress * sweepAngle
            val needleAngleRad = needleAngleDeg * (PI / 180f)
            val needleEnd = Offset(
                x = radiusPx + needleLength * cos(needleAngleRad).toFloat(),
                y = radiusPx + needleLength * sin(needleAngleRad).toFloat()
            )
            
            drawLine(
                color = colors.primary,
                start = Offset(radiusPx, radiusPx),
                end = needleEnd,
                strokeWidth = 3.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
        
        // Inner Concentric Dial Center
        val innerDialSize = controlSize * 0.55f
        Box(
            modifier = Modifier
                .size(innerDialSize)
                .neoStyle(
                    style = innerStyle,
                    shape = CircleShape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation * 0.75f,
                    lightSource = lightSource,
                ),
            contentAlignment = Alignment.Center
        ) {
            if (content != null) {
                content()
            }
        }
    }
}
