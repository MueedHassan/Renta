package com.example.nstp.components.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.nstp.R


@Stable
@Immutable
data class RentaTypography(
    val button: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 18.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_regular)),
    ),
    val displayLarge: TextStyle = TextStyle(
        fontSize = 57.sp,
        lineHeight = 64.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_regular))
    ),
    val displayMedium: TextStyle = TextStyle(
        fontSize = 45.sp,
        lineHeight = 52.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_regular))
    ),
    val displaySmall: TextStyle = TextStyle(
        fontSize = 36.sp,
        lineHeight = 44.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_bold))
    ),
    val headlineLarge: TextStyle = TextStyle(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_regular))
    ),
    val headlineMedium: TextStyle = TextStyle(
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_regular))
    ),
    val headlineSmall: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_regular))
    ),
    val titleLarge: TextStyle = TextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_regular))
    ),
    val titleMedium: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_medium))
    ),
    val titleSmall: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_medium))
    ),
    val labelLarge: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_medium))
    ),
    val labelMedium: TextStyle = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_medium))
    ),
    val labelSmall: TextStyle = TextStyle(
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_medium))
    ),
    val bodyLarge: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_semibold))
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_semibold))
    ),
    val bodySmall: TextStyle = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.gilroy_semibold))
    )
) {
    @Composable
    fun material(): Typography = Typography(
        displayLarge = displayLarge,
        displayMedium = displayMedium,
        displaySmall = displaySmall,
        headlineLarge = headlineLarge,
        headlineMedium = headlineMedium,
        headlineSmall = headlineSmall,
        bodyLarge = bodyLarge,
        bodyMedium = bodyMedium,
        bodySmall = bodySmall,
        titleLarge = titleLarge,
        titleMedium = titleMedium,
        titleSmall = titleSmall,
        labelLarge = labelLarge,
        labelMedium = labelMedium,
        labelSmall = labelSmall,
    )




}