package prasad.vennam.neo.theme

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class NeoColorsTest {

    @Test
    fun defaultLightColors_hasValidLightFlagsAndShadows() {
        val colors = NeoColors.defaultLightColors()
        assertTrue(colors.isLight)
        assertNotNull(colors.lightShadow)
        assertNotNull(colors.darkShadow)
    }

    @Test
    fun defaultDarkColors_hasValidDarkFlagsAndShadows() {
        val colors = NeoColors.defaultDarkColors()
        assertFalse(colors.isLight)
        assertNotNull(colors.lightShadow)
        assertNotNull(colors.darkShadow)
    }
}
