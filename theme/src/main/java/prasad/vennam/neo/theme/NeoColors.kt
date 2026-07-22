package prasad.vennam.neo.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

/**
 * Premium Dribbble-grade color palette tokens for the Neumorphism design system.
 *
 * @property background Primary surface background color.
 * @property surface Component surface container color.
 * @property lightShadow Color used for light highlights facing the light source.
 * @property darkShadow Color used for dark shadows cast away from the light source.
 * @property primary Accent/brand color.
 * @property onPrimary Text/icon color on top of primary background.
 * @property textPrimary Primary content text color.
 * @property textSecondary Muted/secondary text color.
 * @property border Subtle border stroke color.
 * @property isLight Whether this color set represents a light or dark theme.
 */
@Stable
public data class NeoColors(
    public val background: Color,
    public val surface: Color = background,
    public val lightShadow: Color,
    public val darkShadow: Color,
    public val primary: Color = Color(0xFF007AFF),
    public val onPrimary: Color = Color.White,
    public val isLight: Boolean = true,
    public val textPrimary: Color = if (isLight) Color(0xFF2D3748) else Color(0xFFE2E8F0),
    public val textSecondary: Color = if (isLight) Color(0xFF718096) else Color(0xFFA0AEC0),
    public val border: Color = Color.Transparent,
    public val isHighContrast: Boolean = false,
) {
    public companion object {
        /**
         * Standard Dribbble light mode Neumorphism color palette (#ECF0F3).
         */
        public fun defaultLightColors(
            background: Color = Color(0xFFECF0F3),
            primary: Color = Color(0xFF007AFF),
            isHighContrast: Boolean = false
        ): NeoColors = NeoColors(
            background = background,
            surface = background,
            lightShadow = if (isHighContrast) Color.White else Color.White.copy(alpha = 0.95f),
            darkShadow = if (isHighContrast) Color(0xFF718096).copy(alpha = 0.9f) else Color(0xFFA3B1C6).copy(alpha = 0.55f),
            primary = primary,
            onPrimary = Color.White,
            isLight = true,
            textPrimary = Color(0xFF2D3748),
            textSecondary = Color(0xFF718096),
            border = Color.Transparent,
            isHighContrast = isHighContrast
        )

        /**
         * Standard dark mode Neumorphism color palette (#24292E).
         */
        public fun defaultDarkColors(
            background: Color = Color(0xFF24292E),
            primary: Color = Color(0xFF38BDF8),
            isHighContrast: Boolean = false
        ): NeoColors = NeoColors(
            background = background,
            surface = background,
            lightShadow = if (isHighContrast) Color(0xFF4B5563).copy(alpha = 0.9f) else Color(0xFF3A414A).copy(alpha = 0.7f),
            darkShadow = if (isHighContrast) Color.Black else Color(0xFF121518).copy(alpha = 0.8f),
            primary = primary,
            onPrimary = Color.Black,
            isLight = false,
            textPrimary = Color(0xFFF1F5F9),
            textSecondary = Color(0xFF94A3B8),
            border = Color.Transparent,
            isHighContrast = isHighContrast
        )
    }
}
