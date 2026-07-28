package prasad.vennam.neo.foundation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext

public actual fun Modifier.neoParallaxSensor(enabled: Boolean): Modifier =
    composed {
        if (!enabled) return@composed this

        val context = LocalContext.current
        var pitch by remember { mutableFloatStateOf(0f) }
        var roll by remember { mutableFloatStateOf(0f) }

        DisposableEffect(context) {
            val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
            val rotationSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

            if (sensorManager == null || rotationSensor == null) {
                pitch = 0f
                roll = 0f
                return@DisposableEffect onDispose {}
            }

            val listener =
                object : SensorEventListener {
                    private val rotationMatrix = FloatArray(9)
                    private val orientationValues = FloatArray(3)
                    private val smoothingFactor = 0.15f
                    private val maxTiltAngle = 10f

                    override fun onSensorChanged(event: SensorEvent?) {
                        if (event == null || event.sensor.type != Sensor.TYPE_ROTATION_VECTOR) return

                        SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
                        SensorManager.getOrientation(rotationMatrix, orientationValues)

                        val rawPitch = Math.toDegrees(orientationValues[1].toDouble()).toFloat()
                        val rawRoll = Math.toDegrees(orientationValues[2].toDouble()).toFloat()

                        val targetPitch = rawPitch.coerceIn(-maxTiltAngle, maxTiltAngle)
                        val targetRoll = rawRoll.coerceIn(-maxTiltAngle, maxTiltAngle)

                        val newPitch = pitch + smoothingFactor * (targetPitch - pitch)
                        val newRoll = roll + smoothingFactor * (targetRoll - roll)

                        if (Math.abs(newPitch - pitch) > 0.2f || Math.abs(newRoll - roll) > 0.2f) {
                            pitch = newPitch
                            roll = newRoll
                        }
                    }

                    override fun onAccuracyChanged(
                        sensor: Sensor?,
                        accuracy: Int,
                    ) {}
                }

            sensorManager.registerListener(
                listener,
                rotationSensor,
                SensorManager.SENSOR_DELAY_UI,
            )

            onDispose {
                sensorManager.unregisterListener(listener)
            }
        }

        this.graphicsLayer {
            rotationX = -pitch
            rotationY = roll
            cameraDistance = 12f * density
        }
    }
