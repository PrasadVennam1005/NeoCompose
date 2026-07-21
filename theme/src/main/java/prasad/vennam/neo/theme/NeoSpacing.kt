package prasad.vennam.neo.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Standard spacing tokens for padding, margins, and gaps.
 */
@Immutable
public data class NeoSpacing(
    public val extraSmall: Dp = 4.dp,
    public val small: Dp = 8.dp,
    public val medium: Dp = 16.dp,
    public val large: Dp = 24.dp,
    public val extraLarge: Dp = 32.dp
)
