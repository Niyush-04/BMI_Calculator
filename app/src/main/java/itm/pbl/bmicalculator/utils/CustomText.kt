package itm.pbl.bmicalculator.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import itm.pbl.bmicalculator.R

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    fontSize: TextUnit = 27.sp,
    fontWeight: FontWeight = FontWeight.Bold
) {
    val customFontFamily = FontFamily(
        Font(R.font.customfont, FontWeight.Bold, FontStyle.Normal)
    )
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            fontFamily = customFontFamily,
            fontWeight = fontWeight,
            color = color
        ),
        textAlign = TextAlign.Center
    )
}