package itm.pbl.bmicalculator.navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import itm.pbl.bmicalculator.presentation.GenderScreen
import itm.pbl.bmicalculator.presentation.HeightWeightScreen
import itm.pbl.bmicalculator.presentation.ResultScreen
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
                GenderScreen(animatedVisibilityScope = this) {
                    navController.navigate(HeightWeightScreenRoute)
                }
            }

            composable<HeightWeightScreenRoute> {
                HeightWeightScreen() {
                    navController.navigate(ResultScreenRoute)
                }
            }

            composable<ResultScreenRoute> {
                ResultScreen()
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