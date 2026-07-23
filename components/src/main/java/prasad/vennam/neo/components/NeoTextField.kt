package prasad.vennam.neo.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * High-visibility recessed Neumorphic text input field component with leading/trailing icon slots.
 *
 * @param value Input text value.
 * @param onValueChange Callback on text change.
 * @param modifier Custom modifier.
 * @param enabled Whether input is enabled.
 * @param placeholder Optional placeholder text.
 * @param leadingIcon Optional leading icon slot composable.
 * @param trailingIcon Optional trailing icon slot composable.
 * @param shape Field shape (defaults to [NeoTheme.shapes.pill]).
 * @param style Inset visual style.
 * @param elevation Inner shadow displacement.
 * @param colors Color palette tokens.
 * @param contentPadding Internal text padding values derived from design tokens.
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
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = NeoTheme.shapes.pill,
    style: NeoStyle = NeoStyle.Pressed,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    contentPadding: PaddingValues =
        PaddingValues(
            horizontal = NeoTheme.spacing.large,
            vertical = NeoTheme.spacing.medium,
        ),
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    var isFocused by remember { mutableStateOf(false) }

    val animatedBorderColor by animateColorAsState(
        targetValue = if (isFocused) colors.primary else colors.border,
        animationSpec = animationSpec.colorSpec,
        label = "NeoTextFieldFocusBorderAnimation",
    )

    val borderWidth = if (isFocused) NeoTheme.size.borderThick else NeoTheme.size.borderThin

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .neoStyle(
                    style = style,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = NeoTheme.lighting.lightSource,
                    borderWidth = if (isFocused) borderWidth else Dp(0f),
                    borderColor = animatedBorderColor,
                )
                .padding(contentPadding),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leadingIcon != null) {
                leadingIcon()
                Spacer(Modifier.width(NeoTheme.spacing.small))
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (value.isEmpty() && placeholder.isNotEmpty()) {
                    Text(
                        text = placeholder,
                        color = colors.textSecondary,
                        style = NeoTheme.typography.body,
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
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .onFocusChanged { isFocused = it.isFocused },
                )
            }

            if (trailingIcon != null) {
                Spacer(Modifier.width(NeoTheme.spacing.small))
                trailingIcon()
            }
        }
    }
}

@Preview(name = "NeoTextField Preview")
@Composable
private fun NeoTextFieldPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoTextField(
                value = "hello@gmail.com",
                onValueChange = {},
                placeholder = "Enter email...",
            )
        }
    }
}
