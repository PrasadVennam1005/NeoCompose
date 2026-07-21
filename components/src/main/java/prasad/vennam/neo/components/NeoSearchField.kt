package prasad.vennam.neo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Specialized pill-shaped Neumorphic search field with search icon & clear button.
 *
 * @param query Search query text value.
 * @param onQueryChange Callback when search text changes.
 * @param modifier Custom modifier.
 * @param enabled Whether search field is interactive.
 * @param placeholder Search placeholder prompt.
 * @param shape Component shape (defaults to [CircleShape]).
 * @param style Inset visual style (defaults to [NeoStyle.Inset]).
 * @param elevation Inner shadow displacement.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specs.
 */
@Composable
public fun NeoSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "Search...",
    shape: Shape = CircleShape,
    style: NeoStyle = NeoStyle.Inset,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec()
) {
    NeoTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        enabled = enabled,
        placeholder = placeholder,
        shape = shape,
        style = style,
        elevation = elevation,
        colors = colors,
        animationSpec = animationSpec,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = colors.textSecondary
            )
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = query.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear Search",
                    tint = colors.textSecondary,
                    modifier = Modifier.clickable { onQueryChange("") }
                )
            }
        }
    )
}

@Preview(name = "NeoSearchField Preview")
@Composable
private fun NeoSearchFieldPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoSearchField(
                query = "Neumorphism",
                onQueryChange = {}
            )
        }
    }
}
