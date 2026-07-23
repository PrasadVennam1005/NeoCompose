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

/**
 * Composed modifier that applies a subtle 3D parallax tilt effect based on the
 * device's rotation vector sensor (gyroscope/accelerometer fusion).
 *
 * If the rotation vector sensor is unavailable, it reverts to a neutral state (no tilt).
 */
public fun Modifier.neoParallaxSensor(enabled: Boolean = true): Modifier = composed {
    if (!enabled) return@composed this

    val context = LocalContext.current
    var pitch by remember { mutableFloatStateOf(0f) } // Tilt forward/backward
    var roll by remember { mutableFloatStateOf(0f) }  // Tilt left/right

    DisposableEffect(context) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
        val rotationSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

        if (sensorManager == null || rotationSensor == null) {
            pitch = 0f
            roll = 0f
            return@DisposableEffect onDispose {}
        }

        val listener = object : SensorEventListener {
            private val rotationMatrix = FloatArray(9)
            private val orientationValues = FloatArray(3)
            private val smoothingFactor = 0.15f // Low-pass filter coefficient
            private val maxTiltAngle = 10f       // Max rotation in degrees

            override fun onSensorChanged(event: SensorEvent?) {
                if (event == null || event.sensor.type != Sensor.TYPE_ROTATION_VECTOR) return

                // Compute rotation matrix from quaternion vector
                SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
                // Compute Euler orientation values (radians)
                SensorManager.getOrientation(rotationMatrix, orientationValues)

                // Convert to degrees
                val rawPitch = Math.toDegrees(orientationValues[1].toDouble()).toFloat()
                val rawRoll = Math.toDegrees(orientationValues[2].toDouble()).toFloat()

                // Clamp to prevent extreme distortions
                val targetPitch = rawPitch.coerceIn(-maxTiltAngle, maxTiltAngle)
                val targetRoll = rawRoll.coerceIn(-maxTiltAngle, maxTiltAngle)

                // Apply low-pass smoothing filter to remove micro-jitter
                val newPitch = pitch + smoothingFactor * (targetPitch - pitch)
                val newRoll = roll + smoothingFactor * (targetRoll - roll)

                // Only update State if the change exceeds a 0.2 degree threshold (dead zone)
                // to completely suppress hand tremors and minor sensor noise.
                if (Math.abs(newPitch - pitch) > 0.2f || Math.abs(newRoll - roll) > 0.2f) {
                    pitch = newPitch
                    roll = newRoll
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(
            listener,
            rotationSensor,
            SensorManager.SENSOR_DELAY_UI
        )

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    this.graphicsLayer {
        // Rotate around X (pitch) and Y (roll) axes
        rotationX = -pitch
        rotationY = roll
        // Set camera distance (perspective depth projection)
        cameraDistance = 12f * density
    }
}
