# NeoCompose Development Roadmap

This document outlines planned features, upcoming components, and long-term architectural goals for the `NeoCompose` library.

---

## Short-Term (v1.1.0)
- [ ] **Extended Components**:
  - `NeoRadioButton` & `NeoRadioGroup`
  - `NeoRangeSlider` with dual thumbs
  - `NeoSegmentedControl`
  - `NeoBadge` & `NeoChip`
  - `NeoBottomNavigation` & `NeoTabBar`
- [ ] **Additional Shadow Engines**:
  - Support for `RenderEffect.createBlurEffect` on Android 12+ (API 31+) with fallback to `BlurMaskFilter`.

---

## Medium-Term (v1.2.0)
- [ ] **Macrobenchmarking Suite**:
  - Baseline Profiles generation for cold startup and smooth 120Hz scroll performance.
  - Automated Compose UI screenshot regression testing using `Paparazzi` / `Roborazzi`.

---

## Long-Term (v2.0.0)
- [ ] **Kotlin Multiplatform (KMP) Support**:
  - Migrate `:core`, `:foundation`, and `:theme` modules to KMP (`commonMain`, `desktopMain`, `iosMain`, `androidMain`).
  - Native Canvas shadow rendering on iOS (Skia / Metal) and Desktop (Skia).
