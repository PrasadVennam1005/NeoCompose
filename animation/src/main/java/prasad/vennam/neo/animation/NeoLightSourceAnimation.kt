package prasad.vennam.neo.animation

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import prasad.vennam.neo.core.NeoLightSource

/**
 * Animates the light source angle degrees smoothly between target light sources.
 *
 * @param targetLightSource Destination light vector.
 * @param animationSpec Custom [AnimationSpec].
 * @return [State] providing animated [NeoLightSource].
 */
@Composable
public fun animateNeoLightSourceAsState(
    targetLightSource: NeoLightSource,
    animationSpec: AnimationSpec<Float> = NeoAnimationSpec().lightSourceSpec,
): State<NeoLightSource> {
    val animatedAngle =
        animateFloatAsState(
            targetValue = targetLightSource.angleDegrees,
            animationSpec = animationSpec,
            label = "NeoLightSourceAngleAnimation",
        )
    val animatedIntensity =
        animateFloatAsState(
            targetValue = targetLightSource.intensity,
            animationSpec = animationSpec,
            label = "NeoLightSourceIntensityAnimation",
        )

    return remember(targetLightSource) {
        derivedStateOf {
            NeoLightSource(
                angleDegrees = animatedAngle.value,
                intensity = animatedIntensity.value,
            )
        }
    }
}
