package com.example.bursdagsapp

import android.content.Context
import android.icu.util.Calendar
import android.telephony.SmsManager
import android.util.Log
import androidx.core.content.getSystemService
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.bursdagsapp.data.Friend
import com.example.bursdagsapp.data.FriendDao
import com.example.bursdagsapp.repositories.FriendRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first

class MyWorker(val context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        val calendar = Calendar.getInstance()

        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH) + 1

        Log.d("WorkManager", "Fetching friend from database...")

        val repository = FriendRepository(MyApp.database.friendDao())

        val friendsTest = repository.allFriends.first()

        for (friend in friendsTest) {
            Log.d("Friend", "${friend.name}, ${friend.birthDay}/${friend.birthMonth}, ${friend.message}")
        }

        val friends = repository.allFriends.first().filter { friend ->
            friend.birthDay == currentDay && friend.birthMonth == currentMonth
        }

        Log.d("Calendar", "Current date: ${currentDay}/${currentMonth}")
        Log.d("Repository", "Number of friends with birthday today: ${friends.size}")

        val emulatorNumber = "5554"

        for (friend in friends) {
            try {
                // Get the default instance of SmsManager. [3, 8]
                val smsManager: SmsManager = context.getSystemService(SmsManager::class.java)
                // Send the text message. [7, 4]
                smsManager.sendTextMessage(emulatorNumber, null, friend.message, null, null)
                Log.d("MyWorker", "SMS sent successfully to $emulatorNumber.")
            } catch (e: Exception) {
                Log.e("MyWorker", "Failed to send SMS.", e)
                // If it fails, you might want to retry the work
                return Result.retry()
            }
        }
        return Result.success()
    }
}