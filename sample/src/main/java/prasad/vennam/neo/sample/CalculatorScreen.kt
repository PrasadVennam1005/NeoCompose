package prasad.vennam.neo.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.components.NeoCard
import prasad.vennam.neo.components.NeoCalculator
import prasad.vennam.neo.components.NeoSegmentedControl
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.theme.NeoTheme

@Composable
fun CalculatorScreen(state: StudioState) {
    var selectedStyleIndex by remember { mutableIntStateOf(0) }
    val styleOptions = listOf("Flat", "Raised", "Inset", "Glass")

    val keyStyle = when (selectedStyleIndex) {
        0 -> NeoStyle.Flat
        1 -> NeoStyle.Raised
        2 -> NeoStyle.Inset
        else -> NeoStyle.Glass()
    }

    val displayStyle = when (selectedStyleIndex) {
        0 -> NeoStyle.Flat
        1 -> NeoStyle.Inset
        2 -> NeoStyle.Raised
        else -> NeoStyle.Glass()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(NeoTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium),
    ) {
        NeoCard(style = state.selectedStyle, modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.medium),
            ) {
                Text(
                    text = "iOS Calculator Style Experiment",
                    style = NeoTheme.typography.title,
                    color = NeoTheme.colors.textPrimary,
                )
                Text(
                    text = "Toggle button and display style tokens to compare Flat vs Raised Neumorphism.",
                    style = NeoTheme.typography.body,
                    color = NeoTheme.colors.textSecondary,
                )

                Text(
                    text = "Key & Display Style Mode",
                    style = NeoTheme.typography.label,
                    color = NeoTheme.colors.textPrimary,
                )

                NeoSegmentedControl(
                    items = styleOptions,
                    selectedIndex = selectedStyleIndex,
                    onOptionSelected = { selectedStyleIndex = it },
                )
            }
        }

        Spacer(modifier = Modifier.height(NeoTheme.spacing.small))

        // Center iOS Calculator Component
        NeoCalculator(
            keyStyle = keyStyle,
            displayStyle = displayStyle,
            colors = NeoTheme.colors,
            lightSource = NeoTheme.lighting.lightSource,
        )
    }
}
