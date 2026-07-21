package prasad.vennam.neo.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Data model defining shadow parameters for Neumorphic rendering calculations.
 *
 * @property lightColor Highlight color cast towards the light source.
 * @property darkColor Shadow color cast away from the light source.
 * @property elevation Distance/elevation offset for light and dark shadows.
 * @property blurRadius Blur radius applied to shadows.
 * @property spread Spread radius extension for shadow bounds.
 */
@Immutable
public data class NeoShadowParams(
    public val lightColor: Color = Color.White,
    public val darkColor: Color = Color.Black.copy(alpha = 0.2f),
    public val elevation: Dp = 6.dp,
    public val blurRadius: Dp = 12.dp,
    public val spread: Dp = 0.dp
)
