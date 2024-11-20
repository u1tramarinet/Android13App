package io.github.u1tramarinet.android13app.ui.widget

import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import io.github.u1tramarinet.android13app.ui.theme.AndroidViewInheritTheme

@Composable
fun AndroidViewDatePicker(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.background(color = Color.Black),
        factory = { context ->
            DatePicker(
                context,
                null,
                0,
                io.github.u1tramarinet.android13app.R.style.DatePickerSpinnerStyle
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun AndroidViewDatePickerPreview() {
    AndroidViewInheritTheme {
        AndroidViewDatePicker()
    }
}