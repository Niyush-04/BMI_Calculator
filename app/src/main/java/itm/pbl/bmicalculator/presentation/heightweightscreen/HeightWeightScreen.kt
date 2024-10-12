package itm.pbl.bmicalculator.presentation.heightweightscreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import itm.pbl.bmicalculator.R
import itm.pbl.bmicalculator.data.HeightWeightScreenRoute
import itm.pbl.bmicalculator.data.ResultScreenRoute
import itm.pbl.bmicalculator.presentation.viewmodel.BmiViewModel
import itm.pbl.bmicalculator.ui.theme.PrimaryBlue
import itm.pbl.bmicalculator.ui.theme.PrimaryPink
import itm.pbl.bmicalculator.utils.BottomButtons
import itm.pbl.bmicalculator.utils.CustomText

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HeightWeightScreen(
    innerPaddingValues: PaddingValues,
    animatedVisibilityScope: AnimatedVisibilityScope,
    genderRoute: HeightWeightScreenRoute,
    navController: NavController,
    viewModel: BmiViewModel = viewModel(),
    onClick: (ResultScreenRoute) -> Unit
) {
    val heightSlider = viewModel.heightSlider.value
    val weightSlider = viewModel.weightSlider.value

    val bgColor = if (genderRoute.resId == R.drawable.men) PrimaryBlue else PrimaryPink

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(innerPaddingValues),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(text = "Select Height & Weight")

        HeightAndWeightColumn(
            genderRoute,
            viewModel,
            heightSlider,
            weightSlider,
            animatedVisibilityScope
        )

        Slider(
            modifier = Modifier.padding(25.dp),
            value = weightSlider,
            valueRange = 0.15f..0.75f,
            onValueChange = { viewModel.onWeightValueChange(it) },
            colors = SliderDefaults.colors(
                Color.White,
                Color.White.copy(0.5f),
                Color.White.copy(0.5f),
                Color.White.copy(0.5f)
            )
        )

        BottomButtons(
            color = bgColor,
            onClickIcon = { navController.popBackStack() },
            onClickBtn = {
                onClick(
                    ResultScreenRoute(
                        resId = genderRoute.resId,
                        resKey = genderRoute.resKey,
                        height = viewModel.height,
                        weight = viewModel.weight,
                        bmiScore = viewModel.bmiScore(),
                        bmiResult = viewModel.bmiResult()
                    )
                )
            }
        )
    }
}
