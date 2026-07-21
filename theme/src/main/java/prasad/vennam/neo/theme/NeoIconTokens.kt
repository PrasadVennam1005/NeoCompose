package prasad.vennam.neo.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Design system tokens defining icon dimensions and default visual styles across components.
 *
 * @property small Small icon size (18.dp).
 * @property medium Standard icon size (24.dp).
 * @property large Large display icon size (32.dp).
 * @property extraLarge Hero display icon size (48.dp).
 */
@Immutable
public data class NeoIconTokens(
    public val small: Dp = 18.dp,
    public val medium: Dp = 24.dp,
    public val large: Dp = 32.dp,
    public val extraLarge: Dp = 48.dp
)
