package prasad.vennam.neo.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Design system tokens defining standard component dimensions, heights, widths, and border stroke widths.
 *
 * @property borderThin Thin stroke width (1.dp).
 * @property borderMedium Medium stroke width (1.5.dp).
 * @property borderThick Thick stroke width (2.dp).
 * @property controlSmall Small touch control size (28.dp).
 * @property controlMedium Medium touch control size (48.dp).
 * @property controlLarge Large touch control size (56.dp).
 * @property iconSmall Small icon size (18.dp).
 * @property iconMedium Medium icon size (24.dp).
 * @property trackHeightSlim Slim track height (12.dp).
 * @property trackHeightMedium Medium track height (28.dp).
 * @property thumbSizeSmall Small thumb indicator size (12.dp).
 * @property thumbSizeMedium Medium thumb size (24.dp).
 * @property thumbSizeLarge Large thumb size (26.dp).
 */
@Immutable
public data class NeoSize(
    public val borderThin: Dp = 1.dp,
    public val borderMedium: Dp = 1.5.dp,
    public val borderThick: Dp = 2.dp,
    public val controlSmall: Dp = 28.dp,
    public val controlMedium: Dp = 48.dp,
    public val controlLarge: Dp = 56.dp,
    public val iconSmall: Dp = 18.dp,
    public val iconMedium: Dp = 24.dp,
    public val trackHeightSlim: Dp = 12.dp,
    public val trackHeightMedium: Dp = 28.dp,
    public val thumbSizeSmall: Dp = 12.dp,
    public val thumbSizeMedium: Dp = 24.dp,
    public val thumbSizeLarge: Dp = 26.dp
)
