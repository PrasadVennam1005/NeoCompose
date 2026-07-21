# Changelog

All notable changes to the **NeoCompose** library will be documented in this file.

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
