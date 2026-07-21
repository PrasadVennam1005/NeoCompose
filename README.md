# NeoCompose 🎨

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-Material3-purple.svg)](https://developer.android.com/jetpack/compose)

A modern, high-performance, plug & play **Neumorphism Design System Library** built from the ground up for **Jetpack Compose** and **Material 3**.

---

## Features ✨

- 🚀 **Plug & Play**: Works out of the box with Material 3 applications.
- ⚡ **GPU-Accelerated Rendering**: Custom `Canvas` dual shadow engine without nested layout nodes.
- 🎨 **6 Neumorphism Surface Styles**: Raised, Pressed, Inset, Concave, Convex, and Flat.
- 🧩 **14 Material 3 Components**: `NeoSurface`, `NeoButton`, `NeoCard`, `NeoIconButton`, `NeoTextField`, `NeoCheckbox`, `NeoRadioButton`, `NeoSwitch`, `NeoSegmentedControl`, `NeoTabBar`, `NeoChip`, `NeoBadge`, `NeoProgress`, and `NeoFAB`.
- 💡 **Dynamic Light Engine**: Customize light vector angle ($0^\circ..360^\circ$), distance, and shadow softness globally or per component.
- 🌙 **Light & Dark Theme Native**: Intelligent contrast algorithms for realistic highlight and shadow calculations in both modes.
- 📦 **Multi-Module Architecture**: Scalable separation between core graphics, low-level modifiers, design tokens, animations, and UI components.
- 🌐 **Maven Central Ready**: Configured with `maven-publish` sources and POM metadata.

---

## Architecture 🏛️

```
NeoCompose
├── :core        -> Light vector math, GPU Canvas dual shadow & surface gradient engine
├── :foundation  -> Low-level Compose modifiers (neoShadow, neoStyle, neoBorder) & shapes
├── :theme       -> NeoTheme, NeoColors, NeoLighting, NeoElevation, NeoShapes, NeoTypography, CompositionLocals
├── :animation   -> Press depth transitions, spring/tween specs, dynamic light trackers
├── :components  -> NeoSurface, NeoButton, NeoCard, NeoIconButton, NeoTextField, NeoCheckbox, NeoRadioButton, NeoSwitch, NeoSegmentedControl, NeoTabBar, NeoChip, NeoBadge, NeoProgress, NeoFAB
└── :sample      -> Showcase catalog app & interactive Neumorphic Design Studio
```

---

## Quick Start 🚀

```kotlin
// 1. Add dependency to build.gradle.kts
dependencies {
    implementation("prasad.vennam.neo:components:1.0.0")
    implementation("prasad.vennam.neo:theme:1.0.0")
}

// 2. Wrap your content in NeoTheme
NeoTheme {
    NeoButton(
        onClick = { /* perform action */ },
        style = NeoButtonStyle.Raised
    ) {
        Text("Continue")
    }
}
```

For setup instructions and Gradle setup, check [docs/GETTING_STARTED.md](file:///Users/prasadvennam/AndroidStudioProjects/ComposeComponentsPlugNPlay/docs/GETTING_STARTED.md).

---

## Documentation 📚

- 📘 [Getting Started](file:///Users/prasadvennam/AndroidStudioProjects/ComposeComponentsPlugNPlay/docs/GETTING_STARTED.md)
- 🏗️ [Architecture Guide](file:///Users/prasadvennam/AndroidStudioProjects/ComposeComponentsPlugNPlay/docs/ARCHITECTURE.md)
- 🤝 [Contributing Guidelines](file:///Users/prasadvennam/AndroidStudioProjects/ComposeComponentsPlugNPlay/docs/CONTRIBUTING.md)
- 📜 [Changelog](file:///Users/prasadvennam/AndroidStudioProjects/ComposeComponentsPlugNPlay/docs/CHANGELOG.md)
- ⚡ [Performance Guide](file:///Users/prasadvennam/AndroidStudioProjects/ComposeComponentsPlugNPlay/docs/PERFORMANCE.md)

---

## License 📄

```
Copyright 2026 Prasad Vennam

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
