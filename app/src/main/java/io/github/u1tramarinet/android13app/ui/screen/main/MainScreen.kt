package io.github.u1tramarinet.android13app.ui.screen.main

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.u1tramarinet.android13app.Android13AppRoute
import io.github.u1tramarinet.android13app.R
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
    val onItemClick: (Android13AppRoute) -> Unit = { route ->
        playSoundEffect(context, force = true)
        uiAction.onItemClick(route)
    }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Item(
            titleRes = R.string.widget_sample_title,
            route = Android13AppRoute.WidgetSample,
            onClick = onItemClick,
        )
        Item(
            titleRes = R.string.notification_sample,
            route = Android13AppRoute.NotificationSample,
            onClick = onItemClick,
        )
        Item(
            titleRes = R.string.nested,
            route = Android13AppRoute.Nested,
            onClick = onItemClick,
        )
        Item(
            titleRes = R.string.detail,
            route = Android13AppRoute.Detail,
            onClick = onItemClick,
        )
        Item(
            titleRes = R.string.side_nested,
            route = Android13AppRoute.SideNested,
            onClick = onItemClick,
        )
        Item(
            titleRes = R.string.repeat,
            route = Android13AppRoute.Repeat,
            onClick = onItemClick,
        )
        Item(
            titleRes = R.string.list,
            route = Android13AppRoute.List,
            onClick = onItemClick,
        )
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    route: Android13AppRoute,
    onClick: (route: Android13AppRoute) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { onClick(route) }
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 16.dp),
    ) {
        Text(text = stringResource(titleRes), color = Color.White)
    }
    Divider()
}

@Composable
@LocalePreviews
fun MainScreenPreview() {
    Android13AppTheme {
        MainScreenContent()
    }
}