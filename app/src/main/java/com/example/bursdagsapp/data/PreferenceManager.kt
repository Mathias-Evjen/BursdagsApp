package com.example.bursdagsapp.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        const val KEY_SMS_ENABLED = "sms_enabled"
    }

    fun isSmsEnabled(): Boolean {
        return sharedPreferences.getBoolean(KEY_SMS_ENABLED, false)
    }

    fun setSmsEnabled(isEnabled: Boolean) {
        sharedPreferences.edit { putBoolean(KEY_SMS_ENABLED, isEnabled) }
    }
}