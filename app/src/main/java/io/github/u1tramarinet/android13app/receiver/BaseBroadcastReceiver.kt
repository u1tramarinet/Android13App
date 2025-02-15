package io.github.u1tramarinet.android13app.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.github.u1tramarinet.android13app.util.LogUtils

abstract class BaseBroadcastReceiver : BroadcastReceiver() {
    abstract val targetAction: String

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        LogUtils.d(BaseBroadcastReceiver::class.java.simpleName, "onReceive(action=$action)")
        if (action != null && intent.action == targetAction) {
            onReceive(context, intent, action)
        }
    }

    abstract fun onReceive(context: Context, intent: Intent, action: String)
}