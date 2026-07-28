package prasad.vennam.neo.foundation

import androidx.compose.runtime.Composable
import prasad.vennam.neo.core.NeoLightSource

/**
 * Creates and remembers a [NeoLightSource] whose angle is dynamically bound to the physical device's
 * orientation using the accelerometer sensor.
 *
 * @param defaultAngleDegrees Fallback angle when sensor is unavailable or device is horizontal.
 * @param smoothingFactor Factor between 0f (slowest/smoothest) and 1f (instant/jittery) for low-pass filtering.
 */
@Composable
public expect fun rememberSensorLightSource(
    defaultAngleDegrees: Float = 315f,
    smoothingFactor: Float = 0.1f,
): NeoLightSource
