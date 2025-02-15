package io.github.u1tramarinet.android13app

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import io.github.u1tramarinet.android13app.receiver.UserBackgroundBroadcastReceiver
import io.github.u1tramarinet.android13app.util.LogUtils

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LogUtils.d("MainApplication", "onCreate")
        registerReceiver(
            UserBackgroundBroadcastReceiver(),
            IntentFilter(Intent.ACTION_USER_BACKGROUND)
        )
    }
}