# NeoCompose Design & Usage Guidelines 🎨

This document provides comprehensive design system guidelines, visual principles, and best practices for creating stunning Neumorphic user interfaces with `NeoCompose`.

---

## 1. Core Neumorphism Principles ✨

Neumorphism (Soft UI) creates extruded, tactile surfaces by combining directional highlights and soft drop shadows on a continuous monochromatic background.

### The Background Rule ⚠️
In Neumorphism, components **share the exact background color** of their parent container (`#ECF0F3` in light mode, `#24292E` in dark mode).
- **Do NOT** place raised Neumorphic components on white (`#FFFFFF`) or pure black (`#000000`) backgrounds.
- Always wrap your screen content in `NeoTheme` and set `containerColor = NeoTheme.colors.background`.

```kotlin
NeoTheme {
    Surface(color = NeoTheme.colors.background) {
        // App Content
    }
}
```

---

## 2. Neumorphic Surface Styles 🧩

`NeoCompose` provides 6 surface visual styles via `NeoStyle`:

| Style | Visual Appearance | Primary Use Case |
| :--- | :--- | :--- |
| `NeoStyle.Raised` | Extruded surface floating above background | Unpressed buttons, cards, FABs, unselected tabs |
| `NeoStyle.Pressed` | Compressed surface pushed closer to background | Active press feedback state on buttons & cards |
| `NeoStyle.Inset` | Recessed groove carved into background | Text input fields, checkbox containers, progress tracks, active tabs |
| `NeoStyle.Concave` | Curved surface sloping away from light source | Hero action cards, luxury audio controls, luxury dials |
| `NeoStyle.Convex` | Curved surface sloping toward light source | Soft convex buttons, status badges |
| `NeoStyle.Flat` | Flat surface level with background | Unselected subtle menu items |

---

## 3. Light Source & Shadow Math 💡

Shadow direction is governed globally by `NeoLightSource`.

```kotlin
// Light coming from top-left (315°) - Standard default
NeoTheme(
    lighting = NeoLighting(lightSource = NeoLightSource(angleDegrees = 315f))
)
```

- **Elevation Levels**:
  - `NeoTheme.elevation.level1` (2.dp) – Badges, chips
  - `NeoTheme.elevation.level2` (4.dp) – Checkboxes, radio buttons, linear progress tracks
  - `NeoTheme.elevation.level3` (6.dp) – Standard buttons, text fields, cards
  - `NeoTheme.elevation.level4` (10.dp) – Floating Action Buttons (FAB), hero cards

---

## 4. Color & Contrast Rules 🎨

1. **Brand Accents**: Use high-contrast brand accents (`Color(0xFF007AFF)` Electric Blue, `Color(0xFFFF5E00)` Orange) for active selection states (checked switches, selected radio dots, active slider progress bars).
2. **Text Readability**: Ensure primary text uses `NeoTheme.colors.textPrimary` (`#2D3748` in light mode) for high legibility.
3. **No Heavy Outlines**: In true Neumorphism, shadows define component boundaries. Keep `borderWidth = 0.dp` unless highlighting focus states on text inputs.

---

## 5. Shape & Token Hierarchy 📐

Use `NeoTheme` design tokens instead of hardcoded `.dp` values:

- **Shapes**:
  - `NeoTheme.shapes.pill` (`CircleShape`) – Buttons, chips, search inputs, segmented control bars.
  - `NeoTheme.shapes.large` (`RoundedCornerShape(16.dp)`) – Cards, dialogs, media containers.
- **Spacing**:
  - `NeoTheme.spacing.small` (8.dp) – Inner element gap.
  - `NeoTheme.spacing.medium` (16.dp) – Standard component padding.
  - `NeoTheme.spacing.large` (24.dp) – Button horizontal padding.

---

## 6. Accessibility & Touch Standards ♿

1. **Touch Targets**: All interactive controls (`NeoButton`, `NeoIconButton`, `NeoCheckbox`, `NeoRadioButton`) satisfy Android accessibility touch target guidelines (minimum 48.dp x 48.dp hit area).
2. **State Contrast**: Checked/Selected states (`NeoCheckbox`, `NeoSwitch`, `NeoRadioButton`) use filled primary background colors to assist visually impaired users.
3. **Content Descriptions**: Always supply clear `contentDescription` strings for icon components.
