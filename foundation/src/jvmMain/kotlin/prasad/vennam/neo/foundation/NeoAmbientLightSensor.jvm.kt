package prasad.vennam.neo.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember

@Composable
public actual fun rememberSensorAmbientContrast(): State<Float> {
    return remember { mutableFloatStateOf(1.0f) }
}
