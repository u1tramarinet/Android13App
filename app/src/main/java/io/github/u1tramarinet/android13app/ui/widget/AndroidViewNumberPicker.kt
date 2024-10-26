package io.github.u1tramarinet.android13app.ui.widget

import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import io.github.u1tramarinet.android13app.ui.theme.AndroidViewInheritTheme

@Composable
fun AndroidViewNumberPicker(modifier: Modifier = Modifier) {
    Row {
        ThemedAndroidView(
            modifier = modifier.background(color = Color.Black),
            factory = { context ->
                NumberPicker(context)
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AndroidViewNumberPickerPreview() {
    AndroidViewInheritTheme {
        AndroidViewNumberPicker()
    }
}