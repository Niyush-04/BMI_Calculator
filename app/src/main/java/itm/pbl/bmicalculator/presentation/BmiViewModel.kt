package itm.pbl.bmicalculator.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {

    private val _heightSlider = mutableFloatStateOf(1.7f)
    private val _weightSlider = mutableFloatStateOf(0.4f)

    val heightSlider: State<Float> = _heightSlider
    val weightSlider: State<Float> = _weightSlider

    fun onHeightValueChange(height: Float) {
        _heightSlider.floatValue = height
    }

    fun onWeightValueChange(weight: Float) {
        _weightSlider.floatValue = weight
    }

    // height in cm range from 120..220
    val height: Int
        get() = (_heightSlider.floatValue * 100).toInt()

    // weight in kg range from 30..150
    val weight: Int
        get() = (_weightSlider.floatValue * 200).toInt()

    fun bmiScore(): Float {
        val bmi = (weight.toFloat() / (_heightSlider.floatValue * _heightSlider.floatValue))
        return bmi
    }

    fun bmiResult(): String {
        val bmi = bmiScore()

        return when {
            bmi < 18.5 -> "Underweight"
            bmi in 18.5..24.9 -> "Normal weight"
            bmi in 25.0..29.9 -> "Overweight"
            bmi in 30.0..34.9 -> "Obesity (Class 1)"
            bmi in 35.0..39.9 -> "Obesity (Class 2)"
            else -> "Obesity (Class 3 - Extreme Obesity)"
        }
    }
}