package com.example.bursdagsapp.data

import android.net.Uri
import android.provider.BaseColumns
import androidx.core.net.toUri

object FriendContract {
    const val AUTHORITY = "com.example.bursdagsapp.provider"

    val BASE_CONTENT_URI: Uri = "content://$AUTHORITY".toUri()

    const val PATH_FRIENDS = "friends"

    object FriendEntry : BaseColumns {
        val CONTENT_URI: Uri = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FRIENDS).build()

        const val TABLE_NAME = "friend_table"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PHONE_NUMBER = "phoneNumber"
        const val COLUMN_BIRTH_MONTH = "birthMonth"
        const val COLUMN_BIRTH_DAY = "birthDay"
    }
}