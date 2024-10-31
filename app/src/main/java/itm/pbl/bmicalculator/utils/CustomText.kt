package itm.pbl.bmicalculator.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
    fontSize: TextUnit = 32.sp,
    fontWeight: FontWeight = FontWeight.Normal
) {
    val nunitoFontFamily = FontFamily(
        Font(R.font.nunito_black, FontWeight.Black),
        Font(R.font.nunito_bold, FontWeight.Bold),
        Font(R.font.nunito_extra_bold, FontWeight.ExtraBold),
        Font(R.font.nunito_regular, FontWeight.Normal),
        Font(R.font.nunito_semi_bold, FontWeight.SemiBold)
    )
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            fontFamily = nunitoFontFamily,
            fontWeight = fontWeight,
            color = color
        ),
        textAlign = TextAlign.Center
    )
}