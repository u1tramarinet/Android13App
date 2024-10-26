package io.github.u1tramarinet.android13app.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.u1tramarinet.android13app.ui.screen.compose.ComposeScreen
import io.github.u1tramarinet.android13app.ui.screen.legacy.LegacyScreen
import io.github.u1tramarinet.android13app.ui.theme.Android13AppTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    MainScreenContent(modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenContent(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Box(modifier = Modifier.weight(1f)) {
            ComposeScreen(scrollState = rememberScrollState())
        }
        Box(modifier = Modifier.weight(1f)) {
            LegacyScreen(scrollState = rememberScrollState())
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun MainScreenPreview() {
    Android13AppTheme {
        MainScreenContent()
    }
}