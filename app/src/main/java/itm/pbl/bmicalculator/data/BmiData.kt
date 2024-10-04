package itm.pbl.bmicalculator.data

import androidx.annotation.DrawableRes
import itm.pbl.bmicalculator.R
import kotlinx.serialization.Serializable

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

val genders = listOf(
    HeightWeightScreenRoute(R.drawable.men, "Male"),
    HeightWeightScreenRoute(R.drawable.women, "Female"),
)
