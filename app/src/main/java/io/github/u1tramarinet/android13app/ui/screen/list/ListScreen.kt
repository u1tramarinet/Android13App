package io.github.u1tramarinet.android13app.ui.screen.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import io.github.u1tramarinet.android13app.ui.widget.AndroidViewLikeListView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(modifier: Modifier = Modifier, onBack: () -> Unit) {
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = "List Sample")
                        Spacer(modifier = Modifier.width(100.dp))
                        Text(text = "scroll position: ${scrollState.value} (max: ${scrollState.maxValue})")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        AndroidViewLikeListView(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            scrollState = scrollState,
        ) {
            repeat(100) {
                Text("list item$it")
            }
        }
    }
}