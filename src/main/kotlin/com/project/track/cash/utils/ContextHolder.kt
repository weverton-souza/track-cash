package com.project.track.cash.utils

object ContextHolder {
    private val CONTEXT: ThreadLocal<Context> = ThreadLocal()

    var context: Context?
        get() = CONTEXT.get()
        set(value) = CONTEXT.set(value)

    fun clear() = CONTEXT.remove()
}