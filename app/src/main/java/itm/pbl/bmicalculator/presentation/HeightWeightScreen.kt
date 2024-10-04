package itm.pbl.bmicalculator.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import itm.pbl.bmicalculator.R
import itm.pbl.bmicalculator.data.HeightWeightScreenRoute
import itm.pbl.bmicalculator.data.ResultScreenRoute
import itm.pbl.bmicalculator.ui.theme.PrimaryBlue
import itm.pbl.bmicalculator.ui.theme.PrimaryPink
import itm.pbl.bmicalculator.utils.BottomButtons
import itm.pbl.bmicalculator.utils.CustomText

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HeightWeightScreen(
    innerPaddingValues: PaddingValues,
    animatedVisibilityScope: AnimatedVisibilityScope,
    data: HeightWeightScreenRoute,
    navController: NavController,
    viewModel: BmiViewModel = viewModel(),
    onClick: (ResultScreenRoute) -> Unit
) {

    val heightSlider = viewModel.heightSlider.value
    val weightSlider = viewModel.weightSlider.value

    val buttonColor = if (data.resId == R.drawable.men) PrimaryBlue else PrimaryPink

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(buttonColor)
            .padding(innerPaddingValues),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(text = "Select Height & Weight", modifier = Modifier.padding(10.dp))
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .offset(y = 21.dp)
                            .offset(x = 35.dp),
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
                    Image(
                        painter = painterResource(id = data.resId),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 110.dp)
                            .sharedElement(
                                state = rememberSharedContentState(key = data.resKey),
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
                    Slider(
                        value = heightSlider,
                        onValueChange = {
                            if (it in 1.2f..2.2f) {
                                viewModel.onHeightValueChange(it)
                            }
                        },
                        modifier = Modifier
                            .padding(top = 42.dp)
                            .graphicsLayer(
                                rotationZ = 270f,
                                transformOrigin = TransformOrigin(0f, 0f)
                            )
                            .layout { measurable, constraint ->
                                val placeable = measurable.measure(
                                    Constraints(
                                        minWidth = constraint.minHeight,
                                        maxWidth = constraint.maxHeight,
                                        minHeight = constraint.minWidth,
                                        maxHeight = constraint.maxHeight
                                    )
                                )
                                layout(placeable.height, placeable.width) {
                                    placeable.place(-placeable.width, 0)
                                }
                            },
                        valueRange = 0f..2.2f,

                        colors = SliderDefaults.colors(
                            Color.White,
                            Color.White.copy(0.5f),
                            Color.White.copy(0.5f),
                            Color.White.copy(0.5f)
                        )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.machine),
                    contentDescription = null,
                )

                CustomText(
                    modifier = Modifier.width(150.dp),
                    text = "${viewModel.weight} kg"
                )
            }

        }
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
            color = buttonColor,
            onClickIcon = {navController.popBackStack()},
            onClickBtn = {
                onClick(
                ResultScreenRoute(
                    resId = data.resId,
                    resKey = data.resKey,
                    bmiScore = viewModel.bmiScore(),
                    bmiResult = viewModel.bmiResult()
                )
            )}
        )
    }
}

