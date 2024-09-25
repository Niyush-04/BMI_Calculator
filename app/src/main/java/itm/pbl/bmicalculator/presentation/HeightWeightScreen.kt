package itm.pbl.bmicalculator.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HeightWeightScreen(
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = onClick) { }
    }
}