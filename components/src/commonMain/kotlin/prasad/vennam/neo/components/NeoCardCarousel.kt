package prasad.vennam.neo.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import prasad.vennam.neo.core.NeoLightSource
import prasad.vennam.neo.core.NeoStyle
import prasad.vennam.neo.foundation.neoStyle
import prasad.vennam.neo.theme.NeoColors
import prasad.vennam.neo.theme.NeoTheme
import kotlin.math.absoluteValue

/**
 * A horizontal swipeable stacked Carousel of Neumorphic Cards.
 *
 * Provides smooth 3D rotation, scaling, and neumorphic elevation morphing
 * based on the swipe/scroll state offset of each card.
 *
 * @param items List of items of type [T] to present.
 * @param modifier Custom modifier.
 * @param cardHeight Heights of the carousel cards.
 * @param style Style of the cards (defaults to [NeoStyle.Raised]).
 * @param shape Shape of the cards.
 * @param elevation Base shadow displacement distance.
 * @param colors Color palette tokens.
 * @param lightSource Directional light source.
 * @param content Composable page renderer function for each item [T].
 */
@Composable
public fun <T> NeoCardCarousel(
    items: List<T>,
    modifier: Modifier = Modifier,
    cardHeight: Dp = 200.dp,
    style: NeoStyle = NeoStyle.Raised,
    shape: Shape = RoundedCornerShape(24.dp),
    elevation: Dp = NeoTheme.elevation.level3,
    colors: NeoColors = NeoTheme.colors,
    lightSource: NeoLightSource = NeoTheme.lighting.lightSource,
    content: @Composable (T) -> Unit,
) {
    if (items.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { items.size })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 48.dp),
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) { page ->
        val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction

        // 1. Dynamic scale transformation
        val scale = (1f - (pageOffset.absoluteValue * 0.15f)).coerceIn(0.8f, 1f)

        // 2. 3D rotation Y-axis effect
        val rotationY = (pageOffset * -15f).coerceIn(-15f, 15f)

        // 3. Dynamic opacity / alpha fade
        val alpha = (1f - (pageOffset.absoluteValue * 0.3f)).coerceIn(0.6f, 1f)

        // 4. Neumorphic elevation morphing (center card gets full elevation, side cards get flatter)
        val calculatedElevation = elevation * (1f - pageOffset.absoluteValue.coerceIn(0f, 0.75f))

        Box(
            modifier = Modifier
                .graphicsLayer {
                    this.scaleX = scale
                    this.scaleY = scale
                    this.rotationY = rotationY
                    this.alpha = alpha
                }
                .fillMaxWidth()
                .height(cardHeight)
                .neoStyle(
                    style = style,
                    shape = shape,
                    backgroundColor = colors.surface,
                    lightColor = colors.lightShadow,
                    darkColor = colors.darkShadow,
                    elevation = calculatedElevation,
                    lightSource = lightSource,
                ),
            contentAlignment = Alignment.Center
        ) {
            content(items[page])
        }
    }
}
