#!/usr/bin/env python3
import argparse
import json
import os
import re
import sys

def parse_val(val):
    """
    Cleans value string from Figma (e.g., '16px', '8dp', '#ffffff')
    and returns numeric value/color hex.
    """
    if isinstance(val, (int, float)):
        return val
    if isinstance(val, str):
        # Check if color hex
        if val.startswith('#'):
            return val
        # Check if pixels/dp
        match = re.match(r'^([\d\.]+)(px|dp|rem)?$', val)
        if match:
            return float(match.group(1))
    return val

def flatten_dict(d, parent_key='', sep='.'):
    """
    Flattens a nested dictionary.
    """
    items = []
    for k, v in d.items():
        new_key = f"{parent_key}{sep}{k}" if parent_key else k
        if isinstance(v, dict):
            # Check if this is a leaf node containing value
            if '$value' in v:
                items.append((new_key, parse_val(v['$value'])))
            elif 'value' in v:
                items.append((new_key, parse_val(v['value'])))
            else:
                items.extend(flatten_dict(v, new_key, sep=sep).items())
        else:
            items.append((new_key, parse_val(v)))
    return dict(items)

def generate_size_class(sizes):
    # Standard sizes mapping
    # Defaults in case not present in Figma
    mapping = {
        "borderThin": sizes.get("border.thin", sizes.get("borderThin", 1.0)),
        "borderMedium": sizes.get("border.medium", sizes.get("borderMedium", 1.5)),
        "borderThick": sizes.get("border.thick", sizes.get("borderThick", 2.0)),
        "controlSmall": sizes.get("control.small", sizes.get("controlSmall", 28.0)),
        "controlMedium": sizes.get("control.medium", sizes.get("controlMedium", 48.0)),
        "controlLarge": sizes.get("control.large", sizes.get("controlLarge", 56.0)),
        "iconSmall": sizes.get("icon.small", sizes.get("iconSmall", 18.0)),
        "iconMedium": sizes.get("icon.medium", sizes.get("iconMedium", 24.0)),
        "trackHeightSlim": sizes.get("track.slim", sizes.get("trackHeightSlim", 12.0)),
        "trackHeightMedium": sizes.get("track.medium", sizes.get("trackHeightMedium", 28.0)),
        "thumbSizeSmall": sizes.get("thumb.small", sizes.get("thumbSizeSmall", 12.0)),
        "thumbSizeMedium": sizes.get("thumb.medium", sizes.get("thumbSizeMedium", 24.0)),
        "thumbSizeLarge": sizes.get("thumb.large", sizes.get("thumbSizeLarge", 26.0)),
    }

    code = f"""package prasad.vennam.neo.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Design system tokens defining standard component dimensions.
 * Auto-generated via figma_sync.py
 */
@Immutable
public data class NeoSize(
    public val borderThin: Dp = {mapping['borderThin']}.dp,
    public val borderMedium: Dp = {mapping['borderMedium']}.dp,
    public val borderThick: Dp = {mapping['borderThick']}.dp,
    public val controlSmall: Dp = {mapping['controlSmall']}.dp,
    public val controlMedium: Dp = {mapping['controlMedium']}.dp,
    public val controlLarge: Dp = {mapping['controlLarge']}.dp,
    public val iconSmall: Dp = {mapping['iconSmall']}.dp,
    public val iconMedium: Dp = {mapping['iconMedium']}.dp,
    public val trackHeightSlim: Dp = {mapping['trackHeightSlim']}.dp,
    public val trackHeightMedium: Dp = {mapping['trackHeightMedium']}.dp,
    public val thumbSizeSmall: Dp = {mapping['thumbSizeSmall']}.dp,
    public val thumbSizeMedium: Dp = {mapping['thumbSizeMedium']}.dp,
    public val thumbSizeLarge: Dp = {mapping['thumbSizeLarge']}.dp,
)
"""
    return code

