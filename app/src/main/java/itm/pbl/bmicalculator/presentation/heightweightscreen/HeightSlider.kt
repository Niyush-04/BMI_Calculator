package itm.pbl.bmicalculator.presentation.heightweightscreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import itm.pbl.bmicalculator.presentation.viewmodel.BmiViewModel

@Composable
fun HeightSlider(viewModel: BmiViewModel, heightSlider: Float) {
    Slider(
        value = heightSlider,
        onValueChange = {
            if (it in 1.2f..2.2f) {
                viewModel.onHeightValueChange(it)
            }
        },
        modifier = Modifier
            .padding(top = 42.dp)
            .graphicsLayer(
                rotationZ = 270f,
                transformOrigin = TransformOrigin(0f, 0f)
            )
            .layout { measurable, constraint ->
                val placeable = measurable.measure(
                    Constraints(
                        minWidth = constraint.minHeight,
                        maxWidth = constraint.maxHeight,
                        minHeight = constraint.minWidth,
                        maxHeight = constraint.maxHeight
                    )
                )
                layout(placeable.height, placeable.width) {
                    placeable.place(-placeable.width, 0)
                }
            },
        valueRange = 0f..2.2f,
        colors = SliderDefaults.colors(
            Color.White,
            Color.White.copy(0.5f),
            Color.White.copy(0.5f),
            Color.White.copy(0.5f)
        )
    )
}
