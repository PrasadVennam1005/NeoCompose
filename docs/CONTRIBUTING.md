# Contributing to NeoCompose

Thank you for your interest in contributing to NeoCompose!

---

## Code Style & Conventions

- Follow official Kotlin coding conventions.
- All public APIs must have full KDoc documentation.
- Maintain small, focused files (under ~300 lines).
- Keep public API surface minimal and stable using `@Stable` and `@Immutable`.

---

## Submitting Pull Requests

1. Fork the repository and create a feature branch.
2. Ensure unit tests pass across all modules: `./gradlew test`
3. Verify formatting and linting: `./gradlew lint`
4. Submit PR against `main` branch with clear description of architectural impact.
