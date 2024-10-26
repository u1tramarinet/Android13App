package io.github.u1tramarinet.android13app.ui.screen.widgetsample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.u1tramarinet.android13app.ui.screen.compose.ComposeScreen
import io.github.u1tramarinet.android13app.ui.screen.legacy.LegacyScreen
import io.github.u1tramarinet.android13app.ui.theme.Android13AppTheme

@Composable
fun WidgetSampleScreen(modifier: Modifier = Modifier, uiAction: WidgetSampleScreenUiAction) {
    WidgetSampleScreenContent(modifier, uiAction = uiAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WidgetSampleScreenContent(
    modifier: Modifier = Modifier,
    uiAction: WidgetSampleScreenUiAction = WidgetSampleScreenUiAction(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Widget Sample")
                },
                navigationIcon = {
                    IconButton(onClick = uiAction.onBackClick) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
            )
        },
    ) { innerPadding ->
        Row(modifier = Modifier.padding(innerPadding)) {
            Box(modifier = Modifier.weight(1f)) {
                ComposeScreen(scrollState = rememberScrollState())
            }
            Box(modifier = Modifier.weight(1f)) {
                LegacyScreen(scrollState = rememberScrollState())
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun WidgetSampleScreenPreview() {
    Android13AppTheme {
        WidgetSampleScreenContent()
    }
}