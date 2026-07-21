# Migration Guide

## Upgrading to v1.2.0

### Key Updates in v1.2.0

#### 1. Per-Component Directional Light Control
All component function signatures now accept an optional `lightSource` parameter:

```kotlin
// v1.1.0 (Inherited global light source only)
NeoButton(onClick = {}) { Text("Click") }

// v1.2.0 (Custom light direction per component supported)
NeoButton(
    onClick = {},
    lightSource = NeoLightSource.TopRight
) {
    Text("Click")
}
```

#### 2. Design System Token Migration
If you used hardcoded `.dp` values in custom layouts, switch to `NeoTheme.size` and `NeoTheme.icons`:

```kotlin
// Recommended token usage:
val controlHeight = NeoTheme.size.controlMedium // 48.dp
val iconSize = NeoTheme.icons.medium            // 24.dp
val strokeBorder = NeoTheme.size.borderThin     // 1.dp
```

#### 3. New Component Additions
You can now import 10 new components:
- `NeoBottomSheet`
- `NeoDivider`
- `NeoTooltip`
- `NeoSpeedDial`
- `NeoDatePicker`
- `NeoTimePicker`
- `NeoRangeSlider`
- `NeoNumberStepper`
- `NeoAvatar`
- `NeoAudioPlayerBar`
