package com.gustu.logbook.sharePreferences

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {

        @get:Synchronized
        var context: Context? = null
            private set
    }
}
