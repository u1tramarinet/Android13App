package io.github.u1tramarinet.android13app.ui.screen.legacy

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.u1tramarinet.android13app.ui.screen.widgetsample.WidgetSampleScreenScaffold
import io.github.u1tramarinet.android13app.ui.theme.Android13AppTheme
import io.github.u1tramarinet.android13app.ui.theme.AndroidViewInheritTheme
import io.github.u1tramarinet.android13app.ui.widget.AndroidViewCheckBox
import io.github.u1tramarinet.android13app.ui.widget.AndroidViewDatePicker
import io.github.u1tramarinet.android13app.ui.widget.AndroidViewNumberPicker
import io.github.u1tramarinet.android13app.ui.widget.AndroidViewRadioButton
import io.github.u1tramarinet.android13app.ui.widget.AndroidViewSwitch

@Composable
fun LegacyScreen(modifier: Modifier = Modifier, scrollState: ScrollState) {
    LegacyScreenContent(modifier, scrollState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LegacyScreenContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {
    AndroidViewInheritTheme {
        WidgetSampleScreenScaffold(
            modifier = modifier,
            title = "Android 13 App(Legacy)",
            checkBoxes = { checkedIndices, onCheckedChange ->
                AndroidViewCheckBox(
                    checked = checkedIndices.contains(0),
                    onCheckedChange = { isChecked ->
                        onCheckedChange.invoke(0, isChecked)
                    },
                )
                AndroidViewCheckBox(
                    checked = checkedIndices.contains(1),
                    onCheckedChange = { isChecked ->
                        onCheckedChange.invoke(1, isChecked)
                    },
                )
            },
            radioButtons = { selectedIndex, onClick ->
                AndroidViewRadioButton(
                    selected = selectedIndex == 0,
                    onClick = {
                        onClick.invoke(0)
                    },
                )
                AndroidViewRadioButton(
                    selected = selectedIndex == 1,
                    onClick = {
                        onClick.invoke(1)
                    },
                )
            },
            switches = { checkedIndices, onCheckedChange ->
                AndroidViewSwitch(
                    checked = checkedIndices.contains(0),
                    onCheckedChange = { isChecked ->
                        onCheckedChange.invoke(0, isChecked)
                    },
                )
                AndroidViewSwitch(
                    checked = checkedIndices.contains(1),
                    onCheckedChange = { isChecked ->
                        onCheckedChange.invoke(1, isChecked)
                    },
                )
            },
            datePicker = {
                AndroidViewDatePicker()
            },
            numberPicker = {
                AndroidViewNumberPicker()
            },
            scrollState = scrollState,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LegacyScreenPreview() {
    Android13AppTheme {
        LegacyScreenContent()
    }
}