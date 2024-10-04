package itm.pbl.bmicalculator.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import itm.pbl.bmicalculator.R
import itm.pbl.bmicalculator.data.ResultScreenRoute
import itm.pbl.bmicalculator.ui.theme.PrimaryBlue
import itm.pbl.bmicalculator.ui.theme.PrimaryPink
import itm.pbl.bmicalculator.utils.BottomButtons
import itm.pbl.bmicalculator.utils.CustomText
import java.util.Locale

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ResultScreen(
    innerPaddingValues: PaddingValues,
    animatedVisibilityScope: AnimatedVisibilityScope,
    data: ResultScreenRoute,
    navController: NavController
) {

    val buttonColor = if (data.resId == R.drawable.men) PrimaryBlue else PrimaryPink

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            resId = R.raw.scan
        )
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        speed = 3f,
        iterations = 1
    )

    val imgScale by animateFloatAsState(
        animationSpec = tween(500),
        targetValue = if (progress == 1f) 1.2f else 1f,
        label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(buttonColor)
            .padding(innerPaddingValues),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(text = "Your BMI is", modifier = Modifier.padding(10.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = data.resId),
                contentDescription = null,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = data.resKey),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .fillMaxSize()
                    .padding(80.dp)
                    .scale(imgScale)
            )

            if (progress != 1f) {
                LottieAnimation(
                    modifier = Modifier.fillMaxSize(),
                    composition = composition,
                    progress = { progress }
                )
            }

            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AnimatedVisibility(
                    visible = progress == 1f,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                    exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly

                    ) {

                        CustomText(
                            text = String.format(Locale.US, "%.2f", data.bmiScore),
                            color = Color.Black
                        )
                        GradientSlider(data.bmiScore)

                        CustomText(text = data.bmiResult, color = Color.Black)

                    }
                }
            }
        }

        BottomButtons(
            text = "Share",
            color = buttonColor,
            onClickIcon = { navController.popBackStack() },
            onClickBtn = { }
        )
    }
}


@Composable
fun GradientSlider(pos: Float) {
    var sliderPosition by remember { mutableStateOf(pos) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                inactiveTrackColor = Color.Transparent,
                activeTrackColor = Color.Transparent,
                disabledThumbColor = Color.White,
                disabledActiveTrackColor = Color.Transparent,
                disabledInactiveTrackColor = Color.Transparent
            ),
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Red,
                            Color.Yellow,
                            Color.Green,
                            Color.Blue
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        )
    }
}
