package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Sliding Neumorphic Modal Bottom Sheet component with top drag handle pill.
 *
 * @param onDismissRequest Callback when bottom sheet is dismissed.
 * @param modifier Custom modifier.
 * @param sheetState Bottom sheet state controller.
 * @param shape Sheet shape (defaults to top-rounded corners).
 * @param style Surface visual style.
 * @param elevation Shadow displacement depth.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specifications.
 * @param content Sheet inner composable content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun NeoBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    shape: Shape = RoundedCornerShape(topStart = NeoTheme.spacing.large, topEnd = NeoTheme.spacing.large),
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level5,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    content: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = colors.surface,
        contentColor = colors.textPrimary,
        shape = shape,
        dragHandle = {
            // Recessed Neumorphic drag handle pill
            Box(
                modifier = Modifier
                    .padding(vertical = NeoTheme.spacing.medium)
                    .width(NeoTheme.size.controlMedium)
                    .height(NeoTheme.size.trackHeightSlim)
                    .neoStyle(
                        style = NeoStyle.Inset,
                        shape = CircleShape,
                        backgroundColor = colors.surface,
                        lightColor = colors.lightShadow,
                        darkColor = colors.darkShadow,
                        elevation = NeoTheme.elevation.level2,
                        lightSource = lightSource
                    )
            )
        },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = NeoTheme.spacing.medium,
                    end = NeoTheme.spacing.medium,
                    bottom = NeoTheme.spacing.large
                ),
            content = content
        )
    }
}

@Preview(name = "NeoBottomSheet Content Preview")
@Composable
private fun NeoBottomSheetContentPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoCard(modifier = Modifier.fillMaxWidth()) {
                Column {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(48.dp)
                            .height(6.dp)
                            .neoStyle(
                                style = NeoStyle.Inset,
                                shape = CircleShape,
                                backgroundColor = NeoTheme.colors.surface,
                                lightColor = NeoTheme.colors.lightShadow,
                                darkColor = NeoTheme.colors.darkShadow,
                                elevation = NeoTheme.elevation.level2,
                                lightSource = NeoTheme.lighting.lightSource
                            )
                    )
                    Spacer(Modifier.height(16.dp))
                    Text("Neumorphic Bottom Sheet", style = NeoTheme.typography.display, color = NeoTheme.colors.textPrimary)
                    Spacer(Modifier.height(8.dp))
                    Text("Drag down or tap backdrop to dismiss", style = NeoTheme.typography.body, color = NeoTheme.colors.textSecondary)
                }
            }
        }
    }
}
