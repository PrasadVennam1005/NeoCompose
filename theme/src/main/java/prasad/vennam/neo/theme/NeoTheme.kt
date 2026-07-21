package prasad.vennam.neo.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

public val LocalNeoColors: ProvidableCompositionLocal<NeoColors> =
    staticCompositionLocalOf { NeoColors.defaultLightColors() }

public val LocalNeoLighting: ProvidableCompositionLocal<NeoLighting> =
    staticCompositionLocalOf { NeoLighting() }

public val LocalNeoElevation: ProvidableCompositionLocal<NeoElevation> =
    staticCompositionLocalOf { NeoElevation() }

public val LocalNeoShapes: ProvidableCompositionLocal<NeoShapes> =
    staticCompositionLocalOf { NeoShapes() }

public val LocalNeoSpacing: ProvidableCompositionLocal<NeoSpacing> =
    staticCompositionLocalOf { NeoSpacing() }

public val LocalNeoTypography: ProvidableCompositionLocal<NeoTypography> =
    staticCompositionLocalOf { NeoTypography() }

/**
 * Root theme provider for the Neumorphism design system.
 *
 * @param colors Color tokens (defaults to light mode colors).
 * @param lighting Global lighting vector configuration.
 * @param elevation Elevation distance tokens.
 * @param shapes Corner rounding shape tokens.
 * @param spacing Layout padding & margin tokens.
 * @param typography Text style hierarchy tokens.
 * @param content Composable tree content.
 */
@Composable
public fun NeoTheme(
    colors: NeoColors = NeoColors.defaultLightColors(),
    lighting: NeoLighting = NeoLighting(),
    elevation: NeoElevation = NeoElevation(),
    shapes: NeoShapes = NeoShapes(),
    spacing: NeoSpacing = NeoSpacing(),
    typography: NeoTypography = NeoTypography(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalNeoColors provides colors,
        LocalNeoLighting provides lighting,
        LocalNeoElevation provides elevation,
        LocalNeoShapes provides shapes,
        LocalNeoSpacing provides spacing,
        LocalNeoTypography provides typography,
        content = content
    )
}

/**
 * Entry point for retrieving active Neumorphic theme tokens inside composition.
 */
public object NeoTheme {
    public val colors: NeoColors
        @Composable
        @ReadOnlyComposable
        get() = LocalNeoColors.current

    public val lighting: NeoLighting
        @Composable
        @ReadOnlyComposable
        get() = LocalNeoLighting.current

    public val elevation: NeoElevation
        @Composable
        @ReadOnlyComposable
        get() = LocalNeoElevation.current

    public val shapes: NeoShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalNeoShapes.current

    public val spacing: NeoSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalNeoSpacing.current

    public val typography: NeoTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalNeoTypography.current
}
