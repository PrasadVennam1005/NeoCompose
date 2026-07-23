package prasad.vennam.neo.animation

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * Defines animation specs for Neumorphic visual state transitions.
 *
 * @property elevationSpec Animation spec used when animating shadow elevation distance.
 * @property lightSourceSpec Animation spec used when rotating light source angle.
 * @property colorSpec Animation spec used when animating surface color transitions.
 */
@Immutable
public data class NeoAnimationSpec(
    public val elevationSpec: AnimationSpec<Dp> =
        spring(
            stiffness = Spring.StiffnessMediumLow,
            dampingRatio = Spring.DampingRatioLowBouncy,
        ),
    public val lightSourceSpec: AnimationSpec<Float> = tween(durationMillis = 300),
    public val colorSpec: AnimationSpec<Color> = tween(durationMillis = 200),
)
