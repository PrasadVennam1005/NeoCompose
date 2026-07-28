#!/usr/bin/env python3
import argparse
import colorsys
import sys

def hex_to_rgb(hex_str):
    hex_str = hex_str.lstrip('#')
    if len(hex_str) == 3:
        hex_str = ''.join([c*2 for c in hex_str])
    try:
        return [int(hex_str[i:i+2], 16) / 255.0 for i in (0, 2, 4)]
    except ValueError:
        print(f"Error: Invalid hex color format '{hex_str}'", file=sys.stderr)
        sys.exit(1)

def rgb_to_hex(r, g, b):
    return f"#{int(round(r * 255)):02X}{int(round(g * 255)):02X}{int(round(b * 255)):02X}"

def generate_palette(base_hex):
    r, g, b = hex_to_rgb(base_hex)
    h, l, s = colorsys.rgb_to_hls(r, g, b)

    # 1. Background is the base color itself
    bg_hex = rgb_to_hex(r, g, b)

    # Determine if the base color is light or dark
    is_light = l > 0.5

    # 2. Light Shadow (Highlight)
    # For light themes, we want it near pure white. For dark themes, a significantly lighter shade.
    if is_light:
        l_light = min(1.0, l + (1.0 - l) * 0.75)
        # Add slight desaturation for the highlight
        s_light = max(0.0, s * 0.8)
    else:
        l_light = min(1.0, l + (1.0 - l) * 0.3)
        s_light = s
    
    r_l, g_l, b_l = colorsys.hls_to_rgb(h, l_light, s_light)
    light_shadow_hex = rgb_to_hex(r_l, g_l, b_l)

    # 3. Dark Shadow
    # Lower the lightness.
    if is_light:
        l_dark = max(0.0, l - 0.15)
        # Slightly boost saturation to make the shadow richer
        s_dark = min(1.0, s * 1.1)
    else:
        l_dark = max(0.0, l - 0.08)
        s_dark = min(1.0, s * 1.2)

    r_d, g_d, b_d = colorsys.hls_to_rgb(h, l_dark, s_dark)
    dark_shadow_hex = rgb_to_hex(r_d, g_d, b_d)

    # 4. High-Contrast Border
    # For accessibility modes, we want a very thin outline.
    if is_light:
        l_border = max(0.0, l - 0.25)
    else:
        l_border = min(1.0, l + 0.25)
    r_b, g_b, b_b = colorsys.hls_to_rgb(h, l_border, s)
    border_hex = rgb_to_hex(r_b, g_b, b_b)

    # 5. Text Primary & Secondary
    if is_light:
        text_primary = "#1E293B"   # Slate 800
        text_secondary = "#64748B" # Slate 500
    else:
        text_primary = "#F8FAFC"   # Slate 50
        text_secondary = "#94A3B8" # Slate 400

    # 6. Primary Accent (Complementary or modern Electric Blue)
    # Rotate Hue by 180 degrees to find the complement, or use a default vibrant blue if hue is low saturation.
    if s < 0.1:
        # Gray scale base, default to electric blue
        accent_hex = "#007AFF"
    else:
        h_accent = (h + 0.5) % 1.0
        r_a, g_a, b_a = colorsys.hls_to_rgb(h_accent, 0.5, 0.9)
        accent_hex = rgb_to_hex(r_a, g_a, b_a)

    return {
        "background": bg_hex,
        "light_shadow": light_shadow_hex,
        "dark_shadow": dark_shadow_hex,
        "accent": accent_hex,
        "border": border_hex,
        "text_primary": text_primary,
        "text_secondary": text_secondary,
        "is_light": is_light
    }

def print_kotlin_code(palette):
    def to_compose_color(hex_str):
        return f"Color(0xFF{hex_str.lstrip('#')})"

    print("\n// Copy-paste this declaration into your Compose theme file:")
    print("val NeoCustomColors = NeoColors(")
    print(f"    primary = {to_compose_color(palette['accent'])},")
    print(f"    background = {to_compose_color(palette['background'])},")
    print(f"    lightShadow = {to_compose_color(palette['light_shadow'])},")
    print(f"    darkShadow = {to_compose_color(palette['dark_shadow'])},")
    print(f"    border = {to_compose_color(palette['border'])},")
    print(f"    textPrimary = {to_compose_color(palette['text_primary'])},")
    print(f"    textSecondary = {to_compose_color(palette['text_secondary'])},")
    print(f"    isLight = {str(palette['is_light']).lower()}")
    print(")")

def main():
    parser = argparse.ArgumentParser(
        description="Neumorphic Palette Generator for NeoCompose. "
                    "Calculates soft background, light shadow highlight, dark shadow, "
                    "and accessibility colors based on a single brand base color."
    )
    parser.add_argument(
        "base_color",
        help="Base background hex color (e.g. #ECF0F3 or #2E3238)"
    )
    args = parser.parse_args()

    palette = generate_palette(args.base_color)

    print(f"=== Neumorphic Palette for {args.base_color} ===")
    print(f"Theme Type:       {'Light Theme' if palette['is_light'] else 'Dark Theme'}")
    print(f"Background:       {palette['background']}")
    print(f"Light Highlight:  {palette['light_shadow']}")
    print(f"Dark Shadow:      {palette['dark_shadow']}")
    print(f"Contrast Border:  {palette['border']}")
    print(f"Primary Accent:   {palette['accent']}")
    print(f"Text Primary:     {palette['text_primary']}")
    print(f"Text Secondary:   {palette['text_secondary']}")

    print_kotlin_code(palette)

if __name__ == "__main__":
    main()
