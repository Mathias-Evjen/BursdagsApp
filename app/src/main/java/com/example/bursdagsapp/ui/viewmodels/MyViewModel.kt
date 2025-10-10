package com.example.bursdagsapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import com.example.bursdagsapp.MyApp

class MyViewModel(application: Application) : AndroidViewModel(application) {
    fun start() {
        (application as MyApp).scheduleDailyWork(application.applicationContext)
    }

    fun stop() {
        (application as MyApp).cancelDailyWork(application.applicationContext)
    }
}