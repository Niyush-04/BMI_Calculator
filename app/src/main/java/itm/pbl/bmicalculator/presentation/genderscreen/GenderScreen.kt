package itm.pbl.bmicalculator.presentation.genderscreen

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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

    val pagerState = rememberPagerState(pageCount = { genders.size })

    val bgColor by animateColorAsState(
        targetValue = if (pagerState.currentPage == 0) PrimaryBlue else PrimaryPink,
        label = "Background Color",
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
        CustomText(text = "Select Gender")

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 120.dp)
        ) { index ->
            val imgScale by animateFloatAsState(
                targetValue = if (index == pagerState.currentPage) 1.3f else 0.5f,
                animationSpec = tween(500),
                label = ""
            )
            Image(
                painter = painterResource(id = genders[index].resId),
                contentDescription = null,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = genders[index].resKey),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = imageBoundsTransform
                    )
                    .graphicsLayer(
                        scaleX = imgScale,
                        scaleY = imgScale,
                        alpha = if (pagerState.currentPage == index) 1f else 0.5f
                    )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(45.dp)
                    .background(color = Color.White.copy(0.3f), shape = CircleShape)
                    .clickable {
                        coroutineScope.launch {
                            pagerState.scrollToPage(0)
                        }
                    },
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.White
            )

            CustomText(text = genders[pagerState.currentPage].resKey)

            Icon(
                modifier = Modifier
                    .size(45.dp)
                    .background(color = Color.White.copy(0.3f), shape = CircleShape)
                    .clickable {
                        coroutineScope.launch {
                            pagerState.scrollToPage(1)
                        }
                    },
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White
            )
        }
        BottomButtons(
            enable = false,
            color = bgColor,
            onClickIcon = {},
            onClickBtn = {
                onClick(
                    HeightWeightScreenRoute(
                        resId = genders[pagerState.currentPage].resId,
                        resKey = genders[pagerState.currentPage].resKey
                    )
                )
            }
        )
    }
}

val imageBoundsTransform = { _: Rect, _: Rect ->
    tween<Rect>(durationMillis = 500)
}