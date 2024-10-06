package itm.pbl.bmicalculator.presentation.resultscreen

import android.content.Intent
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import itm.pbl.bmicalculator.R
import itm.pbl.bmicalculator.data.ResultScreenRoute
import itm.pbl.bmicalculator.ui.theme.Blue
import itm.pbl.bmicalculator.ui.theme.Green
import itm.pbl.bmicalculator.ui.theme.Orange
import itm.pbl.bmicalculator.ui.theme.PrimaryBlue
import itm.pbl.bmicalculator.ui.theme.PrimaryPink
import itm.pbl.bmicalculator.ui.theme.Red
import itm.pbl.bmicalculator.ui.theme.Yellow
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

    val bmi = String.format(Locale.US, "%.2f", data.bmiScore)

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

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "I just calculated my BMI, and it’s $bmi based on my height (${data.height} cm) and weight (${data.weight} kg).\n" +
                    "That puts me in the '${data.bmiResult}' category.\n" +
                    "\n" +
                    "Have you checked your BMI recently?\n" +
                    "Let’s stay on top of our health together!"
        )
        type = "text/plain"
    }

    val context = LocalContext.current
    val shareIntent = Intent.createChooser(sendIntent, null)

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

            Column(
                modifier = Modifier.fillMaxSize(),
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
                            .fillMaxHeight()
                            .padding(10.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        buttonColor,
                                        buttonColor,
                                    )
                                )
                            )
                            .border(
                                border = BorderStroke(
                                    5.dp,
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            buttonColor,
                                            Color.White.copy(0.8f)
                                        )
                                    )
                                ),
                                shape = RoundedCornerShape(25.dp)
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom

                    ) {

                        CustomText(
                            text = bmi,
                            fontSize = 50.sp
                        )
                        GradientSlider(data.bmiScore)

                        CustomText(text = data.bmiResult)
                        BmiInfo(text = "  Below 18.5: Underweight", color = Blue)
                        BmiInfo(text = "  18.5-24.9: Healthy Weight", color = Green)
                        BmiInfo(text = "  25-29.9: Overweight", color = Yellow)
                        BmiInfo(text = "  30-34.5 : Obesity class 1", color = Orange)
                        BmiInfo(text = "  Above 35: Obesity class 2", color = Red)

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }

        BottomButtons(
            text = "Share",
            color = buttonColor,
            onClickIcon = { navController.popBackStack() },
            onClickBtn = {
                context.startActivity(shareIntent)
            }
        )
    }
}


@Composable
fun GradientSlider(pos: Float) {
    var sliderPosition by remember { mutableStateOf(pos) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp, start = 16.dp, end = 16.dp),
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
                            Blue,
                            Green,
                            Yellow,
                            Orange,
                            Red
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        )
    }
}

@Composable
fun BmiInfo(text: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        HorizontalDivider(
            modifier = Modifier.width(10.dp),
            color = color,
            thickness = 10.dp
        )
        CustomText(text = text, fontSize = 15.sp)
    }

}
