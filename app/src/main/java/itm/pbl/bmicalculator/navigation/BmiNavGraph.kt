package itm.pbl.bmicalculator.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import itm.pbl.bmicalculator.data.GenderScreenRoute
import itm.pbl.bmicalculator.data.HeightWeightScreenRoute
import itm.pbl.bmicalculator.data.ResultScreenRoute
import itm.pbl.bmicalculator.presentation.genderscreen.GenderScreen
import itm.pbl.bmicalculator.presentation.heightweightscreen.HeightWeightScreen
import itm.pbl.bmicalculator.presentation.resultscreen.ResultScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BmiNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = GenderScreenRoute
        ) {
            composable<GenderScreenRoute> {
                GenderScreen(
                    innerPaddingValues = innerPadding,
                    animatedVisibilityScope = this@composable,
                ) { route ->
                    navController.navigate(route)
                }
            }

            composable<HeightWeightScreenRoute> {
                val data = it.toRoute<HeightWeightScreenRoute>()
                HeightWeightScreen(
                    innerPaddingValues = innerPadding,
                    animatedVisibilityScope = this@composable,
                    data,
                    navController
                ) { route ->
                    navController.navigate(route)
                }
            }

            composable<ResultScreenRoute> {
                val data = it.toRoute<ResultScreenRoute>()
                ResultScreen(
                    innerPaddingValues = innerPadding,
                    animatedVisibilityScope = this@composable,
                    data,
                    navController
                )
            }
        }
    }
}