def generate_spacing_class(spacing):
    mapping = {
        "extraSmall": spacing.get("extraSmall", spacing.get("xs", 4.0)),
        "small": spacing.get("small", spacing.get("s", 8.0)),
        "medium": spacing.get("medium", spacing.get("m", 16.0)),
        "large": spacing.get("large", spacing.get("l", 24.0)),
        "extraLarge": spacing.get("extraLarge", spacing.get("xl", 32.0)),
    }

    code = f"""package prasad.vennam.neo.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Standard spacing tokens for padding, margins, and gaps.
 * Auto-generated via figma_sync.py
 */
@Immutable
public data class NeoSpacing(
    public val extraSmall: Dp = {mapping['extraSmall']}.dp,
    public val small: Dp = {mapping['small']}.dp,
    public val medium: Dp = {mapping['medium']}.dp,
    public val large: Dp = {mapping['large']}.dp,
    public val extraLarge: Dp = {mapping['extraLarge']}.dp,
)
"""
    return code

def clean_color(hex_str):
    if not isinstance(hex_str, str):
        return "Color.Transparent"
    hex_str = hex_str.lstrip('#')
    if len(hex_str) == 3:
        hex_str = ''.join([c*2 for c in hex_str])
    if len(hex_str) == 6:
        return f"Color(0xFF{hex_str.upper()})"
    if len(hex_str) == 8:
        # Move alpha from end to start if figma outputs RRGGBBAA
        return f"Color(0x{hex_str[6:8]}{hex_str[0:6].upper()})"
    return "Color.Transparent"

def main():
    parser = argparse.ArgumentParser(
        description="Figma Variables Synchronization Utility. "
                    "Parses exported Figma variable JSON file and overrides local token classes."
    )
    parser.add_argument(
        "--input",
        required=True,
        help="Path to Figma variables JSON export"
    )
    parser.add_argument(
        "--output-dir",
        help="Target package folder containing NeoSize.kt, NeoSpacing.kt"
    )
    args = parser.parse_args()

    # Determine default output directory
    output_dir = args.output_dir
    if not output_dir:
        # Try to locate theme directory in workspace
        current_dir = os.path.dirname(os.path.abspath(__file__))
        project_root = os.path.dirname(current_dir)
        possible_path = os.path.join(project_root, "theme", "src", "main", "java", "prasad", "vennam", "neo", "theme")
        if os.path.exists(possible_path):
            output_dir = possible_path
        else:
            output_dir = "."

    if not os.path.exists(args.input):
        print(f"Error: Input file '{args.input}' not found.", file=sys.stderr)
        sys.exit(1)

    with open(args.input, 'r') as f:
        try:
            raw_data = json.load(f)
        except json.JSONDecodeError as e:
            print(f"Error: Invalid JSON format. {e}", file=sys.stderr)
            sys.exit(1)

    flat_data = flatten_dict(raw_data)

    # Separate sizes, spacing, colors
    sizes = {}
    spacing = {}
    colors = {}

    for k, v in flat_data.items():
        k_lower = k.lower()
        if "spacing" in k_lower:
            # Extract spacing key suffix
            suffix = k.split('.')[-1]
            spacing[suffix] = v
        elif "color" in k_lower or "theme" in k_lower:
            colors[k] = v
        else:
            sizes[k] = v

    print(f"Parsed {len(sizes)} size tokens, {len(spacing)} spacing tokens, and {len(colors)} color tokens.")

    # Generate and write NeoSize.kt
    size_code = generate_size_class(sizes)
    size_file_path = os.path.join(output_dir, "NeoSize.kt")
    with open(size_file_path, 'w') as f:
        f.write(size_code)
    print(f"Successfully generated and wrote {size_file_path}")

    # Generate and write NeoSpacing.kt
    spacing_code = generate_spacing_class(spacing)
    spacing_file_path = os.path.join(output_dir, "NeoSpacing.kt")
    with open(spacing_file_path, 'w') as f:
        f.write(spacing_code)
    print(f"Successfully generated and wrote {spacing_file_path}")

    print("Sync complete. Note: Manual color token adjustments may be required in NeoColors.kt companion objects.")

if __name__ == "__main__":
    main()
