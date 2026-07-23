package prasad.vennam.neo.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.components.NeoBadge
import prasad.vennam.neo.components.NeoSegmentedControl
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.foundation.LocalShadowAlphaMultiplier
import prasad.vennam.neo.foundation.neoParallaxSensor
import prasad.vennam.neo.foundation.rememberSensorAmbientContrast
import prasad.vennam.neo.foundation.rememberSensorLightSource
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoElevation
import prasad.vennam.neo.theme.NeoLighting
import prasad.vennam.neo.theme.NeoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val studioState = remember { StudioState() }

            val colors =
                if (studioState.isDarkTheme) {
                    NeoColors.defaultDarkColors(isHighContrast = studioState.isAccessibilityMode)
                } else {
                    NeoColors.defaultLightColors(isHighContrast = studioState.isAccessibilityMode)
                }

            val sensorLightSource = rememberSensorLightSource(defaultAngleDegrees = studioState.lightAngle)

            val lighting =
                NeoLighting(
                    lightSource =
                        if (studioState.isSensorLightingEnabled) {
                            sensorLightSource
                        } else {
                            NeoLightSource(angleDegrees = studioState.lightAngle)
                        },
                )

            val elevation =
                NeoElevation(
                    level3 = studioState.elevationDp.dp,
                )

            val ambientContrastMultiplier =
                if (studioState.isAmbientContrastEnabled) {
                    rememberSensorAmbientContrast().value
                } else {
                    1.0f
                }

            val studioScrollState = rememberScrollState()
            val catalogScrollState = rememberScrollState()
            val dashboardScrollState = rememberScrollState()

            NeoTheme(
                colors = colors,
                lighting = lighting,
                elevation = elevation,
            ) {
                CompositionLocalProvider(LocalShadowAlphaMultiplier provides ambientContrastMultiplier) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = NeoTheme.colors.background,
                    ) { innerPadding ->
                        Column(
                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .background(NeoTheme.colors.background)
                                    .padding(innerPadding)
                                    .padding(
                                        horizontal = NeoTheme.spacing.medium + NeoTheme.spacing.extraSmall,
                                        vertical = NeoTheme.spacing.small + NeoTheme.spacing.extraSmall,
                                    ),
                        ) {
                            // Header
                            Row(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = NeoTheme.spacing.medium),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "NeoCompose",
                                    style = NeoTheme.typography.display,
                                    color = NeoTheme.colors.textPrimary,
                                )
                                Spacer(Modifier.width(NeoTheme.spacing.small + NeoTheme.spacing.extraSmall))
                                NeoBadge(count = "v1.0.0")
                            }

                            // Navigation Tabs
                            NeoSegmentedControl(
                                items = listOf("Studio", "Catalog", "Dashboard"),
                                selectedIndex = studioState.selectedCategoryTab,
                                onOptionSelected = { studioState.selectedCategoryTab = it },
                                modifier = Modifier.padding(bottom = NeoTheme.spacing.medium),
                            )

                            // Content wrapping container to ensure strict scroll bounds
                            Box(
                                modifier =
                                    Modifier
                                        .weight(1f)
                                        .fillMaxWidth(),
                            ) {
                                AnimatedContent(
                                    targetState = studioState.selectedCategoryTab,
                                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                                    label = "MainTabTransition",
                                    modifier = Modifier.fillMaxSize(),
                                ) { targetTab ->
                                    val scrollState =
                                        when (targetTab) {
                                            0 -> studioScrollState
                                            1 -> catalogScrollState
                                            else -> dashboardScrollState
                                        }
                                    Column(
                                        modifier =
                                            Modifier
                                                .fillMaxSize()
                                                .verticalScroll(scrollState)
                                                .neoParallaxSensor(enabled = studioState.isParallaxTiltEnabled),
                                    ) {
                                        when (targetTab) {
                                            0 -> StudioControls(state = studioState)
                                            1 -> ComponentCatalog(state = studioState)
                                            2 -> SmartHomeDashboard(state = studioState)
                                        }
                                        Spacer(Modifier.height(32.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
