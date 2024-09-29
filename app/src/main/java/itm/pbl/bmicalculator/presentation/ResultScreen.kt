package itm.pbl.bmicalculator.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import itm.pbl.bmicalculator.R
import itm.pbl.bmicalculator.navigation.HeightWeightScreenRoute
import itm.pbl.bmicalculator.ui.theme.PrimaryBlue
import itm.pbl.bmicalculator.ui.theme.PrimaryPink

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ResultScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    data: HeightWeightScreenRoute,
    navController: NavController
) {

    val buttonColor = if (data.resId == R.drawable.men) PrimaryBlue else PrimaryPink

    Column(modifier = Modifier.fillMaxSize().background(buttonColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 50.dp),
            text = "Your BMI is",
            style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
        )
        Image(
            painter = painterResource(id = data.resId),
            contentDescription = null,
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = data.resId),
                    animatedVisibilityScope = animatedVisibilityScope
                )
                .size(400.dp)
        )
        Text(text = data.height.toString())


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
                colors = ButtonDefaults.buttonColors(Color.White.copy(0.5f)),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null
                )
            }

            Button(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                onClick = { }
            ) {
                Text(
                    modifier = Modifier,
                    text = "Share",
                    style = TextStyle(
                        fontSize = 27.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = buttonColor
                    )
                )
            }
        }

    }
}