package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
 * Neumorphic Modal Alert Dialog component.
 *
 * @param onDismissRequest Callback when dialog is dismissed.
 * @param title Dialog header title.
 * @param message Dialog body message text.
 * @param confirmButtonLabel Confirm action button text.
 * @param onConfirmClick Callback when confirm button is clicked.
 * @param modifier Custom modifier.
 * @param dismissButtonLabel Optional dismiss action button text.
 * @param onDismissClick Optional callback when dismiss button is clicked.
 * @param shape Dialog card shape.
 * @param style Surface visual style.
 * @param elevation High shadow displacement depth.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specifications.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun NeoDialog(
    onDismissRequest: () -> Unit,
    title: String,
    message: String,
    confirmButtonLabel: String,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
    dismissButtonLabel: String? = null,
    onDismissClick: (() -> Unit)? = null,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level5,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec()
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        NeoCard(
            modifier = Modifier.fillMaxWidth(0.92f),
            shape = shape,
            style = style,
            elevation = elevation,
            colors = colors,
            lightSource = lightSource,
            animationSpec = animationSpec
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium)
            ) {
                Text(
                    text = title,
                    style = NeoTheme.typography.display,
                    color = colors.textPrimary
                )

                Text(
                    text = message,
                    style = NeoTheme.typography.body,
                    color = colors.textSecondary
                )

                Spacer(Modifier.height(NeoTheme.spacing.small))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (dismissButtonLabel != null && onDismissClick != null) {
                        NeoButton(
                            onClick = onDismissClick,
                            style = NeoStyle.Flat,
                            elevation = Dp(0f),
                            lightSource = lightSource
                        ) {
                            Text(
                                text = dismissButtonLabel,
                                style = NeoTheme.typography.label,
                                color = colors.textSecondary
                            )
                        }
                        Spacer(Modifier.width(NeoTheme.spacing.small))
                    }

                    NeoButton(
                        onClick = onConfirmClick,
                        style = NeoStyle.Raised,
                        lightSource = lightSource
                    ) {
                        Text(
                            text = confirmButtonLabel,
                            style = NeoTheme.typography.label,
                            color = colors.primary
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "NeoDialog Content Preview")
@Composable
private fun NeoDialogContentPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoCard(modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("Confirm Action", style = NeoTheme.typography.display, color = NeoTheme.colors.textPrimary)
                    Text("Are you sure you want to deploy the latest Neumorphism design system tokens to production?", style = NeoTheme.typography.body, color = NeoTheme.colors.textSecondary)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        NeoButton(onClick = {}) {
                            Text("Cancel", style = NeoTheme.typography.label, color = NeoTheme.colors.textSecondary)
                        }
                        Spacer(Modifier.width(8.dp))
                        NeoButton(onClick = {}) {
                            Text("Deploy", style = NeoTheme.typography.label, color = NeoTheme.colors.primary)
                        }
                    }
                }
            }
        }
    }
}
