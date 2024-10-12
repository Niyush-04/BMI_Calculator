package itm.pbl.bmicalculator.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BottomButtons(
    imageVector: ImageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
    text: String = "Next",
    color: Color,
    onClickIcon: () -> Unit,
    onClickBtn: () -> Unit,
    enable: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (enable) {
            Icon(
                modifier = Modifier
                    .size(60.dp)
                    .background(color = Color.White.copy(0.3f), shape = CircleShape)
                    .clickable(onClick = onClickIcon),
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = "Navigate Back",
                tint = Color.White
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(if (enable) 0.8f else 1f),
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = onClickBtn
        ) {
            Spacer(modifier = Modifier.weight(0.6f))
            CustomText(text = text, color = color)
            Spacer(modifier = Modifier.weight(0.4f))
            Icon(
                modifier = Modifier.size(45.dp),
                imageVector = imageVector,
                contentDescription = "Navigation Forward",
                tint = color
            )
        }
    }
}