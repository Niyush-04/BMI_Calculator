package itm.pbl.bmicalculator.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import itm.pbl.bmicalculator.navigation.HeightWeightScreenRoute

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HeightWeightScreen(
//    animatedVisibilityScope: AnimatedVisibilityScope,
//    data: HeightWeightScreenRoute,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = onClick) { }
    }
}