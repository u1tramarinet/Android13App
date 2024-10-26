package io.github.u1tramarinet.android13app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.DeviceFontFamilyName
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import io.github.u1tramarinet.android13app.ui.queryFontFamilyName
import io.github.u1tramarinet.android13app.ui.queryFontFamilyAttrResId

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun Android13AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    Android13BaseAppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
        typography = Typography,
        content = content
    )
}

@Composable
fun AndroidViewInheritTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val fontFamilyResId = context.queryFontFamilyAttrResId()
    val fontFamilyName = context.queryFontFamilyName()
    val fontFamily = if (fontFamilyResId != null) {
        FontFamily(Font(fontFamilyResId))
    } else if (fontFamilyName != null) {
        FontFamily(Font(familyName = DeviceFontFamilyName(name = fontFamilyName)))
    } else {
        null
    }
    val typography = if (fontFamily != null) {
        androidx.compose.material3.Typography(
            displayLarge = Typography.displayLarge.copy(fontFamily = fontFamily),
            displayMedium = Typography.displayMedium.copy(fontFamily = fontFamily),
            displaySmall = Typography.displaySmall.copy(fontFamily = fontFamily),
            headlineLarge = Typography.headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = Typography.headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = Typography.headlineSmall.copy(fontFamily = fontFamily),
            titleLarge = Typography.titleLarge.copy(fontFamily = fontFamily),
            titleMedium = Typography.titleMedium.copy(fontFamily = fontFamily),
            titleSmall = Typography.titleSmall.copy(fontFamily = fontFamily),
            bodyLarge = Typography.bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = Typography.bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = Typography.bodySmall.copy(fontFamily = fontFamily),
        )
    } else {
        Typography
    }

    Android13BaseAppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
        typography = typography,
        content = content
    )
}

@Composable
private fun Android13BaseAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    typography: androidx.compose.material3.Typography,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}