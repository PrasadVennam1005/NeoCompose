# NeoCompose Architecture Overview

NeoCompose is engineered to provide realistic Neumorphism rendering in Jetpack Compose without incurring layout performance overhead or unnecessary object allocations.

---

## 1. Modular Layering

```
+-------------------------------------------------------+
|                     :components                       |  High-level UI components (NeoButton, NeoSurface, etc.)
+--------------------+----------------------------------+
                     |
+--------------------+----------------------------------+
|                     :animation                        |  Spring & tween transitions for Neumorphism states
+--------------------+----------------------------------+
                     |
+--------------------+----------------------------------+
|                     :foundation                       |  Modifiers (neoShadow, neoStyle), interaction handling
+----------+---------+--------------------+-------------+
           |                              |
+----------v---------+          +---------v-------------+
|       :theme       |          |        :core          |  Theme tokens & Rendering engine
+--------------------+          +-----------------------+
```

---

## 2. Core Rendering Principles

1. **DrawScope Integration**:
   - Shadows and surface gradients are rendered directly in `Modifier.drawBehind` / `Modifier.drawWithContent`.
   - Layout tree hierarchy is unaffected: 0 extra `Box` nodes are inserted.

2. **Dual-Shadow Offsets**:
   - Neumorphism calculates light offset vectors using light angle $\theta$ and distance $d$:
     $$\Delta x = d \cdot \cos(\theta), \quad \Delta y = d \cdot \sin(\theta)$$
   - Light shadow: $(-\Delta x, -\Delta y)$ with lighter color tone.
   - Dark shadow: $(+\Delta x, +\Delta y)$ with darker color tone.

3. **Zero Allocation in Composition**:
   - `Paint`, `Path`, `RenderEffect`, and color calculations are remembered during composition phase and cached across re-renders.
   - Computations are offloaded to pre-rendered bitmap caches on draw calls.

---

## 3. Supported Neumorphism Styles

| Style | Description | Light Source Response |
| :--- | :--- | :--- |
| **Raised** | Surface extruded outwards | Outer dual shadows (light top-left, dark bottom-right) |
| **Pressed** | Surface pressed inwards | Inner dual shadows clipped within surface bounds |
| **Inset** | Recessed container | Inset inner drop shadows |
| **Concave** | Curved interior | Convex color gradient + outer dual shadow |
| **Convex** | Curved exterior | Reverse convex color gradient + outer dual shadow |
| **Basin** | Double-recessed edge | Combined outer shadow + inner shadow gradient |
| **Flat** | Level with background | Subtle border elevation |

---

## 4. High-Contrast Accessibility Mode
- Integrates systemic high-contrast tokens into the color palette factories.
- Modifiers automatically adjust shadow alphas (enhancing shadow contrast) and draw a WCAG-compliant `1.dp` border of `colors.textSecondary` if no custom border is configured.

---

## 5. Orientation Sensor Binding (`rememberSensorLightSource`)
- Resolves accelerometer device measurements into a real-time light angle vector.
- Implements gravity vector thresholding to stabilize light coordinates when flat, and low-pass filtering to guarantee smooth shadow rotations on rotation movements.

---

## 6. Specular Highlights Shader
- Implements hardware-accelerated 3D Blinn-Phong specular highlight shading using Android Graphics Shading Language (AGSL).
- Generates hemispherical normal vectors relative to the component center, calculating light-reflectance angles.
- Provides a clean radial gradient overlay fallback on older API levels (< API 33).
