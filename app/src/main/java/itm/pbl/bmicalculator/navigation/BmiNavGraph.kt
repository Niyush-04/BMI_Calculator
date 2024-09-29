package itm.pbl.bmicalculator.navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import itm.pbl.bmicalculator.presentation.GenderScreen
import itm.pbl.bmicalculator.presentation.HeightWeightScreen
import itm.pbl.bmicalculator.presentation.ResultScreen
import itm.pbl.bmicalculator.presentation.myHeight
import itm.pbl.bmicalculator.presentation.myResId
import itm.pbl.bmicalculator.presentation.myResKey
import itm.pbl.bmicalculator.presentation.myWeight
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BmiNavGraph(
    navController: NavHostController
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = GenderScreenRoute
        ) {
            composable<GenderScreenRoute> {
                GenderScreen(animatedVisibilityScope = this) { route ->
                    navController.navigate(route)
                }
            }

            composable<HeightWeightScreenRoute> {
                val data = it.toRoute<HeightWeightScreenRoute>()
                HeightWeightScreen(
                    animatedVisibilityScope = this,
                    data,
                    navController
                ) {
                    navController.navigate(ResultScreenRoute)
                }
            }

            composable<ResultScreenRoute> {
                val data = HeightWeightScreenRoute(myResId, myResKey, myHeight, myWeight)
                ResultScreen(
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
    val resKey: String,
    val height: Int,
    val weight: Int
)

@Serializable
object ResultScreenRoute