package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
 * Neumorphic Navigation Drawer content layout component.
 *
 * @param modifier Custom modifier.
 * @param header Optional drawer header composable.
 * @param footer Optional drawer footer composable.
 * @param shape Drawer container shape (defaults to [NeoTheme.shapes.large]).
 * @param style Base Neumorphic style (defaults to [NeoStyle.Raised]).
 * @param elevation Base shadow displacement elevation.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param content Drawer item composables (`ColumnScope.() -> Unit`).
 */
@Composable
public fun NeoNavigationDrawer(
    modifier: Modifier = Modifier,
    header: (@Composable ColumnScope.() -> Unit)? = null,
    footer: (@Composable ColumnScope.() -> Unit)? = null,
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
                .width(280.dp)
                .neoStyle(
                    style = style,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = elevation,
                    lightSource = lightSource,
                )
                .padding(NeoTheme.spacing.medium),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier.weight(1f, fill = false),
                verticalArrangement = Arrangement.Top,
            ) {
                if (header != null) {
                    header()
                    Spacer(modifier = Modifier.height(NeoTheme.spacing.medium))
                }
                content()
            }
            if (footer != null) {
                footer()
            }
        }
    }
}

/**
 * Navigation destination item inside a [NeoNavigationDrawer].
 *
 * @param selected Whether this item is selected.
 * @param onClick Callback executed when clicked.
 * @param label Text label composable for destination.
 * @param modifier Custom modifier.
 * @param enabled Whether item is interactive.
 * @param icon Optional leading icon composable.
 * @param badge Optional trailing badge composable.
 * @param shape Item shape (defaults to [NeoTheme.shapes.medium]).
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param interactionSource Interaction stream.
 */
@Composable
public fun ColumnScope.NeoDrawerItem(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    badge: (@Composable () -> Unit)? = null,
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
                .fillMaxWidth()
                .padding(vertical = NeoTheme.spacing.extraSmall)
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
                .padding(horizontal = NeoTheme.spacing.medium, vertical = NeoTheme.spacing.small + NeoTheme.spacing.extraSmall),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f, fill = false),
            ) {
                if (icon != null) {
                    icon()
                    Spacer(modifier = Modifier.width(NeoTheme.spacing.medium))
                }
                label()
            }
            if (badge != null) {
                badge()
            }
        }
    }
}
