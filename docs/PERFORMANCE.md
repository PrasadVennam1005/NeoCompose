# NeoCompose Performance Optimization Guide

NeoCompose is engineered specifically for high-frame-rate rendering (60fps / 120fps) in Jetpack Compose.

---

## Key Performance Guarantees

1. **Zero Layout Node Overhead**:
   - Neumorphic dual shadows and inner shadows are rendered inside `Modifier.drawBehind` / `Modifier.drawWithContent`.
   - No additional `Box` layout nodes are added to the Compose layout tree hierarchy.

2. **Zero Object Allocations in Composition**:
   - `Paint` objects, `NativePaint` filters, and shadow paths are cached during initial composition.
   - Drawing calls do not allocate new objects per frame during scroll animations or gestures.

3. **Compose Stability**:
   - All theme tokens (`NeoColors`, `NeoLighting`, `NeoElevation`, `NeoShapes`, `NeoTypography`, `NeoSpacing`) are annotated with `@Stable` or `@Immutable`.
   - State reads inside composables rely on `derivedStateOf` to prevent unnecessary parent recompositions.

4. **Hardware-Accelerated Shadow Caching**:
   - Shadow renderings (which rely on CPU-bound `BlurMaskFilter` processing in the native rendering pipeline) are pre-rendered offscreen onto an `ImageBitmap` during the first draw call.
   - The computed bitmaps are stored in a thread-safe `LruCache<ShadowCacheKey, ImageBitmap>` with a capacity of 50 items.
   - Subsequent draw passes (e.g. during scroll animations or gestures) draw the pre-rendered bitmap directly to the GPU canvas, completely bypassing path operations, clip modifications, and blur calculations. This guarantees zero-allocation scroll passes and steady 120fps frame rates.

---

## Best Practices for Apps

- Use `NeoTheme.colors.background` for app screens to ensure shadow contrast blends naturally.
- Avoid wrapping individual components in redundant `Box` layers; pass `modifier` directly to `Neo` components.
- Rely on **Hardware-Accelerated Caching** for list views; dimensions and states should remain uniform so cache hits are maximized.
- When animating light source angles, pass the animated `NeoLightSource` via `NeoTheme(lighting = ...)` at the root level to keep shadow angles synchronized across all components.
