package itm.pbl.bmicalculator.presentation.heightweightscreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import itm.pbl.bmicalculator.data.HeightWeightScreenRoute
import itm.pbl.bmicalculator.presentation.viewmodel.BmiViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HeightAndWeightColumn(
    genderRoute: HeightWeightScreenRoute,
    viewModel: BmiViewModel,
    heightSlider: Float,
    weightSlider: Float,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                HeightTextBox(viewModel)

                Image(
                    painter = painterResource(id = genderRoute.resId),
                    contentDescription = "Gender Image",
                    modifier = Modifier
                        .padding(start = 110.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = genderRoute.resKey),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .fillMaxHeight(heightSlider * 0.4545f)
                        .scale(0.6f + weightSlider, 1f)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f),
                contentAlignment = Alignment.Center
            ) {
                HeightSlider(viewModel, heightSlider)
            }
        }
        WeightDisplay(viewModel)
    }
}
