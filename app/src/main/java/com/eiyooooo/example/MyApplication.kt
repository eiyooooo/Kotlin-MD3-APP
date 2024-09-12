package com.eiyooooo.example

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.eiyooooo.example.entities.Preferences
import com.eiyooooo.example.utils.FLog
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.DynamicColorsOptions
import timber.log.Timber
import java.util.Date

class MyApplication : Application() {

    companion object {
        lateinit var appStartTime: Date
            private set
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        appStartTime = Date()

        Preferences.init(this)

        FLog.init(this)
        if (Preferences.enableLog) FLog.startFLog()
        Timber.i("App started at: $appStartTime")

        AppCompatDelegate.setDefaultNightMode(Preferences.darkTheme)
        DynamicColors.applyToActivitiesIfAvailable(this,
            DynamicColorsOptions.Builder()
                .setPrecondition { _, _ -> Preferences.systemColor }
                .build()
        )
    }
}
