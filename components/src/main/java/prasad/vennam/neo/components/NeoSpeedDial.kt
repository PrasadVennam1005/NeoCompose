package prasad.vennam.neo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

public data class NeoSpeedDialItem(
    val id: String,
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit,
)

/**
 * Expandable Neumorphic Speed Dial FAB button with sub-action item stack.
 *
 * @param items Sub-action item list.
 * @param modifier Custom modifier.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specifications.
 */
@Composable
public fun NeoSpeedDial(
    items: List<NeoSpeedDialItem>,
    modifier: Modifier = Modifier,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium),
    ) {
        // Vertical stack of sub-action items when expanded
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 }),
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
            ) {
                items.forEach { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                    ) {
                        NeoCard(
                            style = NeoStyle.Raised,
                            elevation = NeoTheme.elevation.level2,
                            lightSource = lightSource,
                        ) {
                            Text(
                                text = item.label,
                                style = NeoTheme.typography.label,
                                color = colors.textPrimary,
                            )
                        }

                        Spacer(Modifier.width(NeoTheme.spacing.small))

                        NeoIconButton(
                            onClick = {
                                item.onClick()
                                expanded = false
                            },
                            size = NeoTheme.size.controlMedium,
                            lightSource = lightSource,
                        ) {
                            Icon(imageVector = item.icon, contentDescription = item.label, tint = colors.primary)
                        }
                    }
                }
            }
        }

        // Main FAB Toggle
        NeoFAB(
            onClick = { expanded = !expanded },
            lightSource = lightSource,
        ) {
            Icon(
                imageVector = if (expanded) Icons.Default.Close else Icons.Default.Add,
                contentDescription = "Speed Dial Toggle",
                tint = colors.primary,
            )
        }
    }
}

@Preview(name = "NeoSpeedDial Preview")
@Composable
private fun NeoSpeedDialPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoSpeedDial(
                items =
                    listOf(
                        NeoSpeedDialItem("1", "Add Document", Icons.Default.Add) {},
                        NeoSpeedDialItem("2", "Close Window", Icons.Default.Close) {},
                    ),
            )
        }
    }
}
