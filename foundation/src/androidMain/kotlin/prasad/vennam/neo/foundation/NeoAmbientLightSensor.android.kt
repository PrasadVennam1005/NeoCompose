package prasad.vennam.neo.foundation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
public actual fun rememberSensorAmbientContrast(): State<Float> {
    val context = LocalContext.current
    val ambientContrast = remember { mutableFloatStateOf(1.0f) }

    DisposableEffect(context) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
        val lightSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

        if (sensorManager == null || lightSensor == null) {
            ambientContrast.floatValue = 1.0f
            return@DisposableEffect onDispose {}
        }

        val listener =
            object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    if (event == null || event.sensor.type != Sensor.TYPE_LIGHT) return
                    val lux = event.values[0]

                    val multiplier =
                        when {
                            lux < 20f -> 0.5f
                            lux < 300f -> 0.8f
                            lux < 4000f -> 1.0f
                            else -> 1.5f
                        }
                    ambientContrast.floatValue = multiplier
                }

                override fun onAccuracyChanged(
                    sensor: Sensor?,
                    accuracy: Int,
                ) {}
            }

        sensorManager.registerListener(
            listener,
            lightSensor,
            SensorManager.SENSOR_DELAY_UI,
        )

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    return ambientContrast
}
