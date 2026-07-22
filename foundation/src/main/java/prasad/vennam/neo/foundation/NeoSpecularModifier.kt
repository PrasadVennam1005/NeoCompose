package prasad.vennam.neo.foundation

import android.graphics.RenderEffect as AndroidRenderEffect
import android.graphics.RuntimeShader
import android.os.Build
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import prasad.vennam.neo.core.NeoLightSource
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

private const val SPECULAR_SHADER_CODE = """
    uniform shader content;
    uniform float2 size;
    uniform float2 lightPosition;
    uniform float specularIntensity;
    uniform float specularShininess;

    half4 main(float2 coords) {
        half4 color = content.eval(coords);
        
        // Simulating normal vector for a rounded/curved button
        // normal points from center outwards, with z axis pointing towards viewer
        float2 center = size * 0.5;
        float2 normal = (coords - center) / (center + 1.0); // Prevent division by zero
        float distSq = dot(normal, normal);
        
        if (distSq > 1.0) {
            return color; // Outside the curved surface boundary
        }
        
        float z = sqrt(1.0 - distSq);
        float3 normal3 = float3(normal, z);
        
        // Light direction from light position to coords
        float3 lightDir3 = normalize(float3(lightPosition - coords, 150.0));
        
        // View direction is directly up (camera)
        float3 viewDir3 = float3(0.0, 0.0, 1.0);
        
        // Halfway vector (Blinn-Phong)
        float3 halfDir3 = normalize(lightDir3 + viewDir3);
        float spec = pow(max(dot(normal3, halfDir3), 0.0), specularShininess);
        
        half3 specColor = half3(1.0, 1.0, 1.0) * spec * specularIntensity;
        return half4(color.rgb + specColor, color.a);
    }
"""

/**
 * Applies a glossy, hardware-accelerated 3D Specular Highlight to a Neumorphic surface.
 *
 * @param lightSource Directional light source configuration defining the light vector direction.
 * @param intensity Brightness of the specular highlight (0f to 1f).
 * @param shininess Roughness/focus index of the highlight (higher values mean sharper shine).
 * @param enabled Whether the specular shine modifier is active.
 */
public fun Modifier.neoSpecular(
    lightSource: NeoLightSource,
    intensity: Float = 0.2f,
    shininess: Float = 12f,
    enabled: Boolean = true
): Modifier = if (enabled && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    this.graphicsLayer {
        val lightDist = max(size.width, size.height) * 1.5f
        val angleRad = Math.toRadians(lightSource.angleDegrees.toDouble())
        val lightX = (size.width / 2f) + lightDist * cos(angleRad).toFloat()
        val lightY = (size.height / 2f) + lightDist * sin(angleRad).toFloat()

        val shader = RuntimeShader(SPECULAR_SHADER_CODE)
        shader.setFloatUniform("size", size.width, size.height)
        shader.setFloatUniform("lightPosition", lightX, lightY)
        shader.setFloatUniform("specularIntensity", intensity)
        shader.setFloatUniform("specularShininess", shininess)

        renderEffect = AndroidRenderEffect.createRuntimeShaderEffect(shader, "content").asComposeRenderEffect()
    }
} else if (enabled) {
    // Fallback for API < 33: Draw a subtle specular highlight gradient overlay
    this.drawWithCache {
        val angleRad = Math.toRadians(lightSource.angleDegrees.toDouble())
        // Start point is offset in direction of light source angle
        val startOffset = Offset(
            x = (size.width / 2f) - (size.width * 0.35f) * cos(angleRad).toFloat(),
            y = (size.height / 2f) - (size.height * 0.35f) * sin(angleRad).toFloat()
        )

        onDrawWithContent {
            drawContent()
            // Draw a subtle radial gloss highlight at the light direction side
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color.White.copy(alpha = intensity * 0.5f), Color.Transparent),
                    center = startOffset,
                    radius = max(size.width, size.height) * 0.4f
                ),
                radius = max(size.width, size.height) * 0.4f,
                center = startOffset
            )
        }
    }
} else {
    this
}
