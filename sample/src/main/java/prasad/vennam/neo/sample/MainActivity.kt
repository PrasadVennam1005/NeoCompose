package prasad.vennam.neo.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                            .verticalScroll(rememberScrollState())
                            .padding(innerPadding)
                            .padding(20.dp)
                    ) {
                        StudioControls(state = studioState)
                        ComponentCatalog(
                            state = studioState,
                            modifier = Modifier.padding(top = 24.dp)
                        )
                    }
                }
            }
        }
    }
}
