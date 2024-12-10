package io.github.u1tramarinet.android13app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.Locale

class LocaleChangeBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION) {
            Log.d("LocaleChangeBroadcastReceiver", "onReceive")
            Log.d(
                "LocaleChangeBroadcastReceiver",
                "currentLocale=${Locale.getDefault().toLanguageTag()}",
            )
        }
    }

    companion object {
        private const val ACTION = Intent.ACTION_LOCALE_CHANGED
    }
}