package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
 * Neumorphic Tab Bar navigation component using design system tokens.
 *
 * @param tabs List of tab titles.
 * @param selectedIndex Index of active tab.
 * @param onTabSelected Callback when a tab is selected.
 * @param modifier Custom modifier.
 * @param enabled Whether tab bar is interactive.
 * @param shape Outer container shape.
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param animationSpec Custom animation specifications.
 */
@Composable
public fun NeoTabBar(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = NeoTheme.shapes.large,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec()
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .neoStyle(
                style = NeoStyle.Raised,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = NeoTheme.lighting.lightSource
            )
            .padding(NeoTheme.spacing.small)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, title ->
                val isSelected = index == selectedIndex
                val interactionSource = remember { MutableInteractionSource() }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .neoStyle(
                            style = if (isSelected) NeoStyle.Inset else NeoStyle.Flat,
                            shape = NeoTheme.shapes.medium,
                            backgroundColor = colors.surface,
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
                            onClick = { onTabSelected(index) }
                        )
                        .padding(vertical = NeoTheme.spacing.small + NeoTheme.spacing.extraSmall),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = NeoTheme.typography.title,
                        color = if (isSelected) colors.primary else colors.textSecondary
                    )
                }
            }
        }
    }
}

@Preview(name = "NeoTabBar Preview")
@Composable
private fun NeoTabBarPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoTabBar(
                tabs = listOf("Home", "Explore", "Profile"),
                selectedIndex = 0,
                onTabSelected = {}
            )
        }
    }
}
