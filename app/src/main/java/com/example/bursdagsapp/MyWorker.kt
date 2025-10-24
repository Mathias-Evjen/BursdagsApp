package com.example.bursdagsapp

import android.content.Context
import android.icu.util.Calendar
import android.telephony.SmsManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.bursdagsapp.data.PreferenceManager
import com.example.bursdagsapp.repositories.FriendRepository
import kotlinx.coroutines.flow.first

class MyWorker(val context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        val calendar = Calendar.getInstance()

        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH) + 1

        Log.d("WorkManager", "Fetching friend from database...")

        val repository = FriendRepository(MyApp.database.friendDao())
        val preferenceManager = PreferenceManager(applicationContext)

        val friends = repository.allFriends.first().filter { friend ->
            friend.birthDay == currentDay && friend.birthMonth == currentMonth
        }
        Log.d("Repository", "Number of friends with birthday today: ${friends.size}")

        for (friend in friends) {
            try {
                val smsManager: SmsManager = context.getSystemService(SmsManager::class.java)
                val messageToSend = friend.message ?: preferenceManager.getDefaultMessage()

                // For å teste SMS med emulator, bytt friend.phoneNumber med "5554"
                smsManager.sendTextMessage(friend.phoneNumber, null, messageToSend, null, null)
                Log.d("MyWorker", "SMS sent successfully to ${friend.phoneNumber}.")
            } catch (e: Exception) {
                Log.e("MyWorker", "Failed to send SMS.", e)
                return Result.retry()
            }
        }
        return Result.success()
    }
}