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

---

## 3. Supported Neumorphism Styles

| Style | Description | Light Source Response |
| :--- | :--- | :--- |
| **Raised** | Surface extruded outwards | Outer dual shadows (light top-left, dark bottom-right) |
| **Pressed** | Surface pressed inwards | Inner dual shadows clipped within surface bounds |
| **Inset** | Recessed container | Inset inner drop shadows |
| **Concave** | Curved interior | Convex color gradient + outer dual shadow |
| **Convex** | Curved exterior | Reverse convex color gradient + outer dual shadow |
| **Flat** | Level with background | Subtle border elevation |
