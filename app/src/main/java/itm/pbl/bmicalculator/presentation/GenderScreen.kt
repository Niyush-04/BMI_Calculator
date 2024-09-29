package itm.pbl.bmicalculator.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itm.pbl.bmicalculator.R
import itm.pbl.bmicalculator.navigation.HeightWeightScreenRoute
import itm.pbl.bmicalculator.ui.theme.PrimaryBlue
import itm.pbl.bmicalculator.ui.theme.PrimaryPink
import kotlinx.coroutines.launch

val genders = listOf(
    HeightWeightScreenRoute(R.drawable.men, "Male", 0, 0),
    HeightWeightScreenRoute(R.drawable.women, "Female", 0, 0),
)

// Temporary
var myResId = R.drawable.men
var myResKey = "Male"
var myHeight = 0
var myWeight = 0

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GenderScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: (HeightWeightScreenRoute) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val pager = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { genders.size }
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Crossfade(
            targetState = pager.currentPage,
            label = "",
            animationSpec = tween(500)
        ) { targetState ->

            val imgScale by animateFloatAsState(
                animationSpec = tween(500),
                targetValue = if (pager.isScrollInProgress) 0.4f else 1.1f,
                label = ""
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (targetState == 0) PrimaryBlue else PrimaryPink)
            )
            Box(
                modifier = Modifier
                    .size((270 * imgScale).dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(0.5f))
                    .align(Alignment.Center)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp, top = 50.dp),
                text = "Select Gender",
                style = TextStyle(
                    fontSize = 27.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            )

            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                state = pager,
                contentPadding = PaddingValues(100.dp),
            ) { index ->
                myResId = genders[pager.currentPage].resId
                myResKey = genders[pager.currentPage].resKey

                val imgScale by animateFloatAsState(
                    animationSpec = tween(500),
                    targetValue = if (index == pager.currentPage) 2f else 1f,
                    label = ""
                )
                Image(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(
                                key = genders[index].resId
                            ),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .size(200.dp)
                        .scale(imgScale)
                        .alpha(if (pager.currentPage == index) 1f else 0.5f),
                    painter = painterResource(id = genders[index].resId),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(start =70.dp, end = 70.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                    Icon(
                        modifier = Modifier.size(45.dp).clickable {
                            coroutineScope.launch {
                            pager.scrollToPage(0)
                        }
                        },
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = Color.White
                    )

                Text(
                    text = if (pager.currentPage == 0) "Male" else "Female",
                    style = TextStyle(
                        fontSize = 27.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                )

                    Icon(
                        modifier = Modifier.size(45.dp).clickable {
                            coroutineScope.launch {
                            pager.scrollToPage(1)
                        }
                        },
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.White
                    )
            }

            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                Button(modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp, end = 10.dp, bottom = 30.dp),
                    colors = ButtonDefaults.buttonColors(Color.White.copy(0.5f)),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
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
                            color = if (pager.currentPage == 0) PrimaryBlue else PrimaryPink
                        )
                    )

                }
            }
        }

    }
}

