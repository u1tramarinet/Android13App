package io.github.u1tramarinet.android13app.ui.screen.repeat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.u1tramarinet.android13app.ui.widget.RepeatableButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    var counter = remember { mutableStateOf(0) }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Repeat Sample")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Text(text = "Counter: ${counter.value}")
            Spacer(modifier = Modifier.height(100.dp))
            RepeatableButton(
                onClick = {
                    counter.value++
                }
            ) {
                Text("countUp")
            }
        }
    }
}