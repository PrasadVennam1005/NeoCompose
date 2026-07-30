package prasad.vennam.neo.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoStyle

/**
 * Preview parameters defining theme color configurations and neumorphic styles
 * for Android Studio Layout Preview parameterization.
 *
 * @property style Neumorphic surface style.
 * @property colors NeoColors color set.
 * @property name Human-readable label for the configuration combination.
 */
public data class NeoPreviewParams(
    public val style: NeoStyle,
    public val colors: NeoColors,
    public val name: String,
)

/**
 * Standard provider generating combinations of light/dark themes and neumorphic
 * surface styles for `@PreviewParameter` usage.
 */
public class NeoPreviewParamsProvider : PreviewParameterProvider<NeoPreviewParams> {
    override val values: Sequence<NeoPreviewParams> =
        sequenceOf(
            NeoPreviewParams(
                style = NeoStyle.Raised,
                colors = NeoColors.defaultLightColors(),
                name = "Light Raised",
            ),
            NeoPreviewParams(
                style = NeoStyle.Pressed,
                colors = NeoColors.defaultLightColors(),
                name = "Light Pressed",
            ),
            NeoPreviewParams(
                style = NeoStyle.Inset,
                colors = NeoColors.defaultLightColors(),
                name = "Light Inset",
            ),
            NeoPreviewParams(
                style = NeoStyle.Concave,
                colors = NeoColors.defaultLightColors(),
                name = "Light Concave",
            ),
            NeoPreviewParams(
                style = NeoStyle.Convex,
                colors = NeoColors.defaultLightColors(),
                name = "Light Convex",
            ),
            NeoPreviewParams(
                style = NeoStyle.Basin,
                colors = NeoColors.defaultLightColors(),
                name = "Light Basin",
            ),
            NeoPreviewParams(
                style = NeoStyle.Glass(),
                colors = NeoColors.defaultLightColors(),
                name = "Glassmorphism",
            ),
            NeoPreviewParams(
                style = NeoStyle.Glow(glowColor = Color(0xFF00E5FF)),
                colors = NeoColors.defaultDarkColors(),
                name = "Neon Cyan Glow",
            ),
            NeoPreviewParams(
                style = NeoStyle.Clay(primaryColor = Color(0xFFFFB74D)),
                colors = NeoColors.defaultLightColors(),
                name = "Pastel Clay",
            ),
            NeoPreviewParams(
                style = NeoStyle.Metallic(finish = NeoStyle.Metallic.Finish.Brushed),
                colors = NeoColors.defaultLightColors(),
                name = "Brushed Metal",
            ),
            NeoPreviewParams(
                style = NeoStyle.Metallic(finish = NeoStyle.Metallic.Finish.Carbon),
                colors = NeoColors.defaultDarkColors(),
                name = "Carbon Fiber",
            ),
        )
}
