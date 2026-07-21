package prasad.vennam.neo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.animation.NeoAnimationSpec
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme

/**
 * Compact Neumorphic Chip component using design system tokens.
 *
 * @param label Chip label text.
 * @param onClick Optional event callback when clicked.
 * @param modifier Custom modifier.
 * @param enabled Whether chip is interactive.
 * @param selected Whether chip is toggled selected ([NeoStyle.Inset] vs [NeoStyle.Raised]).
 * @param shape Component shape (defaults to [CircleShape]).
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param contentPadding Internal chip padding values derived from design tokens.
 * @param animationSpec Custom animation specifications.
 * @param interactionSource Interaction stream.
 */
@Composable
public fun NeoChip(
    label: String,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
    shape: Shape = CircleShape,
    elevation: Dp = NeoTheme.elevation.level2,
    colors: NeoColors = NeoTheme.colors,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = NeoTheme.spacing.medium,
        vertical = NeoTheme.spacing.small
    ),
    animationSpec: NeoAnimationSpec = NeoAnimationSpec(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val style = if (selected) NeoStyle.Inset else NeoStyle.Raised

    val clickModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            role = Role.Button,
            onClick = onClick
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .neoStyle(
                style = style,
                shape = shape,
                backgroundColor = colors.surface,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = elevation,
                lightSource = NeoTheme.lighting.lightSource
            )
            .then(clickModifier)
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = NeoTheme.typography.label,
            color = if (selected) colors.primary else colors.textPrimary
        )
    }
}

/**
 * Small Neumorphic Badge indicator component using design system tokens.
 *
 * @param count Badge number count or text label.
 * @param modifier Custom modifier.
 * @param shape Component shape.
 * @param colors Color palette tokens.
 * @param contentPadding Internal badge padding values derived from design tokens.
 */
@Composable
public fun NeoBadge(
    count: String,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    colors: NeoColors = NeoTheme.colors,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = NeoTheme.spacing.small,
        vertical = NeoTheme.spacing.extraSmall
    )
) {
    Box(
        modifier = modifier
            .neoStyle(
                style = NeoStyle.Raised,
                shape = shape,
                backgroundColor = colors.primary,
                lightColor = colors.lightShadow,
                darkColor = colors.darkShadow,
                elevation = NeoTheme.elevation.level1,
                lightSource = NeoTheme.lighting.lightSource
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = count,
            style = NeoTheme.typography.caption,
            color = colors.onPrimary
        )
    }
}

@Preview(name = "NeoChip & NeoBadge Preview")
@Composable
private fun NeoChipPreview() {
    NeoTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NeoChip(label = "Selected", selected = true)
            Spacer(Modifier.width(8.dp))
            NeoChip(label = "Unselected", selected = false)
            Spacer(Modifier.width(16.dp))
            NeoBadge(count = "99+")
        }
    }
}
