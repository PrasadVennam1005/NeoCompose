# Migration Guide: Upgrading to NeoCompose

This guide assists developers in migrating standard Material 3 Compose applications to `NeoCompose`.

---

## 1. Replacing MaterialTheme with NeoTheme

### Material 3
```kotlin
MaterialTheme(
    colorScheme = lightColorScheme()
) {
    Surface {
        Content()
    }
}
```

### NeoCompose
```kotlin
NeoTheme(
    colors = NeoColors.defaultLightColors()
) {
    Surface(color = NeoTheme.colors.background) {
        Content()
    }
}
```

---

## 2. Component Replacements

| Material 3 Component | NeoCompose Component | Key Differences |
| :--- | :--- | :--- |
| `Button` | `NeoButton` | Built-in press depth animation & soft dual shadows |
| `Card` | `NeoCard` | Supports `NeoStyle.Raised`, `Concave`, `Convex` |
| `IconButton` | `NeoIconButton` | Circular inset/raised shadow options |
| `OutlinedTextField` | `NeoTextField` | Inset inner drop shadows |
| `Checkbox` | `NeoCheckbox` | Inset background when checked |
| `Switch` | `NeoSwitch` | Animated thumb displacement inside inset track |
| `Slider` | `NeoSlider` | Neumorphic raised thumb |
| `LinearProgressIndicator` | `NeoLinearProgressIndicator` | Raised progress bar inside inset track |
| `FloatingActionButton` | `NeoFAB` | High elevation dual shadow FAB |
