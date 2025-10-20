package com.example.bursdagsapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.bursdagsapp.data.AppDatabase
import java.util.concurrent.TimeUnit

class MyApp: Application() {

    companion object {
        lateinit var database: AppDatabase
            private set
    }


    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "friend_db"
        ).build()
    }

    fun cancelDailyWork(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork("Daily work")
    }

    fun scheduleDailyWork(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<MyWorker>(
            15, TimeUnit.MINUTES
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