package io.github.u1tramarinet.android13app.util

import android.util.Log

object LogUtils {
    private const val TAG = "Android13App"

    fun d(tag: String, message: String) {
        Log.d(TAG, "[$tag] $message")
    }
}