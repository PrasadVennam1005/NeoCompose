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
| `RadioButton` | `NeoRadioButton` | Recessed inset radio ring with raised inner dot |
| `Switch` | `NeoSwitch` | Animated thumb displacement inside inset track |
| `SingleChoiceSegmentedButtonRow` | `NeoSegmentedControl` | Multi-option tab segment container |
| `AssistChip` / `FilterChip` | `NeoChip` | Neumorphic selection chip |
| `Badge` | `NeoBadge` | Elevated numbers & counter badge |
| `Tab` / `ScrollableTabRow` | `NeoTabBar` | Neumorphic tab navigation bar |
| `Slider` | `NeoSlider` | Draggable Neumorphic raised thumb |
| `LinearProgressIndicator` | `NeoLinearProgressIndicator` | Raised progress bar inside inset track |
| `CircularProgressIndicator` | `NeoCircularProgressIndicator` | Progress ring inside recessed container |
| `FloatingActionButton` | `NeoFAB` | High elevation dual shadow FAB |
