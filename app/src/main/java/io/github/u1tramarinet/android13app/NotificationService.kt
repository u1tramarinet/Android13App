package io.github.u1tramarinet.android13app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class NotificationService(private val context: Context, private val listener: Listener? = null) {
    private val manager = context.getSystemService(NotificationManager::class.java)
    private val notificationChannel = NotificationChannel(
        "android_13_app_notification",
        "Android 13 App's notification",
        NotificationManager.IMPORTANCE_DEFAULT,
    )
    private val random = Random(1L)

    init {
        initChannel()
    }

    private fun initChannel() {
        manager.createNotificationChannel(notificationChannel)
    }

    fun showNotification(title: String, message: String) {
        val id = random.nextInt()
        val notification = NotificationCompat.Builder(context, notificationChannel.id)
            .setContentTitle("$id $title")
            .setContentText(message)
            .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        manager.notify(id, notification)

        Log.d(TAG, "notificationChannels=${manager.notificationChannels}")
        Log.d(TAG, "activeNotifications=${manager.activeNotifications}")
        listener?.onNotificationChannelsUpdated(manager.notificationChannels)
        listener?.onActiveNotificationsUpdated(manager.activeNotifications.toList())
    }

    interface Listener {
        fun onNotificationChannelsUpdated(channels: List<NotificationChannel>)
        fun onActiveNotificationsUpdated(notifications: List<StatusBarNotification>)
    }

    companion object {
        private const val TAG = "NotificationService"
    }
}
