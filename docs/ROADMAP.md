# NeoCompose Development Roadmap

This document outlines completed features, upcoming components, and long-term architectural goals for the `NeoCompose` library.

---

## Completed (v1.0.0)
- [x] **Core Rendering Engine**:
  - `NeoLightSource` 2D directional vector calculations ($\theta \in [0^\circ, 360^\circ]$).
  - `NeoCanvasRenderer` dual outer drop shadows and soft inner clipped shadows using native blur mask filters.
  - `NeoSurfaceGradient` directional surface curvature lighting for Concave and Convex visual styles.
- [x] **Full 14-Component Suite**:
  - `NeoSurface`, `NeoButton`, `NeoCard`, `NeoIconButton`, `NeoTextField`, `NeoCheckbox`, `NeoRadioButton`, `NeoSwitch`, `NeoSegmentedControl`, `NeoTabBar`, `NeoChip`, `NeoBadge`, `NeoProgress`, and `NeoFAB`.
- [x] **Theme & Design Tokens**:
  - `NeoColors` light (`#E0E5EC`) and dark (`#24292E`) mode color schemes.
  - `NeoLighting`, `NeoElevation`, `NeoShapes`, `NeoSpacing`, and `NeoTypography`.
- [x] **Maven Central Publishing**:
  - `maven-publish` plugin configured across all 5 library modules (`prasad.vennam.neo`) with sources JAR generation and POM metadata.

---

## Short-Term (v1.1.0)
- [ ] **Extended Components**:
  - `NeoRangeSlider` with dual thumbs
  - `NeoBottomNavigation`
  - `NeoDropdownMenu`
- [ ] **Baseline Profiles & Benchmarking**:
  - Baseline Profiles generation for cold startup and smooth 120Hz scroll performance.
  - Automated Compose UI screenshot regression testing using `Paparazzi` / `Roborazzi`.

---

## Long-Term (v2.0.0)
- [ ] **Kotlin Multiplatform (KMP) Support**:
  - Migrate `:core`, `:foundation`, and `:theme` modules to KMP (`commonMain`, `desktopMain`, `iosMain`, `androidMain`).
  - Native Canvas shadow rendering on iOS (Skia / Metal) and Desktop (Skia).
