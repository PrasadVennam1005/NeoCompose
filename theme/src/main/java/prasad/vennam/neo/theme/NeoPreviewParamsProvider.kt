package prasad.vennam.neo.theme

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
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
                style = NeoStyle.Concave,
                colors = NeoColors.defaultLightColors(),
                name = "Light Concave",
            ),
            NeoPreviewParams(
                style = NeoStyle.Raised,
                colors = NeoColors.defaultDarkColors(),
                name = "Dark Raised",
            ),
            NeoPreviewParams(
                style = NeoStyle.Pressed,
                colors = NeoColors.defaultDarkColors(),
                name = "Dark Pressed",
            ),
            NeoPreviewParams(
                style = NeoStyle.Concave,
                colors = NeoColors.defaultDarkColors(),
                name = "Dark Concave",
            ),
        )
}
