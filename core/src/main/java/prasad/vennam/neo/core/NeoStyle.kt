package prasad.vennam.neo.core

import androidx.compose.runtime.Immutable

/**
 * Defines the Neumorphic surface visual style.
 */
@Immutable
public sealed interface NeoStyle {
    /**
     * Extruded surface raised above the background with dual outer shadows.
     */
    @Immutable
    public data object Raised : NeoStyle

    /**
     * Pressed surface indented into the background with dual inner shadows.
     */
    @Immutable
    public data object Pressed : NeoStyle

    /**
     * Recessed container with deep inner drop shadows.
     */
    @Immutable
    public data object Inset : NeoStyle

    /**
     * Curved interior surface with smooth color gradient and outer shadows.
     */
    @Immutable
    public data object Concave : NeoStyle

    /**
     * Curved exterior surface with reversed color gradient and outer shadows.
     */
    @Immutable
    public data object Convex : NeoStyle

    /**
     * Flat surface level with background with subtle border elevation contrast.
     */
    @Immutable
    public data object Flat : NeoStyle
}
