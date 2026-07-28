package prasad.vennam.neo.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import prasad.vennam.neo.core.NeoLightSource

@Composable
public actual fun rememberSensorLightSource(
    defaultAngleDegrees: Float,
    smoothingFactor: Float,
): NeoLightSource {
    return remember(defaultAngleDegrees) {
        NeoLightSource(angleDegrees = defaultAngleDegrees)
    }
}
