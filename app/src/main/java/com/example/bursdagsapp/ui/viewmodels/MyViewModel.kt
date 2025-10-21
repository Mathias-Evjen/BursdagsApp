package com.example.bursdagsapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import com.example.bursdagsapp.MyApp
import com.example.bursdagsapp.data.PreferenceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val myApp = application as MyApp
    private val preferenceManager = PreferenceManager(application.applicationContext)

    private val _isSmsEnabled = MutableStateFlow(preferenceManager.isSmsEnabled())
    val isSmsEnabled: StateFlow<Boolean> = _isSmsEnabled

    fun start() {
        preferenceManager.setSmsEnabled(true)
        _isSmsEnabled.value = true
        myApp.scheduleDailyWork(application.applicationContext)
    }

    fun stop() {
        preferenceManager.setSmsEnabled(false)
        _isSmsEnabled.value = false
        myApp.cancelDailyWork(application.applicationContext)
    }
}