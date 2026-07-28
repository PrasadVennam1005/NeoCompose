package prasad.vennam.neo.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset
import kotlin.math.cos
import kotlin.math.sin

/**
 * Represents a directional light source in 2D space used to calculate Neumorphic highlight
 * and shadow displacement vectors.
 *
 * @property angleDegrees The angle of incoming light in degrees (0..360), measured clockwise from 3 o'clock (Right).
 * @property intensity Multiplier for light projection distance (typically 0.0f..2.0f).
 */
@Immutable
public data class NeoLightSource(
    public val angleDegrees: Float = 315f,
    public val intensity: Float = 1.0f,
) {
    /**
     * Calculates the 2D offset vector for dark shadows cast away from the light source.
     *
     * @param distance Base shadow distance in pixels.
     * @return [Offset] displacement for dark shadow drawing.
     */
    public fun calculateDarkShadowOffset(distance: Float): Offset {
        val rad = Math.toRadians(angleDegrees.toDouble())
        val effDist = distance * intensity
        val dx = (effDist * cos(rad)).toFloat()
        val dy = (effDist * sin(rad)).toFloat()
        return Offset(dx, dy)
    }

    /**
     * Calculates the 2D offset vector for light highlights cast towards the light source.
     *
     * @param distance Base shadow distance in pixels.
     * @return [Offset] displacement for light highlight shadow drawing.
     */
    public fun calculateLightShadowOffset(distance: Float): Offset {
        val darkOffset = calculateDarkShadowOffset(distance)
        return Offset(-darkOffset.x, -darkOffset.y)
    }

    public companion object {
        /**
         * Light coming from Top-Left (315 degrees). Standard default for Neumorphic UI design.
         */
        public val TopLeft: NeoLightSource = NeoLightSource(angleDegrees = 315f, intensity = 1.0f)

        /**
         * Light coming from Top-Right (45 degrees).
         */
        public val TopRight: NeoLightSource = NeoLightSource(angleDegrees = 45f, intensity = 1.0f)

        /**
         * Light coming from Bottom-Left (225 degrees).
         */
        public val BottomLeft: NeoLightSource = NeoLightSource(angleDegrees = 225f, intensity = 1.0f)

        /**
         * Light coming from Bottom-Right (135 degrees).
         */
        public val BottomRight: NeoLightSource = NeoLightSource(angleDegrees = 135f, intensity = 1.0f)

        // Naming aliases matching popular Neumorphism design specs:
        public val LEFT_TOP: NeoLightSource get() = TopLeft
        public val LEFT_BOTTOM: NeoLightSource get() = BottomLeft
        public val RIGHT_TOP: NeoLightSource get() = TopRight
        public val RIGHT_BOTTOM: NeoLightSource get() = BottomRight
    }
}
