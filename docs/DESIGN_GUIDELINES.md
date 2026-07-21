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

## 2. Neumorphic Light Source Presets 💡

`NeoLightSource` provides preset constants matching standard Neumorphism specifications:

| Preset Name | Angle | Description |
| :--- | :--- | :--- |
| `NeoLightSource.TopLeft` (`LEFT_TOP`) | 315° | Standard default incoming light from Top-Left |
| `NeoLightSource.BottomLeft` (`LEFT_BOTTOM`) | 225° | Incoming light from Bottom-Left |
| `NeoLightSource.TopRight` (`RIGHT_TOP`) | 45° | Incoming light from Top-Right |
| `NeoLightSource.BottomRight` (`RIGHT_BOTTOM`) | 135° | Incoming light from Bottom-Right |

---

## 3. Surface Shape Types (`NeoStyle`) 🧩

`NeoCompose` provides full support for standard Neumorphism `ShapeType` conventions:

| ShapeType / Style | Visual Appearance | Primary Use Case |
| :--- | :--- | :--- |
| `NeoStyle.Raised` / `FLAT` | Extruded surface floating above background with dual outer shadows | Unpressed buttons, cards, FABs |
| `NeoStyle.Pressed` / `PRESSED` | Compressed surface pushed closer to background with inner shadows | Active press feedback, recessed inputs |
| `NeoStyle.Basin` / `BASIN` | Curved interior rim surface sloping toward central depression | Circular media buttons, luxury dials, action pods |
| `NeoStyle.Inset` | Recessed container with deep inner drop shadows | Text fields, checkbox containers, progress tracks |
| `NeoStyle.Concave` | Curved interior surface with directional lighting gradient | Hero action cards, luxury controls |
| `NeoStyle.Convex` | Curved exterior surface with reversed lighting gradient | Convex badges, convex buttons |

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
