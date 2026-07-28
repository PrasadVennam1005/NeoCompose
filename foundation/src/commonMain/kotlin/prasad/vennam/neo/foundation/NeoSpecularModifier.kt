package prasad.vennam.neo.foundation

import androidx.compose.ui.Modifier
import prasad.vennam.neo.core.NeoLightSource

/**
 * Applies a glossy, hardware-accelerated 3D Specular Highlight to a Neumorphic surface.
 *
 * @param lightSource Directional light source configuration defining the light vector direction.
 * @param intensity Brightness of the specular highlight (0f to 1f).
 * @param shininess Roughness/focus index of the highlight (higher values mean sharper shine).
 * @param enabled Whether the specular shine modifier is active.
 */
public expect fun Modifier.neoSpecular(
    lightSource: NeoLightSource,
    intensity: Float = 0.2f,
    shininess: Float = 12f,
    enabled: Boolean = true,
): Modifier
