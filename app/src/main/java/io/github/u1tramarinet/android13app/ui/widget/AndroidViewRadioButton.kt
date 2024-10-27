package io.github.u1tramarinet.android13app.ui.widget

import android.widget.RadioButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import io.github.u1tramarinet.android13app.ui.theme.AndroidViewInheritTheme

@Composable
fun AndroidViewRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            RadioButton(context).apply {
                isChecked = selected
                isEnabled = enabled
                setOnClickListener {
                    onClick?.invoke()
                }
            }
        },
        update = { view ->
            view.isChecked = selected
            view.isEnabled = enabled
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun AndroidViewRadioButtonPreview() {
    AndroidViewInheritTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AndroidViewRadioButton(selected = true, onClick = null)
            AndroidViewRadioButton(selected = false, onClick = null)
        }
    }
}