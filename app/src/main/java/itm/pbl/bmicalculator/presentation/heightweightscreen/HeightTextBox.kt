package itm.pbl.bmicalculator.presentation.heightweightscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itm.pbl.bmicalculator.presentation.viewmodel.BmiViewModel
import itm.pbl.bmicalculator.utils.CustomText

@Composable
fun HeightTextBox(viewModel: BmiViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 21.dp, x = 35.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomText(
            modifier = Modifier
                .border(
                    border = BorderStroke(2.dp, Color.White),
                    shape = CircleShape
                )
                .padding(7.dp),
            text = "${viewModel.height} cm",
            fontSize = 24.sp
        )

        HorizontalDivider(thickness = 2.dp, color = Color.White)
    }
}
