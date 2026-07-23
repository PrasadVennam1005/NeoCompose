package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * High-aesthetic Neumorphic Audio & Media Player Controller Bar.
 *
 * @param trackTitle Media title string.
 * @param artistName Media artist/subtitle string.
 * @param isPlaying Whether playback is currently playing.
 * @param onPlayPauseToggle Callback on play/pause click.
 * @param progress Current track playback progress fraction (0.0f..1.0f).
 * @param onProgressChange Callback on track scrubbing slider change.
 * @param onSkipPrevious Optional callback for skip previous track.
 * @param onSkipNext Optional callback for skip next track.
 * @param modifier Custom modifier.
 * @param shape Outer card shape.
 * @param style Surface style.
 * @param elevation Shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specifications.
 */
@Composable
public fun NeoAudioPlayerBar(
    trackTitle: String,
    artistName: String,
    isPlaying: Boolean,
    onPlayPauseToggle: () -> Unit,
    progress: Float,
    onProgressChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    onSkipPrevious: (() -> Unit)? = null,
    onSkipNext: (() -> Unit)? = null,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
) {
    NeoCard(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        style = style,
        elevation = elevation,
        colors = colors,
        lightSource = lightSource,
        animationSpec = animationSpec,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium),
        ) {
            // Track details header row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NeoAvatar(
                    size = NeoTheme.size.controlLarge,
                    imageVector = Icons.Default.MusicNote,
                    style = NeoStyle.Basin,
                    lightSource = lightSource,
                )

                Spacer(Modifier.width(NeoTheme.spacing.medium))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = trackTitle,
                        style = NeoTheme.typography.title,
                        color = colors.textPrimary,
                    )
                    Text(
                        text = artistName,
                        style = NeoTheme.typography.caption,
                        color = colors.textSecondary,
                    )
                }

                // Playback Control Buttons Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small),
                ) {
                    if (onSkipPrevious != null) {
                        NeoIconButton(
                            onClick = onSkipPrevious,
                            size = NeoTheme.size.controlSmall,
                            lightSource = lightSource,
                        ) {
                            Icon(Icons.Default.SkipPrevious, contentDescription = "Previous", tint = colors.textPrimary)
                        }
                    }

                    // Basin Play/Pause Central Button
                    NeoIconButton(
                        onClick = onPlayPauseToggle,
                        size = NeoTheme.size.controlMedium,
                        style = NeoStyle.Basin,
                        lightSource = lightSource,
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Play/Pause",
                            tint = colors.primary,
                        )
                    }

                    if (onSkipNext != null) {
                        NeoIconButton(
                            onClick = onSkipNext,
                            size = NeoTheme.size.controlSmall,
                            lightSource = lightSource,
                        ) {
                            Icon(Icons.Default.SkipNext, contentDescription = "Next", tint = colors.textPrimary)
                        }
                    }
                }
            }

            // Track Scrub Slider
            NeoSlider(
                value = progress,
                onValueChange = onProgressChange,
                lightSource = lightSource,
            )
        }
    }
}

@Preview(name = "NeoAudioPlayerBar Preview")
@Composable
private fun NeoAudioPlayerBarPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoAudioPlayerBar(
                trackTitle = "Midnight Resonance",
                artistName = "Synthesizer Wave",
                isPlaying = true,
                onPlayPauseToggle = {},
                progress = 0.45f,
                onProgressChange = {},
                onSkipPrevious = {},
                onSkipNext = {},
            )
        }
    }
}
