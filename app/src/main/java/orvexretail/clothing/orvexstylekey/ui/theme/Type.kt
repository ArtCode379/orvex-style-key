package orvexretail.clothing.orvexstylekey.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val EditorialHeading = FontFamily.Serif
private val EditorialBody = FontFamily.SansSerif

val AppTypography = Typography(
    displayLarge = TextStyle(fontFamily = EditorialHeading, fontWeight = FontWeight.SemiBold, fontSize = 40.sp, lineHeight = 44.sp),
    headlineLarge = TextStyle(fontFamily = EditorialHeading, fontWeight = FontWeight.SemiBold, fontSize = 32.sp, lineHeight = 38.sp),
    headlineMedium = TextStyle(fontFamily = EditorialHeading, fontWeight = FontWeight.SemiBold, fontSize = 26.sp, lineHeight = 32.sp),
    titleLarge = TextStyle(fontFamily = EditorialHeading, fontWeight = FontWeight.SemiBold, fontSize = 22.sp, lineHeight = 28.sp),
    titleMedium = TextStyle(fontFamily = EditorialBody, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, lineHeight = 22.sp),
    bodyLarge = TextStyle(fontFamily = EditorialBody, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = EditorialBody, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 21.sp),
    labelLarge = TextStyle(fontFamily = EditorialBody, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, letterSpacing = 0.4.sp)
)

val Typography = AppTypography
