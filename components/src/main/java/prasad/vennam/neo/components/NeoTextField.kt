package prasad.vennam.neo.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Recessed Neumorphic text input field component with inset inner shadows.
 *
 * @param value Input text value.
 * @param onValueChange Callback on text change.
 * @param modifier Custom modifier.
 * @param enabled Whether input is enabled.
 * @param placeholder Optional placeholder text.
 * @param shape Field shape.
 * @param style Inset visual style.
 * @param elevation Inner shadow displacement.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specs.
 * @param interactionSource Interaction stream.
 */
@Composable
public fun NeoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "",
    shape: Shape = NeoTheme.shapes.medium,
    style: NeoStyle = NeoStyle.Inset,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = NeoTheme.lighting.lightSource
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (value.isEmpty() && placeholder.isNotEmpty()) {
            Text(
                text = placeholder,
                color = colors.textSecondary,
                style = NeoTheme.typography.body
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = true,
            textStyle = NeoTheme.typography.body.copy(color = colors.textPrimary),
            cursorBrush = SolidColor(colors.primary),
            interactionSource = interactionSource,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
