# Getting Started with NeoCompose

---

## Installation

Add the library dependency to your module `build.gradle.kts`:

```kotlin
dependencies {
    implementation("prasad.vennam.neo:components:1.0.0")
    implementation("prasad.vennam.neo:theme:1.0.0")
}
```

---

## Basic Setup

1. **Theme Provider**:
   Wrap your app root composable with `NeoTheme`:

   ```kotlin
   NeoTheme {
       Surface(color = NeoTheme.colors.background) {
           MainScreen()
       }
   }
   ```

2. **Customizing Light Source & Theme**:

   ```kotlin
   NeoTheme(
       colors = NeoColors.defaultLightColors(),
       lighting = NeoLighting(
           lightSource = NeoLightSource.TopLeft,
           intensity = 0.8f
       )
   ) {
       // Content
   }
   ```
