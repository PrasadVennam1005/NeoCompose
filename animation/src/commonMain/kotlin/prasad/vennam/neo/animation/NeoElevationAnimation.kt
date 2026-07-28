package prasad.vennam.neo.animation

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.unit.Dp
import prasad.vennam.neo.core.NeoStyle

/**
 * Animates Neumorphic shadow elevation smoothly between target states.
 *
 * @param targetElevation Target elevation depth [Dp].
 * @param animationSpec Custom [AnimationSpec] (defaults to bouncy spring).
 * @param label Debug animation label.
 * @return [State] providing animated [Dp] elevation value.
 */
@Composable
public fun animateNeoElevationAsState(
    targetElevation: Dp,
    animationSpec: AnimationSpec<Dp> = NeoAnimationSpec().elevationSpec,
    label: String = "NeoElevationAnimation",
): State<Dp> =
    animateDpAsState(
        targetValue = targetElevation,
        animationSpec = animationSpec,
        label = label,
    )

/**
 * Derives and animates target elevation for a given [NeoStyle].
 *
 * @param style Target Neumorphic style.
 * @param defaultElevation Base elevation when raised.
 * @param pressedElevation Reduced elevation when pressed or inset.
 * @param animationSpec Custom [AnimationSpec].
 * @return [State] providing animated [Dp] elevation value.
 */
@Composable
public fun animateNeoElevationForStyleAsState(
    style: NeoStyle,
    defaultElevation: Dp,
    pressedElevation: Dp = defaultElevation * 0.3f,
    animationSpec: AnimationSpec<Dp> = NeoAnimationSpec().elevationSpec,
): State<Dp> {
    val target: Dp =
        when (style) {
            is NeoStyle.Raised, is NeoStyle.Concave, is NeoStyle.Convex -> defaultElevation
            is NeoStyle.Pressed, is NeoStyle.Inset -> pressedElevation
            is NeoStyle.Flat -> defaultElevation * 0.5f
            else -> {}
        } as Dp
    return animateNeoElevationAsState(
        targetElevation = target,
        animationSpec = animationSpec,
    )
}
