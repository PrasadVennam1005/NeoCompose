package prasad.vennam.neo.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

/**
 * Color palette tokens for the Neumorphism design system.
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
    public val primary: Color = Color(0xFF6200EE),
    public val onPrimary: Color = Color.White,
    public val isLight: Boolean = true,
    public val textPrimary: Color = if (isLight) Color(0xFF212121) else Color(0xFFE0E0E0),
    public val textSecondary: Color = if (isLight) Color(0xFF757575) else Color(0xFF9E9E9E),
    public val border: Color = if (isLight) Color(0xFFE0E0E0) else Color(0xFF373737),
) {
    public companion object {
        /**
         * Standard light mode Neumorphism color palette (Light Grey #E0E5EC).
         */
        public fun defaultLightColors(
            background: Color = Color(0xFFE0E5EC),
            primary: Color = Color(0xFF3F51B5)
        ): NeoColors = NeoColors(
            background = background,
            surface = background,
            lightShadow = Color.White.copy(alpha = 0.9f),
            darkShadow = Color(0xFFA3B1C6).copy(alpha = 0.6f),
            primary = primary,
            onPrimary = Color.White,
            isLight = true
        )

        /**
         * Standard dark mode Neumorphism color palette (Dark Charcoal #24292E).
         */
        public fun defaultDarkColors(
            background: Color = Color(0xFF24292E),
            primary: Color = Color(0xFF7986CB)
        ): NeoColors = NeoColors(
            background = background,
            surface = background,
            lightShadow = Color(0xFF3A414A).copy(alpha = 0.7f),
            darkShadow = Color(0xFF121518).copy(alpha = 0.8f),
            primary = primary,
            onPrimary = Color.Black,
            isLight = false
        )
    }
}
