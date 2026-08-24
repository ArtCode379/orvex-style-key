package orvexretail.clothing.orvexstylekey.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val OrvexColorScheme = lightColorScheme(
    primary = OrvexPlum,
    onPrimary = OrvexSurface,
    primaryContainer = OrvexChip,
    onPrimaryContainer = OrvexChipText,
    secondary = OrvexTeal,
    onSecondary = OrvexSurface,
    tertiary = OrvexRose,
    background = OrvexIvory,
    onBackground = OrvexInk,
    surface = OrvexSurface,
    onSurface = OrvexInk,
    onSurfaceVariant = OrvexMuted,
    outline = OrvexBorder,
    error = OrvexWarning
)

@Composable
fun ProductAppZLFJRTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = OrvexColorScheme,
        typography = AppTypography,
        content = content
    )
}
