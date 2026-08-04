package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
 * Neumorphic Navigation Rail component for side-navigation on tablet/desktop layouts.
 *
 * @param modifier Custom modifier.
 * @param header Optional top header slot (e.g. logo, FAB, or user avatar).
 * @param shape Outer container shape (defaults to [NeoTheme.shapes.large]).
 * @param style Surface Neumorphic style (defaults to [NeoStyle.Raised]).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param content Navigation rail items composable slot (`ColumnScope.() -> Unit`).
 */
@Composable
public fun NeoNavigationRail(
    modifier: Modifier = Modifier,
    header: (@Composable ColumnScope.() -> Unit)? = null,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier =
            modifier
                .fillMaxHeight()
                .width(80.dp)
                .neoStyle(
                    style = style,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = lightSource,
                )
                .padding(vertical = NeoTheme.spacing.medium),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            if (header != null) {
                header()
                Spacer(modifier = Modifier.height(NeoTheme.spacing.medium))
            }
            content()
        }
    }
}

/**
 * Individual destination item within a [NeoNavigationRail].
 *
 * @param selected Whether this item is selected.
 * @param onClick Callback executed when item is clicked.
 * @param icon Composable icon for the item.
 * @param modifier Custom modifier.
 * @param enabled Whether this item is interactive.
 * @param label Optional text label composable for the destination item.
 * @param badge Optional badge composable.
 * @param alwaysShowLabel Whether to display label when unselected.
 * @param shape Item shape (defaults to [NeoTheme.shapes.medium]).
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param interactionSource Interaction source for touch events.
 */
@Composable
public fun ColumnScope.NeoNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    shape: Shape = NeoTheme.shapes.medium,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val haptic = LocalHapticFeedback.current
    val itemStyle = if (selected) NeoStyle.Inset else NeoStyle.Flat

    Box(
        modifier =
            modifier
                .padding(vertical = NeoTheme.spacing.small)
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
                .padding(NeoTheme.spacing.medium),
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
