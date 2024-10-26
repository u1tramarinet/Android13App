package io.github.u1tramarinet.android13app.ui.screen.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.u1tramarinet.android13app.ui.screen.main.SampleScreenScaffold
import io.github.u1tramarinet.android13app.ui.theme.Android13AppTheme

@Composable
fun ComposeScreen(modifier: Modifier = Modifier, scrollState: ScrollState) {
    ComposeScreenContent(modifier, scrollState = scrollState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ComposeScreenContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {
    SampleScreenScaffold(
        modifier = modifier,
        title = "Android 13 App",
        checkBoxes = { checkedIndices, onCheckedChange ->
            Checkbox(
                checked = checkedIndices.contains(0),
                onCheckedChange = { isChecked ->
                    onCheckedChange.invoke(0, isChecked)
                },
            )
            Checkbox(
                checked = checkedIndices.contains(1),
                onCheckedChange = { isChecked ->
                    onCheckedChange.invoke(1, isChecked)
                },
            )
        },
        radioButtons = { selectedIndex, onClick ->
            RadioButton(
                selected = selectedIndex == 0,
                onClick = {
                    onClick.invoke(0)
                },
            )
            RadioButton(
                selected = selectedIndex == 1,
                onClick = {
                    onClick.invoke(1)
                },
            )
        },
        switches = { checkedIndices, onCheckedChange ->
            Switch(
                checked = checkedIndices.contains(0),
                onCheckedChange = { isChecked ->
                    onCheckedChange.invoke(0, isChecked)
                },
            )
            Switch(
                checked = checkedIndices.contains(1),
                onCheckedChange = { isChecked ->
                    onCheckedChange.invoke(1, isChecked)
                },
            )
        },
        datePicker = {
            DatePicker(
                state = DatePickerState(
                    initialDisplayMode = DisplayMode.Picker,
                    yearRange = IntRange(2023, 2023),
                    initialDisplayedMonthMillis = null,
                    initialSelectedDateMillis = null,
                ),
            )
        },
        numberPicker = {

        },
        scrollState = scrollState,
    )
}

@Composable
@Preview(showBackground = true)
private fun ComposeScreenPreview() {
    Android13AppTheme {
        ComposeScreenContent()
    }
}