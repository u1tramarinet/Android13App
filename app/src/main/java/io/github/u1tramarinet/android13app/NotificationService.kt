package io.github.u1tramarinet.android13app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
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

    fun showNotification(title: String, message: String, deepLink: String? = null) {
        val id = random.nextInt()
        val pendingIntent = createPendingIntent(createIntent(deepLink))
        Log.d(TAG, "pendingIntent=$pendingIntent")
        val notification = NotificationCompat.Builder(context, notificationChannel.id)
            .setContentTitle("$id $title")
            .setContentText(message)
            .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOnlyAlertOnce(true)
            .build()
        manager.notify(id, notification)

        Log.d(TAG, "notificationChannels=${manager.notificationChannels}")
        Log.d(TAG, "activeNotifications=${manager.activeNotifications}")
        listener?.onNotificationChannelsUpdated(manager.notificationChannels)
        listener?.onActiveNotificationsUpdated(manager.activeNotifications.toList())
    }

    private fun createIntent(deepLink: String?): Intent {
        return if (deepLink != null) {
            Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(deepLink)
            }
        } else {
            Intent(context, MainActivity::class.java)
        }
    }

    private fun createPendingIntent(intent: Intent): PendingIntent? {
        return TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }
    }

    interface Listener {
        fun onNotificationChannelsUpdated(channels: List<NotificationChannel>)
        fun onActiveNotificationsUpdated(notifications: List<StatusBarNotification>)
    }

    companion object {
        private const val TAG = "NotificationService"
    }
}
