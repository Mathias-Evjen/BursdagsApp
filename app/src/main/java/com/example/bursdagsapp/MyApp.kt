package com.example.bursdagsapp

import android.app.Application
import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MyApp: Application() {
    fun cancelDailyWork(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork("Daily work")
    }

    fun scheduleDailyWork(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(
            24, TimeUnit.HOURS
        )
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "Daily work",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }
}