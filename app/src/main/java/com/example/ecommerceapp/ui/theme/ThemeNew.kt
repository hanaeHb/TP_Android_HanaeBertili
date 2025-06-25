package com.example.ecommerceapp.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.staticCompositionLocalOf


enum class Mode { Light, Calme }

class ThemeState {
    var mode by mutableStateOf(Mode.Light)
}

val LocalThemeState = staticCompositionLocalOf { ThemeState() }


val LightScheme = lightColorScheme(
    primary = Color(0xFF907E36),
    secondary = Color(0xFFE6E6FA),
    tertiary = Color(0xFF1D0057),
    background = Color.White,
    surface = Color.White,
)

val CalmeColorScheme = lightColorScheme(
    primary = Color(0xFF907E36),
    secondary = Color.White,
    tertiary = Color(0xFF1D0057),
    background = Color(0xFFE6E6FA),
    surface = Color(0xFFE6E6FA),
)


@Composable
fun EcommerceAPPTheme(
    themeState: ThemeState = remember { ThemeState() },
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeState.mode) {
        Mode.Light -> LightScheme
        Mode.Calme -> CalmeColorScheme
    }

    CompositionLocalProvider(LocalThemeState provides themeState) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
