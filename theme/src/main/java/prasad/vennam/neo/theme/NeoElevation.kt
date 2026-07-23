package prasad.vennam.neo.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Standard elevation tokens defining shadow distance and blur depth levels.
 */
@Immutable
public data class NeoElevation(
    public val level0: Dp = 0.dp,
    public val level1: Dp = 2.dp,
    public val level2: Dp = 4.dp,
    public val level3: Dp = 6.dp,
    public val level4: Dp = 8.dp,
    public val level5: Dp = 12.dp,
)
