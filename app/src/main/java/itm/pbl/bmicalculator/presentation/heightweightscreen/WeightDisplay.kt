package itm.pbl.bmicalculator.presentation.heightweightscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import itm.pbl.bmicalculator.R
import itm.pbl.bmicalculator.presentation.viewmodel.BmiViewModel
import itm.pbl.bmicalculator.utils.CustomText

@Composable
fun WeightDisplay(viewModel: BmiViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.machine),
            contentDescription = "Weight Machine",
        )

        CustomText(
            modifier = Modifier.width(150.dp),
            text = "${viewModel.weight} kg"
        )
    }
}
