package io.github.u1tramarinet.android13app.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class RemoteService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        Log.d("RemoteService", "onBind")
        return null
    }

    override fun onCreate() {
        Log.d("RemoteService", "onCreate")
        super.onCreate()
    }

    fun doSomething() {
        Log.d("RemoteService", "doSomething")
    }
}