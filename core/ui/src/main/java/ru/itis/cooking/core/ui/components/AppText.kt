package ru.itis.cooking.core.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import ru.itis.cooking.core.ui.theme.AppFont

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    size: Int,
    color: Color,
    maxLine: Int = 1,
    fontFamily: FontFamily = AppFont
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = size.sp,
        maxLines = maxLine,
        color = color,
        fontFamily = fontFamily,
        overflow = TextOverflow.Ellipsis
    )
}
