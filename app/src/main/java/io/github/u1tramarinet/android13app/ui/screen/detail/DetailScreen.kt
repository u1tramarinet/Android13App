package io.github.u1tramarinet.android13app.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import io.github.u1tramarinet.android13app.ui.LocalePreviews
import io.github.u1tramarinet.android13app.ui.theme.Android13AppTheme

@Composable
fun DetailScreen(modifier: Modifier = Modifier, uiAction: DetailScreenUiAction) {
    DetailScreenContent(modifier = modifier, uiAction = uiAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreenContent(
    modifier: Modifier = Modifier,
    uiAction: DetailScreenUiAction = DetailScreenUiAction(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Detail")
                },
                navigationIcon = {
                    IconButton(onClick = uiAction.onBackClick) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

        }
    }
}

@Composable
@LocalePreviews
fun DetailScreenPreview() {
    Android13AppTheme {
        DetailScreenContent()
    }
}