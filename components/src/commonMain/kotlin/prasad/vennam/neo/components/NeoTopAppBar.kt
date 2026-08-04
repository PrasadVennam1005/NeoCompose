package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Neumorphic Top App Bar component for screen headers with title, navigation icon, and action items.
 *
 * @param title Screen title composable.
 * @param modifier Custom modifier.
 * @param navigationIcon Optional navigation icon composable (e.g. back button or menu button).
 * @param actions Optional action icons composable slot (`RowScope.() -> Unit`).
 * @param shape Component shape (defaults to [NeoTheme.shapes.large]).
 * @param style Base Neumorphic surface style (defaults to [NeoStyle.Raised]).
 * @param elevation Base shadow displacement elevation.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param windowInsets Window insets for top status bar padding.
 */
@Composable
public fun NeoTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level2,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    windowInsets: WindowInsets = WindowInsets(0, 0, 0, 0),
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
                .padding(horizontal = NeoTheme.spacing.medium, vertical = NeoTheme.spacing.small),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f, fill = false),
            ) {
                if (navigationIcon != null) {
                    navigationIcon()
                    Spacer(modifier = Modifier.width(NeoTheme.spacing.medium))
                }
                title()
            }
            if (actions != null) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    content = actions,
                )
            }
        }
    }
}
