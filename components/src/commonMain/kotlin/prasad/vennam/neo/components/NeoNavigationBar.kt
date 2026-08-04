package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Data class representing a simple navigation destination item for data-driven [NeoNavigationBar] usage.
 */
public data class NeoNavigationItemData(
    val label: String,
    val icon: @Composable () -> Unit,
    val badge: String? = null,
    val enabled: Boolean = true,
)

/**
 * Neumorphic Navigation Bar container for primary app bottom navigation destinations.
 *
 * @param modifier Custom modifier.
 * @param shape Outer container shape (defaults to [NeoTheme.shapes.large]).
 * @param style Surface Neumorphic style (defaults to [NeoStyle.Raised]).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param windowInsets Insets for padding around the bottom navigation bar.
 * @param content Navigation bar items composable slot (`RowScope.() -> Unit`).
 */
@Composable
public fun NeoNavigationBar(
    modifier: Modifier = Modifier,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    windowInsets: WindowInsets = WindowInsets(0, 0, 0, 0),
    content: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .neoStyle(
                    style = style,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = lightSource,
                )
                .padding(NeoTheme.spacing.small),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

/**
 * Overload for [NeoNavigationBar] accepting a list of [NeoNavigationItemData].
 */
@Composable
public fun NeoNavigationBar(
    items: List<NeoNavigationItemData>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = NeoTheme.shapes.large,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
) {
    NeoNavigationBar(
        modifier = modifier,
        shape = shape,
        elevation = elevation,
        colors = colors,
        lightSource = lightSource,
    ) {
        items.forEachIndexed { index, item ->
            NeoNavigationBarItem(
                selected = index == selectedIndex,
                onClick = { onItemSelected(index) },
                icon = item.icon,
                enabled = item.enabled,
                label = { Text(item.label) },
                badge = item.badge?.let { count -> { NeoBadge(count = count) } },
                colors = colors,
                lightSource = lightSource,
            )
        }
    }
}

/**
 * Individual destination item within a [NeoNavigationBar].
 *
 * @param selected Whether this item is selected.
 * @param onClick Callback executed when item is clicked.
 * @param icon Composable icon for the item.
 * @param modifier Custom modifier.
 * @param enabled Whether this item is interactive.
 * @param label Optional text label composable for the destination item.
 * @param badge Optional badge composable (e.g. notification dot or counter).
 * @param alwaysShowLabel Whether to display label when unselected.
 * @param shape Item shape (defaults to [NeoTheme.shapes.pill]).
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param interactionSource Interaction source for touch events.
 */
@Composable
public fun RowScope.NeoNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    shape: Shape = NeoTheme.shapes.pill,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val haptic = LocalHapticFeedback.current
    val itemStyle = if (selected) NeoStyle.Inset else NeoStyle.Flat

    Box(
        modifier =
            modifier
                .weight(1f)
                .neoStyle(
                    style = itemStyle,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = if (selected) NeoTheme.elevation.level2 else 0.dp,
                    lightSource = lightSource,
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = enabled,
                    role = Role.Tab,
                    onClick = {
                        if (enabled) {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        }
                        onClick()
                    },
                )
                .padding(vertical = NeoTheme.spacing.small, horizontal = NeoTheme.spacing.extraSmall),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(contentAlignment = Alignment.TopEnd) {
                icon()
                if (badge != null) {
                    Box(modifier = Modifier.offset(x = 6.dp, y = (-4).dp)) {
                        badge()
                    }
                }
            }
            if (label != null && (selected || alwaysShowLabel)) {
                Spacer(modifier = Modifier.height(NeoTheme.spacing.extraSmall))
                label()
            }
        }
    }
}

/**
 * Backward-compatible alias for [NeoNavigationBar].
 */
@Composable
public fun NeoBottomNavigation(
    modifier: Modifier = Modifier,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    content: @Composable RowScope.() -> Unit,
) = NeoNavigationBar(
    modifier = modifier,
    shape = shape,
    style = style,
    elevation = elevation,
    colors = colors,
    lightSource = lightSource,
    content = content,
)

/**
 * Backward-compatible alias for [NeoNavigationBarItem].
 */
@Composable
public fun RowScope.NeoBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    shape: Shape = NeoTheme.shapes.pill,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = NeoNavigationBarItem(
    selected = selected,
    onClick = onClick,
    icon = icon,
    modifier = modifier,
    enabled = enabled,
    label = label,
    badge = badge,
    alwaysShowLabel = alwaysShowLabel,
    shape = shape,
    colors = colors,
    lightSource = lightSource,
    interactionSource = interactionSource,
)
