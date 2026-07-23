package prasad.vennam.neo.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Shape tokens defining corner rounding across Neumorphic components.
 */
@Immutable
public data class NeoShapes(
    public val small: Shape = RoundedCornerShape(8.dp),
    public val medium: Shape = RoundedCornerShape(12.dp),
    public val large: Shape = RoundedCornerShape(16.dp),
    public val extraLarge: Shape = RoundedCornerShape(24.dp),
    public val pill: Shape = CircleShape,
)
