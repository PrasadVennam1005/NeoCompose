package prasad.vennam.neo.foundation

import androidx.compose.ui.Modifier
import org.junit.Assert.assertNotNull
import org.junit.Test
import prasad.vennam.neo.core.NeoStyle

class NeoFoundationTest {

    @Test
    fun neoShadowModifier_returnsNonNullModifierChain() {
        val modifier = Modifier.neoShadow(
            style = NeoStyle.Raised,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.0f),
            lightColor = androidx.compose.ui.graphics.Color.White,
            darkColor = androidx.compose.ui.graphics.Color.Black
        )
        assertNotNull(modifier)
    }
}
