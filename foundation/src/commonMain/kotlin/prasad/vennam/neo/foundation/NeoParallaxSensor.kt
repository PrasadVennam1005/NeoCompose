package prasad.vennam.neo.foundation

import androidx.compose.ui.Modifier

/**
 * Composed modifier that applies a subtle 3D parallax tilt effect based on the
 * device's rotation vector sensor (gyroscope/accelerometer fusion).
 */
public expect fun Modifier.neoParallaxSensor(enabled: Boolean = true): Modifier
