package itm.pbl.bmicalculator.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import itm.pbl.bmicalculator.R
import itm.pbl.bmicalculator.navigation.ResultScreenRoute
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


    Column(
        modifier = Modifier
            .background(buttonColor)
            .padding(innerPaddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CustomText(text = "Your BMI is")

        Image(
            painter = painterResource(id = data.resId),
            contentDescription = null,
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = data.resKey),
                    animatedVisibilityScope = animatedVisibilityScope
                )
                .size(400.dp)
        )
        Text(text = String.format(Locale.US, "%.2f", data.bmiScore))
        Text(text = data.bmiResult)

        Spacer(modifier = Modifier.weight(1f))
        BottomButtons(
            text = "Share",
            color = buttonColor,
            onClickIcon = { navController.popBackStack() },
            onClickBtn = { }
        )
    }
}