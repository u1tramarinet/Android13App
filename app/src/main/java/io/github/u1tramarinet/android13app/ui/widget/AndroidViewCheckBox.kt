package io.github.u1tramarinet.android13app.ui.widget

import android.widget.CheckBox
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
fun AndroidViewCheckBox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            CheckBox(context).apply {
                isChecked = checked
                isEnabled = enabled
                setOnCheckedChangeListener { _, isChecked ->
                    onCheckedChange?.invoke(isChecked)
                }
            }
        },
        update = { view ->
            view.isChecked = checked
            view.isEnabled = enabled
        }
    )
}

@Composable
@Preview(showBackground = true)
fun AndroidViewCheckBoxPreview() {
    AndroidViewInheritTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AndroidViewCheckBox(checked = true, onCheckedChange = null)
            AndroidViewCheckBox(checked = false, onCheckedChange = null)
        }
    }
}