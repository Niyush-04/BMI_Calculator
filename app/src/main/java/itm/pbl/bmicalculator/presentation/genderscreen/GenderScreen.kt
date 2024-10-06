package itm.pbl.bmicalculator.presentation.genderscreen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import itm.pbl.bmicalculator.data.HeightWeightScreenRoute
import itm.pbl.bmicalculator.data.genders
import itm.pbl.bmicalculator.ui.theme.PrimaryBlue
import itm.pbl.bmicalculator.ui.theme.PrimaryPink
import itm.pbl.bmicalculator.utils.BottomButtons
import itm.pbl.bmicalculator.utils.CustomText
import kotlinx.coroutines.launch

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GenderScreen(
    innerPaddingValues: PaddingValues,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick: (HeightWeightScreenRoute) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val pager = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { genders.size }
    )

    val bgColor by animateColorAsState(
        targetValue = if (pager.currentPage == 0) PrimaryBlue else PrimaryPink,
        label = "",
        animationSpec = tween(500)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(innerPaddingValues),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(text = "Select Gender", modifier = Modifier.padding(10.dp))

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            state = pager,
            contentPadding = PaddingValues(120.dp)
        ) { index ->
            val imgScale by animateFloatAsState(
                targetValue = if (index == pager.currentPage) 2f else 1f,
                animationSpec = tween(500),
                label = ""
            )
            Image(
                painter = painterResource(id = genders[index].resId),
                contentDescription = null,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = genders[index].resKey),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .size(200.dp)
                    .graphicsLayer(
                        scaleX = imgScale,
                        scaleY = imgScale,
                        alpha = if (pager.currentPage == index) 1f else 0.5f
                    )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 70.dp, end = 70.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        coroutineScope.launch {
                            pager.scrollToPage(0)
                        }
                    },
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.White
            )

            CustomText(text = if (pager.currentPage == 0) "Male" else "Female")

            Icon(
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        coroutineScope.launch {
                            pager.scrollToPage(1)
                        }
                    },
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White
            )
        }
        val context = LocalContext.current
        BottomButtons(
            imageVector = Icons.Outlined.Info,
            color = bgColor,
            onClickIcon = {
//                Toast.makeText(context, "coming soon", Toast.LENGTH_SHORT).show()
            },
            onClickBtn = {
                onClick(
                    HeightWeightScreenRoute(
                        resId = genders[pager.currentPage].resId,
                        resKey = genders[pager.currentPage].resKey,
                    )
                )
            }
        )
    }
}