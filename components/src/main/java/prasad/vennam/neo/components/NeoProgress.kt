package prasad.vennam.neo.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Linear Neumorphic progress indicator component using design system tokens.
 *
 * @param progress Progress fraction (0.0f..1.0f).
 * @param modifier Custom modifier.
 * @param shape Track shape.
 * @param style Base inset track style.
 * @param elevation Track shadow elevation.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specs.
 */
@Composable
public fun NeoLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    style: NeoStyle = NeoStyle.Inset,
    elevation: Dp = NeoTheme.elevation.level2,
    colors: NeoColors = NeoTheme.colors,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec()
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = animationSpec.lightSourceSpec,
        label = "NeoLinearProgressAnimation"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(NeoTheme.size.trackHeightSlim)
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = NeoTheme.lighting.lightSource
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(animatedProgress)
                .neoStyle(
                    style = NeoStyle.Raised,
                    shape = shape,
                    backgroundColor = colors.primary,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation * 0.5f,
                    lightSource = NeoTheme.lighting.lightSource
                )
        )
    }
}

/**
 * Circular Neumorphic progress indicator component using design system tokens.
 *
 * @param progress Progress fraction (0.0f..1.0f).
 * @param modifier Custom modifier.
 * @param shape Container shape.
 * @param style Track style.
 * @param elevation Shadow displacement distance.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specs.
 */
@Composable
public fun NeoCircularProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    style: NeoStyle = NeoStyle.Inset,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec()
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = animationSpec.lightSourceSpec,
        label = "NeoCircularProgressAnimation"
    )

    val sizeDp = NeoTheme.size.controlLarge
    val indicatorSizeDp = NeoTheme.size.controlMedium - NeoTheme.spacing.small

    Box(
        modifier = modifier
            .size(sizeDp)
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = NeoTheme.lighting.lightSource
            ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { animatedProgress },
            color = colors.primary,
            trackColor = colors.surface,
            modifier = Modifier.size(indicatorSizeDp),
            strokeWidth = NeoTheme.spacing.extraSmall
        )
    }
}

@Preview(name = "NeoProgress Preview")
@Composable
private fun NeoProgressPreview() {
    NeoTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NeoLinearProgressIndicator(progress = 0.75f)
            NeoCircularProgressIndicator(progress = 0.75f)
        }
    }
}
