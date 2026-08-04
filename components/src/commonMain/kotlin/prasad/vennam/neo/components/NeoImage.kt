package prasad.vennam.neo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * State representing async image loading lifecycle.
 */
public sealed interface NeoAsyncImageState {
    public object Loading : NeoAsyncImageState
    public object Success : NeoAsyncImageState
    public data class Error(val throwable: Throwable? = null) : NeoAsyncImageState
}

/**
 * Neumorphic Image component supporting painter assets, vector icons, shapes, specular highlights, and touch feedback.
 *
 * @param painter Image painter source.
 * @param contentDescription Text description for accessibility.
 * @param modifier Custom modifier.
 * @param shape Outer container and clip shape.
 * @param style Neumorphic surface style ([NeoStyle.Raised], [NeoStyle.Inset], [NeoStyle.Glass], etc.).
 * @param elevation Base shadow displacement distance.
 * @param colors Theme color tokens.
 * @param lightSource Directional light source.
 * @param alignment Image alignment within container bounds.
 * @param contentScale Image scaling algorithm.
 * @param alpha Opacity alpha applied to image.
 * @param colorFilter Optional color filter.
 * @param specularHighlight Whether glossy AGSL shader highlight is enabled.
 * @param onClick Optional click callback.
 */
@Composable
public fun NeoImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    specularHighlight: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    val haptic = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }

    val clickModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            role = Role.Button,
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            },
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = lightSource,
                specularHighlight = specularHighlight,
            )
            .then(clickModifier)
            .padding(NeoTheme.spacing.extraSmall)
            .clip(shape),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
        )
    }
}

/**
 * Overload for [NeoImage] accepting an [ImageVector].
 */
@Composable
public fun NeoImage(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    specularHighlight: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    val haptic = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }

    val clickModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            role = Role.Button,
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            },
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = lightSource,
                specularHighlight = specularHighlight,
            )
            .then(clickModifier)
            .padding(NeoTheme.spacing.small)
            .clip(shape),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            tint = colors.primary,
        )
    }
}

/**
 * Neumorphic Async Image container wrapping image fetching lifecycle with loading spinner and error fallbacks.
 *
 * @param painter Image painter if available.
 * @param contentDescription Text description.
 * @param loadingState Current loading state ([NeoAsyncImageState.Loading], [NeoAsyncImageState.Success], [NeoAsyncImageState.Error]).
 * @param modifier Custom modifier.
 * @param shape Frame shape.
 * @param style Neumorphic surface style.
 * @param elevation Base elevation.
 * @param colors Theme color tokens.
 * @param lightSource Directional light source.
 * @param contentScale Image scaling algorithm.
 * @param loadingContent Custom loading placeholder.
 * @param errorContent Custom error fallback content.
 * @param onClick Optional click callback.
 */
@Composable
public fun NeoAsyncImage(
    painter: Painter?,
    contentDescription: String?,
    loadingState: NeoAsyncImageState,
    modifier: Modifier = Modifier,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    contentScale: ContentScale = ContentScale.Crop,
    loadingContent: (@Composable () -> Unit)? = null,
    errorContent: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val haptic = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }

    val clickModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            role = Role.Button,
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            },
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = lightSource,
            )
            .then(clickModifier)
            .padding(NeoTheme.spacing.extraSmall)
            .clip(shape),
        contentAlignment = Alignment.Center,
    ) {
        when (loadingState) {
            is NeoAsyncImageState.Loading -> {
                if (loadingContent != null) {
                    loadingContent()
                } else {
                    NeoCircularProgressIndicator(
                        progress = 0.5f,
                        modifier = Modifier.size(36.dp),
                    )
                }
            }
            is NeoAsyncImageState.Error -> {
                if (errorContent != null) {
                    errorContent()
                } else {
                    Icon(
                        imageVector = Icons.Default.BrokenImage,
                        contentDescription = "Error loading image",
                        tint = colors.textSecondary,
                        modifier = Modifier.size(36.dp),
                    )
                }
            }
            is NeoAsyncImageState.Success -> {
                if (painter != null) {
                    Image(
                        painter = painter,
                        contentDescription = contentDescription,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = contentScale,
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = contentDescription,
                        tint = colors.primary,
                        modifier = Modifier.fillMaxSize(0.6f),
                    )
                }
            }
        }
    }
}
