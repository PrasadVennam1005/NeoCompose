package prasad.vennam.neo.animation

import org.junit.Assert.assertNotNull
import org.junit.Test

class NeoAnimationSpecTest {

    @Test
    fun defaultNeoAnimationSpec_hasNonNullSpecs() {
        val spec = NeoAnimationSpec()
        assertNotNull(spec.elevationSpec)
        assertNotNull(spec.lightSourceSpec)
        assertNotNull(spec.colorSpec)
    }
}
