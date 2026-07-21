# Changelog

All notable changes to the `NeoCompose` library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.0.0] - 2026-07-21

### Added
- **Multi-Module Architecture**: Modular separation into `:core`, `:foundation`, `:theme`, `:animation`, `:components`, and `:sample`.
- **Core Rendering Engine**:
  - `NeoLightSource` with 2D vector offset calculations ($\theta \in [0^\circ, 360^\circ]$) and standard presets (`TopLeft`, `TopRight`, `BottomLeft`, `BottomRight`).
  - `NeoCanvasRenderer` for GPU-accelerated dual outer drop shadows and soft inner clipped shadows using native `BlurMaskFilter`.
  - `NeoSurfaceGradient` for light-source-oriented linear surface brushes on `Concave` and `Convex` visual styles.
  - `NeoStyle` sealed hierarchy supporting `Raised`, `Pressed`, `Inset`, `Concave`, `Convex`, and `Flat`.
- **Theme System (`NeoTheme`)**:
  - `NeoColors` light mode (`#E0E5EC`) and dark mode (`#24292E`) palettes with automatic highlight/shadow derivations.
  - `NeoLighting`, `NeoElevation`, `NeoShapes`, `NeoSpacing`, and `NeoTypography`.
  - `CompositionLocal` providers for all design system tokens.
- **Foundation Modifiers**:
  - `Modifier.neoShadow`, `Modifier.neoStyle`, and `Modifier.neoBorder`.
  - `rememberNeoInteractionStyle` reactive interaction bridge.
- **Animation Engine**:
  - `animateNeoElevationAsState`, `animateNeoElevationForStyleAsState`, and `animateNeoLightSourceAsState`.
  - `NeoAnimationSpec` with spring & tween specs.
- **Production Components (14 Components)**:
  - `NeoSurface`, `NeoButton`, `NeoCard`, `NeoIconButton`, `NeoTextField`, `NeoCheckbox`, `NeoRadioButton`, `NeoSwitch`, `NeoSegmentedControl`, `NeoTabBar`, `NeoChip`, `NeoBadge`, `NeoLinearProgressIndicator`, `NeoCircularProgressIndicator`, and `NeoFAB`.
- **Publishing Infrastructure**:
  - `maven-publish` plugin configured across `:core`, `:foundation`, `:theme`, `:animation`, and `:components` with sources JAR generation and POM metadata (`prasad.vennam.neo`).
- **Sample Showcase App**:
  - Neumorphism Studio with live light vector angle, elevation depth, and light/dark theme controls showcasing all 14 components.
