package itm.pbl.bmicalculator.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
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
    imageVector: ImageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
    text: String = "Next",
    color: Color,
    onClickIcon: () -> Unit,
    onClickBtn: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            modifier = Modifier.weight(0.2f),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(Color.White.copy(0.5f)),
            onClick = onClickIcon
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = imageVector,
                contentDescription = "Navigate Back"
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            modifier = Modifier.weight(0.8f),
            colors = ButtonDefaults.buttonColors(Color.White),
            onClick = onClickBtn
        ) {
            CustomText(text = text, color = color)
        }
    }
}