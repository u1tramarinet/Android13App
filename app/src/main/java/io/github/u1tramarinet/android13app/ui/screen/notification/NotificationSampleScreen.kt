package io.github.u1tramarinet.android13app.ui.screen.notification

import android.Manifest
import android.app.NotificationChannel
import android.service.notification.StatusBarNotification
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import io.github.u1tramarinet.android13app.Android13AppRoute
import io.github.u1tramarinet.android13app.NotificationService
import io.github.u1tramarinet.android13app.ui.screen.nested.Android13AppNestedRoute
import io.github.u1tramarinet.android13app.ui.theme.Android13AppTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationSampleScreen(
    modifier: Modifier = Modifier,
    uiAction: NotificationSampleScreenUiAction
) {
    val permissions = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val notificationChannels = remember { mutableStateListOf<NotificationChannel>() }
    val activeNotifications = remember { mutableStateListOf<StatusBarNotification>() }
    val notificationService = NotificationService(
        context = LocalContext.current,
        listener = object : NotificationService.Listener {
            override fun onNotificationChannelsUpdated(channels: List<NotificationChannel>) {
                notificationChannels.clear()
                notificationChannels.addAll(channels)
            }

            override fun onActiveNotificationsUpdated(notifications: List<StatusBarNotification>) {
                activeNotifications.clear()
                activeNotifications.addAll(notifications)
            }
        },
    )
    NotificationSampleScreenContent(
        modifier,
        uiAction,
        isPermissionGranted = permissions.status.isGranted,
        onLaunchPermissionRequest = {
            permissions.launchPermissionRequest()
        },
        onSendNotification = { route ->
            val messageSuffix = if (route != null) {
                " (${route.deepLink})"
            } else {
                ""
            }
            notificationService.showNotification(
                "テストタイトル",
                "テストメッセージ$messageSuffix",
                route?.deepLink,
            )
        },
        notificationChannels = notificationChannels,
        activeNotifications = activeNotifications
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotificationSampleScreenContent(
    modifier: Modifier = Modifier,
    uiAction: NotificationSampleScreenUiAction = NotificationSampleScreenUiAction(),
    isPermissionGranted: Boolean = true,
    onLaunchPermissionRequest: () -> Unit = {},
    onSendNotification: (route: Android13AppRoute?) -> Unit = {},
    notificationChannels: List<NotificationChannel> = emptyList(),
    activeNotifications: List<StatusBarNotification> = emptyList(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Notification Sample")
                },
                navigationIcon = {
                    IconButton(onClick = uiAction.onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = onLaunchPermissionRequest,
                enabled = !isPermissionGranted
            ) {
                Text(text = "通知を許可する")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    onSendNotification(null)
                },
                enabled = isPermissionGranted
            ) {
                Text(text = "通知を送信する")
            }
            Button(
                onClick = {
                    onSendNotification(Android13AppRoute.WidgetSample)
                },
                enabled = isPermissionGranted
            ) {
                Text(text = "通知を送信する(WidgetSampleへ)")
            }
            Button(
                onClick = {
                    onSendNotification(Android13AppRoute.NotificationSample)
                },
                enabled = isPermissionGranted
            ) {
                Text(text = "通知を送信する(NotificationSampleへ)")
            }
            Button(
                onClick = {
                    onSendNotification(Android13AppRoute.Nested)
                },
                enabled = isPermissionGranted
            ) {
                Text(text = "通知を送信する(Nestedへ)")
            }
            Button(
                onClick = {
                    onSendNotification(Android13AppRoute.NestedWithArg(destination = Android13AppNestedRoute.Nested3.route))
                },
                enabled = isPermissionGranted
            ) {
                Text(text = "通知を送信する(Nested3へ)")
            }
            Spacer(modifier = Modifier.height(64.dp))
            Text(text = "通知チャンネル一覧")
            notificationChannels.forEach {
                Text(text = it.name.toString())
            }
            Spacer(modifier = Modifier.height(64.dp))
            Text(text = "アクティブな通知一覧")
            activeNotifications.forEach {
                Text(text = it.notification.locusId?.toString() ?: "notification")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun NotificationSampleScreenPreview() {
    Android13AppTheme {
        NotificationSampleScreenContent()
    }
}