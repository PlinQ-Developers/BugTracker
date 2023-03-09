package com.plinqdevelopers.dartplay

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BTApplication : Application() {

    @Override
    override fun onCreate() {
        super.onCreate()

        Timber.plant(
            Timber.DebugTree()
        )
    }
}
