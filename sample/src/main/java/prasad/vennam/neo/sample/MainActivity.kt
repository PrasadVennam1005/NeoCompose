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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.components.NeoBadge
import prasad.vennam.neo.components.NeoSegmentedControl
import prasad.vennam.neo.core.NeoLightSource
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

            val colors = if (studioState.isDarkTheme) {
                NeoColors.defaultDarkColors()
            } else {
                NeoColors.defaultLightColors()
            }

            val lighting = NeoLighting(
                lightSource = NeoLightSource(angleDegrees = studioState.lightAngle)
            )

            val elevation = NeoElevation(
                level3 = studioState.elevationDp.dp
            )

            NeoTheme(
                colors = colors,
                lighting = lighting,
                elevation = elevation
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = NeoTheme.colors.background
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(NeoTheme.colors.background)
                            .padding(innerPadding)
                            .padding(horizontal = 20.dp, vertical = 12.dp)
                    ) {
                        // Header
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "NeoCompose",
                                style = NeoTheme.typography.display,
                                color = NeoTheme.colors.textPrimary
                            )
                            Spacer(Modifier.width(12.dp))
                            NeoBadge(count = "v1.0.0")
                        }

                        // Navigation Tabs
                        NeoSegmentedControl(
                            items = listOf("Studio", "Catalog", "Dashboard"),
                            selectedIndex = studioState.selectedCategoryTab,
                            onOptionSelected = { studioState.selectedCategoryTab = it },
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Content
                        AnimatedContent(
                            targetState = studioState.selectedCategoryTab,
                            transitionSpec = { fadeIn() togetherWith fadeOut() },
                            label = "MainTabTransition"
                        ) { targetTab ->
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                            ) {
                                when (targetTab) {
                                    0 -> StudioControls(state = studioState)
                                    1 -> ComponentCatalog(state = studioState)
                                    2 -> SmartHomeDashboard(state = studioState)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
