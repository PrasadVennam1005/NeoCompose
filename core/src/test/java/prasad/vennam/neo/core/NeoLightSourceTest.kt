package prasad.vennam.neo.core

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.abs

class NeoLightSourceTest {

    @Test
    fun testTopLeftLightSource_calculatesCorrectDisplacementOffsets() {
        val lightSource = NeoLightSource.TopLeft // 315 degrees
        val distance = 10f

        val darkOffset = lightSource.calculateDarkShadowOffset(distance)
        val lightOffset = lightSource.calculateLightShadowOffset(distance)

        // At 315 deg (Top-Left): cos(315) = +0.707, sin(315) = -0.707
        // Dark shadow offset points away from light source (+x, -y)
        assertTrue("Dark shadow X should be positive", darkOffset.x > 0)
        assertTrue("Dark shadow Y should be negative", darkOffset.y < 0)

        // Light shadow offset points opposite (-x, +y)
        assertEquals(-darkOffset.x, lightOffset.x, 0.001f)
        assertEquals(-darkOffset.y, lightOffset.y, 0.001f)
    }

    @Test
    fun testIntensityMultiplier_scalesOffsetsLinearly() {
        val base = NeoLightSource(angleDegrees = 0f, intensity = 1.0f) // 0 deg = Right
        val scaled = NeoLightSource(angleDegrees = 0f, intensity = 2.0f)

        val baseDark = base.calculateDarkShadowOffset(10f)
        val scaledDark = scaled.calculateDarkShadowOffset(10f)

        assertEquals(10f, baseDark.x, 0.01f)
        assertEquals(0f, baseDark.y, 0.01f)

        assertEquals(20f, scaledDark.x, 0.01f)
        assertEquals(0f, scaledDark.y, 0.01f)
    }

    @Test
    fun testPresetLightSources_areImmutableAndStandardized() {
        assertEquals(315f, NeoLightSource.TopLeft.angleDegrees, 0.001f)
        assertEquals(45f, NeoLightSource.TopRight.angleDegrees, 0.001f)
        assertEquals(225f, NeoLightSource.BottomLeft.angleDegrees, 0.001f)
        assertEquals(135f, NeoLightSource.BottomRight.angleDegrees, 0.001f)
    }
}
