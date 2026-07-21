package prasad.vennam.neo.theme

import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
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

    @Test
    fun neoSizeAndIconTokens_haveStandardizedDimensions() {
        val size = NeoSize()
        val icons = NeoIconTokens()

        assertEquals(1.dp, size.borderThin)
        assertEquals(28.dp, size.controlSmall)
        assertEquals(48.dp, size.controlMedium)

        assertEquals(18.dp, icons.small)
        assertEquals(24.dp, icons.medium)
        assertEquals(32.dp, icons.large)
    }
}
