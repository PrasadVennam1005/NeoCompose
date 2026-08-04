package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme
import kotlin.math.abs

/**
 * iOS-style Neumorphic Calculator component.
 *
 * @param modifier Custom modifier.
 * @param keyStyle Neumorphic style for buttons (e.g. [NeoStyle.Flat], [NeoStyle.Raised], [NeoStyle.Inset]).
 * @param displayStyle Neumorphic style for display screen.
 * @param shape Container outer shape.
 * @param elevation Elevation level.
 * @param colors Theme color tokens.
 * @param lightSource Light vector direction.
 * @param onResultCalculated Callback when calculation finishes.
 */
@Composable
public fun NeoCalculator(
    modifier: Modifier = Modifier,
    keyStyle: NeoStyle = NeoStyle.Raised,
    displayStyle: NeoStyle = NeoStyle.Inset,
    shape: Shape = NeoTheme.shapes.large,
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    onResultCalculated: ((Double) -> Unit)? = null,
) {
    var displayText by remember { mutableStateOf("0") }
    var expressionText by remember { mutableStateOf("") }
    var storedOperand by remember { mutableStateOf<Double?>(null) }
    var pendingOperation by remember { mutableStateOf<String?>(null) }
    var isNewInput by remember { mutableStateOf(true) }

    fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0 && abs(value) < 1e12) {
            value.toLong().toString()
        } else {
            val formatted = "%.6f".format(value).trimEnd('0').trimEnd('.')
            if (formatted.isEmpty()) "0" else formatted
        }
    }

    fun onNumberPress(digit: String) {
        if (isNewInput || displayText == "0" || displayText == "Error") {
            displayText = if (digit == ".") "0." else digit
            isNewInput = false
        } else {
            if (digit == "." && displayText.contains(".")) return
            if (displayText.length < 10) {
                displayText += digit
            }
        }
    }

    fun onOperationPress(op: String) {
        val currentVal = displayText.toDoubleOrNull() ?: return
        if (storedOperand != null && pendingOperation != null && !isNewInput) {
            val res = calculate(storedOperand!!, currentVal, pendingOperation!!)
            if (res != null) {
                displayText = formatResult(res)
                storedOperand = res
                onResultCalculated?.invoke(res)
            } else {
                displayText = "Error"
                storedOperand = null
                pendingOperation = null
                isNewInput = true
                return
            }
        } else {
            storedOperand = currentVal
        }
        pendingOperation = op
        expressionText = "${formatResult(storedOperand!!)} $op"
        isNewInput = true
    }

    fun onEqualsPress() {
        val currentVal = displayText.toDoubleOrNull()
        val op = pendingOperation
        val prev = storedOperand

        if (prev != null && op != null && currentVal != null) {
            val result = calculate(prev, currentVal, op)
            if (result != null) {
                expressionText = "${formatResult(prev)} $op ${formatResult(currentVal)} ="
                displayText = formatResult(result)
                storedOperand = null
                pendingOperation = null
                isNewInput = true
                onResultCalculated?.invoke(result)
            } else {
                displayText = "Error"
                expressionText = ""
                storedOperand = null
                pendingOperation = null
                isNewInput = true
            }
        }
    }

    fun onClearPress() {
        displayText = "0"
        expressionText = ""
        storedOperand = null
        pendingOperation = null
        isNewInput = true
    }

    fun onSignToggle() {
        val valDouble = displayText.toDoubleOrNull() ?: return
        val toggled = valDouble * -1
        displayText = formatResult(toggled)
    }

    fun onPercentPress() {
        val valDouble = displayText.toDoubleOrNull() ?: return
        val percent = valDouble / 100.0
        displayText = formatResult(percent)
    }

    val haptic = LocalHapticFeedback.current

    Box(
        modifier = modifier
            .width(320.dp)
            .neoStyle(
                style = NeoStyle.Raised,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = lightSource,
            )
            .padding(16.dp),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // iOS Display Screen
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(104.dp)
                    .neoStyle(
                        style = displayStyle,
                        shape = NeoTheme.shapes.medium,
                        backgroundColor = colors.surface,
                        lightColor = colors.lightShadow,
                        darkColor = colors.darkShadow,
                        elevation = NeoTheme.elevation.level2,
                        lightSource = lightSource,
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.BottomEnd,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = expressionText,
                        style = NeoTheme.typography.caption,
                        color = colors.textSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End,
                    )
                    Text(
                        text = displayText,
                        fontSize = when {
                            displayText.length > 9 -> 26.sp
                            displayText.length > 6 -> 34.sp
                            else -> 42.sp
                        },
                        fontWeight = FontWeight.Light,
                        color = colors.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val clearLabel = if (displayText != "0" || storedOperand != null) "C" else "AC"
            val keypad: List<List<Pair<String, () -> Unit>>> = listOf(
                listOf(
                    clearLabel to { onClearPress() },
                    "±" to { onSignToggle() },
                    "%" to { onPercentPress() },
                    "÷" to { onOperationPress("÷") }
                ),
                listOf("7" to { onNumberPress("7") }, "8" to { onNumberPress("8") }, "9" to { onNumberPress("9") }, "×" to { onOperationPress("×") }),
                listOf("4" to { onNumberPress("4") }, "5" to { onNumberPress("5") }, "6" to { onNumberPress("6") }, "−" to { onOperationPress("−") }),
                listOf("1" to { onNumberPress("1") }, "2" to { onNumberPress("2") }, "3" to { onNumberPress("3") }, "+" to { onOperationPress("+") }),
                listOf("0" to { onNumberPress("0") }, "." to { onNumberPress(".") }, "=" to { onEqualsPress() }),
            )

            keypad.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    row.forEach { (label, onClick) ->
                        val isOperator = label in listOf("÷", "×", "−", "+", "=")
                        val isFunction = label in listOf("AC", "C", "±", "%")
                        val isSelectedOperator = pendingOperation == label && isNewInput

                        val buttonStyle = when {
                            isSelectedOperator -> NeoStyle.Inset
                            else -> keyStyle
                        }

                        val buttonColor = when {
                            isOperator -> colors.primary
                            isFunction -> colors.surface.copy(alpha = 0.85f)
                            else -> colors.surface
                        }

                        val textColor = when {
                            isOperator -> colors.onPrimary
                            isFunction -> colors.textPrimary
                            else -> colors.textPrimary
                        }

                        val isWide = label == "0"
                        val keyShape = CircleShape

                        val interactionSource = remember { MutableInteractionSource() }

                        Box(
                            modifier = Modifier
                                .weight(if (isWide) 2.15f else 1f)
                                .height(60.dp)
                                .neoStyle(
                                    style = buttonStyle,
                                    shape = keyShape,
                                    backgroundColor = buttonColor,
                                    lightColor = colors.lightShadow,
                                    darkColor = colors.darkShadow,
                                    elevation = if (keyStyle is NeoStyle.Flat) 0.dp else NeoTheme.elevation.level2,
                                    lightSource = lightSource,
                                )
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    role = Role.Button,
                                    onClick = {
                                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                        onClick()
                                    },
                                ),
                            contentAlignment = if (isWide) Alignment.CenterStart else Alignment.Center,
                        ) {
                            Text(
                                text = label,
                                fontSize = if (isOperator || isFunction) 22.sp else 24.sp,
                                fontWeight = if (isOperator) FontWeight.SemiBold else FontWeight.Normal,
                                color = textColor,
                                modifier = if (isWide) Modifier.padding(start = 24.dp) else Modifier,
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun calculate(a: Double, b: Double, op: String): Double? {
    return when (op) {
        "+" -> a + b
        "−" -> a - b
        "×" -> a * b
        "÷" -> if (b == 0.0) null else a / b
        else -> null
    }
}
