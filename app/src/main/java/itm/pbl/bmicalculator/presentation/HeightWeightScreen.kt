package itm.pbl.bmicalculator.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import itm.pbl.bmicalculator.R
import itm.pbl.bmicalculator.navigation.HeightWeightScreenRoute
import itm.pbl.bmicalculator.ui.theme.PrimaryBlue
import itm.pbl.bmicalculator.ui.theme.PrimaryPink
import kotlin.math.roundToInt

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HeightWeightScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    data: HeightWeightScreenRoute,
    navController: NavController,
    onClick: (HeightWeightScreenRoute) -> Unit
) {
    var heightPos by remember { mutableFloatStateOf(1.7f) }
    var weightPos by remember { mutableFloatStateOf(0.4f) }

    val buttonColor = if (data.resId == R.drawable.men) PrimaryBlue else PrimaryPink

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(buttonColor),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier
                .padding(start = 10.dp, top = 50.dp)
                .align(Alignment.CenterHorizontally),
            text = "Select Height & Weight",
            style = TextStyle(
                fontSize = 27.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        )

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.6f)
                        .padding(start = 50.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 10.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "${(100 * heightPos).toInt()} cm",
                            fontSize = 24.sp,
                            color = Color.White
                        )

                        HorizontalDivider(
                            thickness = 2.dp,
                            color = Color.White
                        )
                    }
                    Image(
                        painter = painterResource(id = data.resId),
                        contentDescription = null,
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = data.resId),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            .fillMaxHeight(heightPos * 0.4545f)
                            .scale(0.6f + weightPos, 1f)
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
                        value = heightPos,
                        onValueChange = {
                            if (it in 1.2f..2.2f) {
                                heightPos = it
                            }
                        },
                        modifier = Modifier
                            .padding(top = 25.dp)
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
//                        valueRange = 1.2f..2.2f,
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
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp),
                    textAlign = TextAlign.Center,
                    text = "${(200 * weightPos).roundToInt()}KG",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                )
            }

        }
        Slider(
            modifier = Modifier.padding(25.dp),
            value = weightPos,
            valueRange = 0.15f..0.75f,
            onValueChange = { weightPos = it },
            colors = SliderDefaults.colors(
                Color.White,
                Color.White.copy(0.5f),
                Color.White.copy(0.5f),
                Color.White.copy(0.5f)
            )
        )

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
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }

            Button(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                onClick = {
                    onClick(
                        HeightWeightScreenRoute(
                            resId = myResId,
                            resKey = myResKey,
                            height = 0,
                            weight = 0
                        )
                    )
                }
            ) {
                Text(
                    modifier = Modifier,
                    text = "Next",
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

