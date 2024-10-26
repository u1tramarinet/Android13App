package io.github.u1tramarinet.android13app.ui.screen.main

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleScreenScaffold(
    modifier: Modifier = Modifier,
    title: String,
    checkBoxes: @Composable RowScope.(checkedIndices: List<Int>, onCheckedChange: (Int, Boolean) -> Unit) -> Unit,
    radioButtons: @Composable RowScope.(selectedIndex: Int, onClick: (Int) -> Unit) -> Unit,
    switches: @Composable RowScope.(checkedIndices: List<Int>, onCheckedChange: (Int, Boolean) -> Unit) -> Unit,
    datePicker: @Composable BoxScope.() -> Unit,
    numberPicker: @Composable BoxScope.() -> Unit,
    scrollState: ScrollState = rememberScrollState(),
) {
    val currentCheckedCheckBoxIndices = remember { mutableStateListOf<Int>(1) }
    var currentSelectedIndex = remember { mutableStateOf<Int>(1) }
    val currentCheckedSwitchIndices = remember { mutableStateListOf<Int>(1) }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(title) })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(scrollState),
        ) {
            Text(text = "CheckBoxes")
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                checkBoxes.invoke(
                    this,
                    currentCheckedCheckBoxIndices,
                ) { index: Int, isChecked: Boolean ->
                    if (isChecked) {
                        currentCheckedCheckBoxIndices.add(index)
                    } else {
                        currentCheckedCheckBoxIndices.remove(index)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "RadioButtons")
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                radioButtons.invoke(
                    this,
                    currentSelectedIndex.value,
                ) { index: Int ->
                    currentSelectedIndex.value = index
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Switches")
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                switches.invoke(
                    this,
                    currentCheckedSwitchIndices,
                ) { index: Int, isChecked: Boolean ->
                    if (isChecked) {
                        currentCheckedSwitchIndices.add(index)
                    } else {
                        currentCheckedSwitchIndices.remove(index)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "DatePicker")

            Box(
                modifier = Modifier.padding(16.dp),
                content = datePicker,
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "NumberPicker")

            Box(
                modifier = Modifier.padding(16.dp),
                content = numberPicker,
            )
        }
    }
}