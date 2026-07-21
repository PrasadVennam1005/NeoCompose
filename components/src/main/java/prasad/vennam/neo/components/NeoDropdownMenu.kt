package prasad.vennam.neo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Interactive Neumorphic Dropdown Menu / Select component matching Dribbble designs.
 *
 * @param selectedOption Text label of currently selected option.
 * @param options List of available dropdown option strings.
 * @param onOptionSelected Callback when an option is selected.
 * @param modifier Custom modifier.
 * @param enabled Whether control is interactive.
 * @param placeholder Optional placeholder when no option is selected.
 * @param leadingIcon Optional leading icon slot composable.
 * @param shape Component shape (defaults to [NeoTheme.shapes.medium]).
 * @param style Base Neumorphic surface style (defaults to [NeoStyle.Inset]).
 * @param elevation Shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specs.
 */
@Composable
public fun NeoDropdownMenu(
    selectedOption: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "Select option",
    leadingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = NeoTheme.shapes.medium,
    style: NeoStyle = NeoStyle.Inset,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec()
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        // Dropdown Header Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .neoStyle(
                    style = style,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = lightSource,
                    borderWidth = if (expanded) NeoTheme.size.borderMedium else Dp(0f),
                    borderColor = if (expanded) colors.primary else colors.border
                )
                .clickable(
                    enabled = enabled,
                    role = Role.Button,
                    onClick = { expanded = !expanded }
                )
                .padding(
                    horizontal = NeoTheme.spacing.medium,
                    vertical = NeoTheme.spacing.medium
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (leadingIcon != null) {
                        leadingIcon()
                        Spacer(Modifier.width(NeoTheme.spacing.small))
                    }
                    Text(
                        text = selectedOption.ifEmpty { placeholder },
                        style = NeoTheme.typography.body,
                        color = if (selectedOption.isNotEmpty()) colors.textPrimary else colors.textSecondary
                    )
                }

                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown Toggle",
                    tint = if (expanded) colors.primary else colors.textSecondary,
                    modifier = Modifier.padding(start = NeoTheme.spacing.small)
                )
            }
        }

        // Expanded Neumorphic Menu Items Surface
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .neoStyle(
                    style = NeoStyle.Raised,
                    shape = NeoTheme.shapes.large,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = NeoTheme.elevation.level4,
                    lightSource = lightSource
                )
                .padding(NeoTheme.spacing.small)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.extraSmall)) {
                options.forEach { option ->
                    val isSelected = option == selectedOption
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .neoStyle(
                                style = if (isSelected) NeoStyle.Inset else NeoStyle.Flat,
                                shape = NeoTheme.shapes.medium,
                                backgroundColor = if (isSelected) colors.surface else colors.surface.copy(alpha = 0f),
                                lightColor = colors.lightShadow,
                                darkColor = colors.darkShadow,
                                elevation = if (isSelected) NeoTheme.elevation.level2 else Dp(0f),
                                lightSource = lightSource
                            )
                            .clickable {
                                onOptionSelected(option)
                                expanded = false
                            }
                            .padding(
                                horizontal = NeoTheme.spacing.medium,
                                vertical = NeoTheme.spacing.small + NeoTheme.spacing.extraSmall
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = option,
                            style = NeoTheme.typography.body,
                            color = if (isSelected) colors.primary else colors.textPrimary
                        )
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = colors.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "NeoDropdownMenu Preview")
@Composable
private fun NeoDropdownMenuPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoDropdownMenu(
                selectedOption = "Australia",
                options = listOf("Argentina", "Armenia", "Australia"),
                onOptionSelected = {}
            )
        }
    }
}
