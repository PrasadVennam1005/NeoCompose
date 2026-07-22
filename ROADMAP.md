# NeoCompose System Roadmap

## Phase 1: Core Architecture & Rendering Engine (100% Complete) ✅
- [x] Canvas dual shadow rendering (`drawOuterShadows` & `drawInnerShadows`)
- [x] Hardware-accelerated `BlurMaskFilter` support
- [x] `NeoLightSource` 2D polar vector math ($\theta \in [0^\circ, 360^\circ]$)
- [x] Surface shapes (`Raised`, `Pressed`, `Inset`, `Concave`, `Convex`, `Basin`, `Flat`)

## Phase 2: Design System & First 15 Components (100% Complete) ✅
- [x] `NeoTheme`, `NeoColors`, `NeoTypography`, `NeoElevation`, `NeoSpacing`
- [x] Tokenized dimensions (`NeoSize`) and icon scales (`NeoIconTokens`)
- [x] Core 15 components: `NeoSurface`, `NeoButton`, `NeoCard`, `NeoIconButton`, `NeoTextField`, `NeoCheckbox`, `NeoRadioButton`, `NeoSwitch`, `NeoSegmentedControl`, `NeoTabBar`, `NeoChip`, `NeoBadge`, `NeoProgress`, `NeoFAB`, `NeoIcon`
- [x] Complete Compose `@Preview` support for all components

## Phase 3: Advanced Form & Media Controls (100% Complete) ✅
- [x] `NeoSearchField` (Pill search bar with clear button)
- [x] `NeoDropdownMenu` (Select box with elevated popup menu)
- [x] `NeoRangeSlider` (Dual-thumb min/max range slider)
- [x] `NeoNumberStepper` (Counter `[−] 0 [+]` control)
- [x] `NeoAvatar` (Profile image with online indicator dot)
- [x] `NeoAudioPlayerBar` (Media player bar with Basin play button)
- [x] `NeoBanner` (Notification toast alert card)
- [x] `NeoDialog` (Modal alert dialog container)

## Phase 4: Sheets, Modals, Dividers & Pickers (100% Complete) ✅
- [x] `NeoBottomSheet` (Sliding modal bottom sheet drawer with top drag handle)
- [x] `NeoTooltip` (Speech bubble tooltip popover box)
- [x] `NeoDivider` (Horizontal & vertical groove line separator)
- [x] `NeoSpeedDial` (Expandable FAB revealing sub-action buttons)
- [x] `NeoDatePicker` (Calendar month grid day selector)
- [x] `NeoTimePicker` (`[HH] : [MM] [AM/PM]` hour/minute picker)

## Milestone 1: Accessibility & M3 Interoperability (v1.1.0) 🎯
- [x] **High-Contrast Accessibility Mode**: Auto-contrast shadow/border adjustments matching WCAG AA guidelines when high-contrast system settings are active.
- [x] **Material 3 Interop Modifier**: `Modifier.neoInteraction()` utility to easily drape standard M3 components in Neumorphic shadows.
- [x] **Previews parameterization**: Standardized Preview Parameter Providers for seamless light/dark/variant previews in Android Studio.

## Milestone 2: Performance Tuning & Shadow Caching (v1.2.0) ⚡
- [x] **Hardware-Accelerated Shadow Caching**: Offload path blur calculations to RenderNodes/bitmaps to prevent redraws on recomposition.
- [x] **Scroll Performance Optimization**: Zero-allocation layout passes inside LazyLists to guarantee 120fps scrolling.

## Milestone 3: Tactile Feedback & Sensors (v1.3.0) 📳
- [x] **Tactile Haptic Feedback**: Click-release tactile vibration feedback via `LocalHapticFeedback` on button press and slider step changes.
- [x] **Gyroscope Lighting Binder**: Optional sensor binding (`NeoSensorLightSource`) to automatically shift component light vectors based on phone tilt.

## Milestone 4: Specular Shaders & Compose Multiplatform (v2.0.0) 🌐
- [ ] **Compose Multiplatform Support**: Build targets for iOS (Kotlin/Native), Desktop (JVM), and Web (Compose HTML/Canvas).
- [x] **Specular Highlights (AGSL Shaders)**: Interactive high-fidelity gloss effects using custom fragment shaders.

## Developer Tooling & Ecosystem 🛠
- [ ] **Neumorphic Palette Generator**: CLI/Web tool to compute mathematically sound surface, shadow, and text colors based on a single brand base color.
- [ ] **Figma variables sync**: Script to convert exported Figma variable tokens directly into Compose `NeoTheme` values.
