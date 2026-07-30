package prasad.vennam.neo.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Defines the Neumorphic surface visual style archetype and extensible dynamic style descriptors.
 */
@Immutable
public sealed interface NeoStyle {
    // ------------------------------------------------------------------------
    // Core Neumorphic Archetypes
    // ------------------------------------------------------------------------

    /** Extruded surface raised above the background with dual outer shadows. */
    @Immutable
    public data object Raised : NeoStyle

    /** Pressed surface indented into the background with dual inner shadows. */
    @Immutable
    public data object Pressed : NeoStyle

    /** Recessed container with deep inner drop shadows. */
    @Immutable
    public data object Inset : NeoStyle

    /** Curved interior surface with smooth color gradient and outer shadows. */
    @Immutable
    public data object Concave : NeoStyle

    /** Curved exterior surface with reversed color gradient and outer shadows. */
    @Immutable
    public data object Convex : NeoStyle

    /** Basin curved rim surface with central inset depression. */
    @Immutable
    public data object Basin : NeoStyle

    /** Flat surface level with background with subtle border elevation contrast. */
    @Immutable
    public data object Flat : NeoStyle

    // ------------------------------------------------------------------------
    // Dynamic Extensible Style Descriptors
    // ------------------------------------------------------------------------

    /**
     * Glassmorphism surface with translucent background fill and optional frosted specular rim.
     */
    @Immutable
    public data class Glass(
        val blurRadius: Dp = 12.dp,
        val alpha: Float = 0.45f,
        val borderAlpha: Float = 0.35f,
        val frosted: Boolean = true,
    ) : NeoStyle

    /**
     * Glowing surface emitting ambient colored light halo or inner glow.
     */
    @Immutable
    public data class Glow(
        val glowColor: Color = Color(0xFF00E5FF),
        val glowRadius: Dp = 16.dp,
        val isInnerGlow: Boolean = false,
        val pulseAlpha: Float = 1.0f,
    ) : NeoStyle

    /**
     * Claymorphic soft rounded surface with rich pastel background fill and high elevation.
     */
    @Immutable
    public data class Clay(
        val primaryColor: Color = Color(0xFFFFB74D),
        val softElevation: Dp = 8.dp,
        val cornerSmoothing: Float = 0.8f,
    ) : NeoStyle

    /**
     * Metallic surface with brushed, polished, chrome, or carbon fiber finish.
     */
    @Immutable
    public data class Metallic(
        val finish: Finish = Finish.Brushed,
        val specularAngle: Float = 45f,
        val reflectivity: Float = 0.7f,
    ) : NeoStyle {
        @Immutable
        public enum class Finish { Brushed, Polished, Chrome, Carbon }
    }

    /**
     * Custom surface descriptor allowing arbitrary shadow modes, custom brushes, and overlays.
     */
    @Immutable
    public data class Custom(
        val shadowMode: ShadowMode = ShadowMode.Outer,
        val customBrush: Brush? = null,
        val overlayColor: Color = Color.Unspecified,
        val extraGlowColor: Color? = null,
        val customElevation: Dp? = null,
    ) : NeoStyle {
        @Immutable
        public enum class ShadowMode { Outer, Inner, Dual, None }
    }
}
