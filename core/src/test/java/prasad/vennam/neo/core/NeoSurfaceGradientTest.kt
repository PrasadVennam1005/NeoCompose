package prasad.vennam.neo.core

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertNotNull
import org.junit.Test

class NeoSurfaceGradientTest {

    @Test
    fun createSurfaceBrush_returnsValidLinearGradientForConcaveAndConvex() {
        val size = Size(100f, 100f)

        val concaveBrush = NeoSurfaceGradient.createSurfaceBrush(
            style = NeoStyle.Concave,
            lightSource = NeoLightSource.TopLeft,
            baseColor = Color.Gray,
            lightColor = Color.White,
            darkColor = Color.Black,
            size = size
        )
        assertNotNull(concaveBrush)

        val convexBrush = NeoSurfaceGradient.createSurfaceBrush(
            style = NeoStyle.Convex,
            lightSource = NeoLightSource.TopLeft,
            baseColor = Color.Gray,
            lightColor = Color.White,
            darkColor = Color.Black,
            size = size
        )
        assertNotNull(convexBrush)
    }
}
