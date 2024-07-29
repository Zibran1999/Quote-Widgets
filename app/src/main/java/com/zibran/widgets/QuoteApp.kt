package com.zibran.widgets

import android.app.Application
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuoteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Toast.makeText(this, BuildConfig.BASE_URL, Toast.LENGTH_SHORT).show()
        } else {
            println("Release: ${BuildConfig.BASE_URL}")
            Toast.makeText(this, BuildConfig.BASE_URL, Toast.LENGTH_SHORT).show()
        }
    }
}