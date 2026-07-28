package prasad.vennam.neo.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

/**
 * Registers an ambient light sensor listener and returns a dynamic multiplier
 * scaling shadow alphas to adapt to environmental brightness.
 */
@Composable
public expect fun rememberSensorAmbientContrast(): State<Float>
