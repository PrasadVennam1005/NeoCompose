package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
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
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Calendar Month Grid Day Picker Neumorphic Component.
 *
 * @param selectedDay Currently selected day of month integer (1..31).
 * @param onDaySelected Callback when a day is selected.
 * @param monthName Header month string (e.g. "July 2026").
 * @param modifier Custom modifier.
 * @param daysInMonth Total number of days in month (defaults to 31).
 * @param shape Card shape.
 * @param style Surface visual style.
 * @param elevation Shadow displacement depth.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param animationSpec Custom animation specifications.
 */
@Composable
public fun NeoDatePicker(
    selectedDay: Int,
    onDaySelected: (Int) -> Unit,
    monthName: String = "July 2026",
    modifier: Modifier = Modifier,
    daysInMonth: Int = 31,
    onPreviousMonthClick: (() -> Unit)? = null,
    onNextMonthClick: (() -> Unit)? = null,
    shape: Shape = NeoTheme.shapes.large,
    style: NeoStyle = NeoStyle.Raised,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    animationSpec: NeoAnimationSpec = NeoAnimationSpec()
) {
    val daysList = (1..daysInMonth).toList()
    val weekHeaders = listOf("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa")

    NeoCard(
        modifier = modifier.fillMaxWidth(),
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
            // Month Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NeoIconButton(
                    onClick = { onPreviousMonthClick?.invoke() },
                    size = NeoTheme.size.controlSmall,
                    lightSource = lightSource
                ) {
                    Icon(Icons.Default.ChevronLeft, contentDescription = "Previous Month", tint = colors.textPrimary)
                }

                Text(
                    text = monthName,
                    style = NeoTheme.typography.title,
                    color = colors.textPrimary
                )

                NeoIconButton(
                    onClick = { onNextMonthClick?.invoke() },
                    size = NeoTheme.size.controlSmall,
                    lightSource = lightSource
                ) {
                    Icon(Icons.Default.ChevronRight, contentDescription = "Next Month", tint = colors.textPrimary)
                }
            }

            // Days of week header row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                weekHeaders.forEach { header ->
                    Text(
                        text = header,
                        style = NeoTheme.typography.caption,
                        color = colors.textSecondary
                    )
                }
            }

            // Days grid (7 columns)
            Column(verticalArrangement = Arrangement.spacedBy(NeoTheme.spacing.small)) {
                daysList.chunked(7).forEach { weekRow ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        weekRow.forEach { day ->
                            val isSelected = day == selectedDay
                            Box(
                                modifier = Modifier
                                    .size(NeoTheme.size.controlSmall)
                                    .neoStyle(
                                        style = if (isSelected) NeoStyle.Inset else NeoStyle.Flat,
                                        shape = CircleShape,
                                        backgroundColor = if (isSelected) colors.surface else colors.surface.copy(alpha = 0f),
                                        lightColor = colors.lightShadow,
                                        darkColor = colors.darkShadow,
                                        elevation = if (isSelected) NeoTheme.elevation.level2 else Dp(0f),
                                        lightSource = lightSource
                                    )
                                    .clickable { onDaySelected(day) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = day.toString(),
                                    style = NeoTheme.typography.label,
                                    color = if (isSelected) colors.primary else colors.textPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "NeoDatePicker Preview")
@Composable
private fun NeoDatePickerPreview() {
    NeoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NeoDatePicker(
                selectedDay = 21,
                onDaySelected = {}
            )
        }
    }
}
