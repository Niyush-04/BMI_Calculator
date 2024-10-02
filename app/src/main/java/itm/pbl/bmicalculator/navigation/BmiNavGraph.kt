package itm.pbl.bmicalculator.navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import itm.pbl.bmicalculator.presentation.GenderScreen
import itm.pbl.bmicalculator.presentation.HeightWeightScreen
import itm.pbl.bmicalculator.presentation.ResultScreen
import kotlinx.serialization.Serializable

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
                    animatedVisibilityScope = this,
                ) { route ->
                    navController.navigate(route)
                }
            }

            composable<HeightWeightScreenRoute> {
                val data = it.toRoute<HeightWeightScreenRoute>()
                HeightWeightScreen(
                    innerPaddingValues = innerPadding,
                    animatedVisibilityScope = this,
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
                    animatedVisibilityScope = this,
                    data,
                    navController
                )
            }
        }
    }
}

@Serializable
object GenderScreenRoute

@Serializable
data class HeightWeightScreenRoute(
    @DrawableRes
    val resId: Int,
    val resKey: String
)

@Serializable
data class ResultScreenRoute(
    val resId: Int,
    val resKey: String,
    val bmiScore: Float,
    val bmiResult: String
)
