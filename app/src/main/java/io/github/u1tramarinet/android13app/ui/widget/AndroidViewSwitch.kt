package io.github.u1tramarinet.android13app.ui.widget

import android.widget.Switch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.u1tramarinet.android13app.ui.theme.AndroidViewInheritTheme

@Composable
fun AndroidViewSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    enabled: Boolean = true,
) {
    ThemedAndroidView(
        modifier = modifier,
        factory = { context ->
            Switch(context).apply {
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
private fun AndroidViewSwitchPreview() {
    AndroidViewInheritTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AndroidViewSwitch(checked = true)
            AndroidViewSwitch(checked = false)
        }
    }
}