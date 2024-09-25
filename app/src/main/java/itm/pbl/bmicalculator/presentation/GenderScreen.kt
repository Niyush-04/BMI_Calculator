package itm.pbl.bmicalculator.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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
                    .size((270 * imgScale).dp)
                    .clip(CircleShape)
                    .background(if (targetState == 0) PrimaryBlue else PrimaryPink)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp, top = 40.dp),
                text = "Select Gender",
                style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.SemiBold)
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                TextButton(
                    modifier = Modifier
                        .alpha(if (pager.currentPage == 0) 1f else 0.3f)
                        .width(150.dp)
                        .background(
                            PrimaryBlue.copy(0.3f), shape = CircleShape
                        ),
                    onClick = {
                        coroutineScope.launch {
                            pager.scrollToPage(0)
                        }
                    }, border = BorderStroke(2.dp, PrimaryBlue)
                ) {
                    Text(
                        text = "Male",
                        color = PrimaryBlue,
                        style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.SemiBold)
                    )
                }
                TextButton(modifier = Modifier
                    .alpha(if (pager.currentPage == 1) 1f else 0.3f)
                    .width(150.dp)
                    .background(
                        PrimaryPink.copy(0.3f), shape = CircleShape
                    ),
                    onClick = {
                        coroutineScope.launch {
                            pager.scrollToPage(1)
                        }
                    }, border = BorderStroke(2.dp, PrimaryPink)
                ) {
                    Text(
                        text = "Female",
                        color = PrimaryPink,
                        style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.SemiBold)
                    )
                }
            }
            Button(modifier = Modifier
                .align(Alignment.End)
                .padding(30.dp),
                colors = ButtonDefaults.buttonColors(if (pager.currentPage == 1) PrimaryPink else PrimaryBlue),
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
                Icon(
                    modifier = Modifier.size(45.dp),
                    imageVector = Icons.AutoMirrored.TwoTone.ArrowForward, contentDescription = null
                )
            }

        }

    }
}

