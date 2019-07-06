package com.jaqxues.thunder

import android.app.Application
import timber.log.Timber

/**
 * This file was created by Jacques Hoffann (jaqxues) in the Project ProHealth_Android.<br>
 * Date: 06.07.2019 - Time 08:41.
 */
class ThunderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
