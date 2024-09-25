package itm.pbl.bmicalculator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import itm.pbl.bmicalculator.presentation.GenderScreen
import itm.pbl.bmicalculator.presentation.HeightWeightScreen
import itm.pbl.bmicalculator.presentation.ResultScreen
import kotlinx.serialization.Serializable

@Composable
fun BmiNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = GenderScreenRoute
    ){
        composable<GenderScreenRoute> {
            GenderScreen() {
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

@Serializable
object GenderScreenRoute

@Serializable
object HeightWeightScreenRoute

@Serializable
object ResultScreenRoute