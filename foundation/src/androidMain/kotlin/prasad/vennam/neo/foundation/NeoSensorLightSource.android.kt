package prasad.vennam.neo.foundation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import prasad.vennam.neo.core.NeoLightSource
import kotlin.math.atan2
import kotlin.math.roundToInt
import kotlin.math.sqrt

@Composable
public actual fun rememberSensorLightSource(
    defaultAngleDegrees: Float,
    smoothingFactor: Float,
): NeoLightSource {
    val context = LocalContext.current
    var angleDegrees by remember { mutableFloatStateOf(defaultAngleDegrees) }

    DisposableEffect(context) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
        val accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        var listener: SensorEventListener? = null

        if (sensorManager != null && accelerometer != null) {
            listener =
                object : SensorEventListener {
                    private var currentAngle = defaultAngleDegrees

                    override fun onSensorChanged(event: SensorEvent) {
                        val ax = event.values[0]
                        val ay = event.values[1]

                        val magnitude = sqrt(ax * ax + ay * ay)
                        if (magnitude > 1.5f) {
                            val targetAngleRad = atan2(-ay.toDouble(), ax.toDouble())
                            var targetAngleDeg = Math.toDegrees(targetAngleRad).toFloat()
                            if (targetAngleDeg < 0) {
                                targetAngleDeg += 360f
                            }

                            var diff = targetAngleDeg - currentAngle
                            if (diff > 180f) {
                                diff -= 360f
                            } else if (diff < -180f) {
                                diff += 360f
                            }

                            currentAngle += diff * smoothingFactor
                            currentAngle = (currentAngle + 360f) % 360f

                            val discretized = (currentAngle / 15f).roundToInt() * 15
                            angleDegrees = (discretized % 360).toFloat()
                        }
                    }

                    override fun onAccuracyChanged(
                        sensor: Sensor,
                        accuracy: Int,
                    ) {}
                }

            sensorManager.registerListener(
                listener,
                accelerometer,
                SensorManager.SENSOR_DELAY_UI,
            )
        }

        onDispose {
            if (sensorManager != null && listener != null) {
                sensorManager.unregisterListener(listener)
            }
        }
    }

    return remember(angleDegrees) {
        NeoLightSource(angleDegrees = angleDegrees)
    }
}
