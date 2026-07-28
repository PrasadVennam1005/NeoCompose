# Migration Guide

## Upgrading to v2.0.0 (Compose Multiplatform)

NeoCompose has been rebuilt from the ground up as a **Compose Multiplatform** library. It now supports **Android**, **iOS**, and **Desktop (JVM)** targets.

### 1. Dependency Group Name Changes
Dependencies are now split into Kotlin Multiplatform targets. If you are importing them, make sure to add them to your `commonMain` dependencies block (or individual platform blocks as needed):

```kotlin
// In commonMain.dependencies
implementation("prasad.vennam.neo:core:2.0.0")
implementation("prasad.vennam.neo:foundation:2.0.0")
implementation("prasad.vennam.neo:theme:2.0.0")
implementation("prasad.vennam.neo:animation:2.0.0")
implementation("prasad.vennam.neo:components:2.0.0")
```

### 2. Previews in commonMain
* **Important**: Android-specific `@Preview` annotations from `androidx.compose.ui.tooling.preview` have been removed from the common components. Previews are target-specific and should be declared in target source sets (e.g. `androidMain` or `jvmMain`) or in your sample application module.

### 3. Thread-Safe LruCache
* The shadow-caching LruCache has been replaced with a KMP-compatible thread-safe implementation. There are no API changes for the `neoShadow` modifier, but if you referenced `android.util.LruCache` through custom extensions, you should migrate to the platform-independent `SimpleLruCache`.

---

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
