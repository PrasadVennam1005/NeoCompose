package prasad.vennam.neo.theme

import androidx.compose.runtime.Immutable
import prasad.vennam.neo.core.NeoLightSource

/**
 * Global lighting configuration for Neumorphic shadow rendering across the design system.
 *
 * @property lightSource The directional light vector (angle and intensity).
 * @property intensity Overall shadow blur/distance scale factor (0.5f..2.0f).
 * @property contrast Shadow color opacity multiplier (0.5f..2.0f).
 */
@Immutable
public data class NeoLighting(
    public val lightSource: NeoLightSource = NeoLightSource.TopLeft,
    public val intensity: Float = 1.0f,
    public val contrast: Float = 1.0f
)
