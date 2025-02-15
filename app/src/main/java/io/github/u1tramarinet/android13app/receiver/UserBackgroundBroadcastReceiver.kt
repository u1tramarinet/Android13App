package io.github.u1tramarinet.android13app.receiver

import android.content.Context
import android.content.Intent
import io.github.u1tramarinet.android13app.util.LogUtils

class UserBackgroundBroadcastReceiver : BaseBroadcastReceiver() {
    override val targetAction: String
        get() = Intent.ACTION_USER_BACKGROUND

    override fun onReceive(context: Context, intent: Intent, action: String) {
        LogUtils.d(UserBackgroundBroadcastReceiver::class.java.simpleName, "onReceive")
    }
}