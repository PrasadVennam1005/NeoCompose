# Changelog

All notable changes to the **NeoCompose** library will be documented in this file.

## [2.0.0-alpha01] - 2026-07-22

### Added
- **Specular Highlights (AGSL Shaders)**: High-fidelity Blinn-Phong 3D specular glare highlights on surfaces (`Modifier.neoSpecular`). Uses AGSL RuntimeShader on Android 13+ and radial gradient white highlights on older APIs.
- **specularHighlight Parameter**: Integrated specular gloss options across `NeoButton`, `NeoCard`, and `NeoSurface` containers.

---

## [1.3.0] - 2026-07-22

### Added
- **High-Contrast Accessibility Mode**: WCAG AA compliant mode setting (`isHighContrast = true`) that deepens shadow contrast and draws `1.dp` high-contrast borders around interactive elements.
- **Material 3 Interoperability (`Modifier.neoInteraction`)**: Clickable interaction wrapper translating focused, hovered, and pressed actions into Neumorphic Raised/Pressed transitions on M3 buttons/cards.
- **Hardware-Accelerated Shadow Caching**: Thread-safe `LruCache` shadow renderer caching pre-rendered blurred bitmaps to bypass CPU blurs, achieving **120fps scrolling**.
- **Stepped Tactile Feedback**: Integrated micro-vibration ticks (`HapticFeedbackType.TextHandleMove`) on every 5% interval boundary of sliders/range sliders, and click haptics on buttons.
- **Dynamic Sensor-Driven Lighting (`rememberSensorLightSource`)**: Accelerometer light vector binder that smooths shadow rotations in response to physical tilt.
- **Previews Parameterization**: `NeoPreviewParamsProvider` providing 6 common preview setups in Android Studio.

---

## [1.2.0] - 2026-07-21

### Added
- **10 New Neumorphic Components**:
  - `NeoBottomSheet`: Sliding modal bottom sheet drawer with top drag handle pill.
  - `NeoDivider`: Horizontal & vertical groove line separator (`Inset` / `Raised`).
  - `NeoTooltip`: Speech bubble tooltip popover box.
  - `NeoSpeedDial`: Expandable FAB revealing sub-action buttons.
  - `NeoDatePicker`: Calendar day grid picker.
  - `NeoTimePicker`: `[HH] : [MM] [AM/PM]` time selector.
  - `NeoRangeSlider`: Dual-thumb min/max range selector.
  - `NeoNumberStepper`: Counter `[−] 0 [+]` stepper control.
  - `NeoAvatar`: Profile avatar container with active online status indicator.
  - `NeoAudioPlayerBar`: Media player bar with Play/Pause basin button & scrub track.
- **Compose Previews (@Preview)**: Added `@Preview` functions across all 31 components covering Light and Dark themes.
- **Directional Light Control**: Added `lightSource: NeoLightSource = NeoTheme.lighting.lightSource` parameter to all component signatures.
- **Basin Dual Shadow Support**: `NeoStyle.Basin` renders both outer dual drop shadows and inner soft clipped shadows.

### Changed
- **NeoSwitch**: Corrected track default style to `NeoStyle.Inset` with smooth thumb color transitions.

---

## [1.1.0] - 2026-07-21

### Added
- Tokenized design system values (`NeoTheme.size` and `NeoTheme.icons`).
- `NeoStyle.Basin` visual surface style.
- Directional light aliases (`LEFT_TOP`, `LEFT_BOTTOM`, `RIGHT_TOP`, `RIGHT_BOTTOM`).

---

## [1.0.0] - 2026-07-21

### Added
- Initial release of **NeoCompose** Neumorphism design system library for Jetpack Compose.
