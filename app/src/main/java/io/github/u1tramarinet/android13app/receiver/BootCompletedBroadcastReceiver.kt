package io.github.u1tramarinet.android13app.receiver

import android.content.Context
import android.content.Intent
import io.github.u1tramarinet.android13app.util.LogUtils

class BootCompletedBroadcastReceiver : BaseBroadcastReceiver() {
    override val targetAction: String
        get() = Intent.ACTION_BOOT_COMPLETED

    override fun onReceive(context: Context, intent: Intent, action: String) {
        LogUtils.d(BootCompletedBroadcastReceiver::class.java.simpleName, "onReceive")
    }
}