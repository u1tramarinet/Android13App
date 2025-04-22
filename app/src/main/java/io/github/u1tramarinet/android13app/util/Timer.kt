package io.github.u1tramarinet.android13app.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class Timer(private val scope: CoroutineScope) {
    constructor(dispatcher: CoroutineDispatcher) : this(CoroutineScope(dispatcher))

    private var job: Job? = null

    fun start(durationMs: Long) {
        job?.cancel()
        job = scope.async {
            println("start")
            delay(durationMs)
            println("expired")
        }
    }

    fun isActive() = job?.isActive == true

    fun cancel() {
        job?.cancel()
        job = null
    }
}