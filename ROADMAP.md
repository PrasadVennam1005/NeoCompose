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

## Phase 5: Multiplatform & Advanced Motion (Planned) 🔮
- [ ] Compose Multiplatform support for Desktop (JVM) & iOS (Kotlin/Native)
- [ ] Interactive Gyroscope Light Sensor binding (`NeoSensorLightSource`)
- [ ] Glare & Metallic specular highlight shaders
