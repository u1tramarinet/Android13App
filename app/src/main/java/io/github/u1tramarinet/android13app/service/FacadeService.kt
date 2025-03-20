package io.github.u1tramarinet.android13app.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log

class FacadeService : Service() {
    private val callbacks = mutableSetOf<IBinderCallback>()
    private var remoteServiceBinder: IBinder? = null
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            callbacks.forEach { it.onBinderConnected(service) }
            remoteServiceBinder = service
            try {
                service?.linkToDeath(deathRecipient, 0)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            remoteServiceBinder = null
        }
    }
    private val deathRecipient = object : IBinder.DeathRecipient {
        override fun binderDied() {
            remoteServiceBinder = null
            doBind()
        }
    }

    override fun onCreate() {
        super.onCreate()
        doBind()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d("FacadeService", "onBind")
        return object : IFacadeService.Stub() {
            override fun getRemoteBinder(): IBinder? {
                return remoteServiceBinder
            }

            override fun registerBinderCallback(callback: IBinderCallback?) {
                callback?.let { callbacks.add(it) }
            }
        }
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(serviceConnection)
        remoteServiceBinder?.unlinkToDeath(deathRecipient, 0)
    }

    private fun doBind() {
        val serviceIntent = Intent(this, RemoteService::class.java)
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE)
    }
}