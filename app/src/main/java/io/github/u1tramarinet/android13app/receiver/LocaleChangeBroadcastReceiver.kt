package io.github.u1tramarinet.android13app.receiver

import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.Locale

class LocaleChangeBroadcastReceiver : BaseBroadcastReceiver() {
    override val targetAction: String
        get() = Intent.ACTION_LOCALE_CHANGED

    override fun onReceive(context: Context, intent: Intent, action: String) {
        Log.d("LocaleChangeBroadcastReceiver", "onReceive")
        Log.d(
            "LocaleChangeBroadcastReceiver",
            "currentLocale=${Locale.getDefault().toLanguageTag()}",
        )
    }
}