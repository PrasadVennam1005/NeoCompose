package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Neumorphic Segmented Control container using pill shapes matching Dribbble designs.
 *
 * @param items List of tab label options.
 * @param selectedIndex Index of currently selected tab option.
 * @param onOptionSelected Callback when an option is selected.
 * @param modifier Custom modifier.
 * @param enabled Whether control is interactive.
 * @param shape Outer container shape (defaults to [CircleShape]).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specifications.
 */
@Composable
public fun NeoSegmentedControl(
    items: List<String>,
    selectedIndex: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CircleShape,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec()
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .neoStyle(
                style = NeoStyle.Inset,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = NeoTheme.lighting.lightSource
            )
            .padding(NeoTheme.spacing.extraSmall)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, title ->
                val isSelected = index == selectedIndex
                val interactionSource = remember { MutableInteractionSource() }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .neoStyle(
                            style = if (isSelected) NeoStyle.Raised else NeoStyle.Flat,
                            shape = CircleShape,
                            backgroundColor = if (isSelected) colors.surface else colors.surface.copy(alpha = 0f),
                            lightColor = colors.lightShadow,
                            darkColor = colors.darkShadow,
                            elevation = if (isSelected) NeoTheme.elevation.level2 else Dp(0f),
                            lightSource = NeoTheme.lighting.lightSource
                        )
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            enabled = enabled,
                            role = Role.Tab,
                            onClick = { onOptionSelected(index) }
                        )
                        .padding(vertical = NeoTheme.spacing.medium),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = NeoTheme.typography.label,
                        color = if (isSelected) colors.primary else colors.textSecondary
                    )
                }
            }
        }
    }
}

@Preview(name = "NeoSegmentedControl Preview")
@Composable
private fun NeoSegmentedControlPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoSegmentedControl(
                items = listOf("Day", "Week", "Month"),
                selectedIndex = 0,
                onOptionSelected = {}
            )
        }
    }
}
