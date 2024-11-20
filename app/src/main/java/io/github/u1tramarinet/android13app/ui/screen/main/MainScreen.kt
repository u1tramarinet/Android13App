package io.github.u1tramarinet.android13app.ui.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.u1tramarinet.android13app.Android13AppRoute
import io.github.u1tramarinet.android13app.ui.LocalePreviews
import io.github.u1tramarinet.android13app.ui.theme.Android13AppTheme
import io.github.u1tramarinet.android13app.ui.widget.playSoundEffect

@Composable
fun MainScreen(modifier: Modifier = Modifier, uiAction: MainScreenUiAction) {
    MainScreenContent(modifier, uiAction = uiAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    uiAction: MainScreenUiAction = MainScreenUiAction(),
) {
    val context = LocalContext.current
    Column(modifier = modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .clickable {
                    playSoundEffect(context, force = true)
                    uiAction.onItemClick(Android13AppRoute.WidgetSample)
                }
                .fillMaxWidth()
                .padding(32.dp),
        ) {
            Text(text = "Widget Sample", color = Color.White)
        }
        Divider()
        Box(
            modifier = Modifier
                .clickable {
                    playSoundEffect(context, force = true)
                    uiAction.onItemClick(Android13AppRoute.NotificationSample)
                }
                .fillMaxWidth()
                .padding(32.dp),
        ) {
            Text(text = "Notification Sample", color = Color.White)
        }
        Divider()
        Box(
            modifier = Modifier
                .clickable {
                    playSoundEffect(context, force = true)
                    uiAction.onItemClick(Android13AppRoute.Nested)
                }
                .fillMaxWidth()
                .padding(32.dp),
        ) {
            Text(text = "Nested", color = Color.White)
        }
        Divider()
        Box(
            modifier = Modifier
                .clickable {
                    playSoundEffect(context, force = true)
                    uiAction.onItemClick(Android13AppRoute.Detail)
                }
                .fillMaxWidth()
                .padding(32.dp),
        ) {
            Text(text = "Detail", color = Color.White)
        }
        Divider()
        Box(
            modifier = Modifier
                .clickable {
                    playSoundEffect(context, force = true)
                    uiAction.onItemClick(Android13AppRoute.SideNested)
                }
                .fillMaxWidth()
                .padding(32.dp),
        ) {
            Text(text = "SideNested", color = Color.White)
        }
    }
}

@Composable
@LocalePreviews
fun MainScreenPreview() {
    Android13AppTheme {
        MainScreenContent()
    }
}