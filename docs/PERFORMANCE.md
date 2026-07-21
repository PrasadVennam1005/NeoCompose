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

---

## Best Practices for Apps

- Use `NeoTheme.colors.background` for app screens to ensure shadow contrast blends naturally.
- Avoid wrapping individual components in redundant `Box` layers; pass `modifier` directly to `Neo` components.
- When animating light source angles, pass the animated `NeoLightSource` via `NeoTheme(lighting = ...)` at the root level to keep shadow angles synchronized across all components.
