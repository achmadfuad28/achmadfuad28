package com.framework.di.components

import android.content.Context
import android.content.SharedPreferences

interface AppComponent {
    fun context(): Context
    fun prefs(): SharedPreferences
}