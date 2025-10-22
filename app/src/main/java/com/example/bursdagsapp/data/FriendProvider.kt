package com.example.bursdagsapp.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.bursdagsapp.data.FriendContract.AUTHORITY
import com.example.bursdagsapp.data.FriendContract.PATH_FRIENDS

class FriendProvider : ContentProvider() {

    private lateinit var friendDao: FriendDao

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, PATH_FRIENDS, CODE_FRIENDS_DIR)
        addURI(AUTHORITY, "$PATH_FRIENDS/#", CODE_FRIENDS_ITEM)
    }

    companion object {
        private const val CODE_FRIENDS_DIR = 1
        private const val CODE_FRIENDS_ITEM = 2
    }

    override fun onCreate(): Boolean {
        val context = context ?: return false
        val db = AppDatabase.getDatabase(context)
        friendDao = db.friendDao()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ) : Cursor? {
        val code = uriMatcher.match(uri)
        return when (code) {
            CODE_FRIENDS_DIR -> {
                val context = context ?: return null
                val cursor = friendDao.getAllFriendsAsCursor()
                cursor.setNotificationUri(context.contentResolver, uri)
                cursor
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? = null
    override fun insert(uri: Uri, values: ContentValues?): Uri? = null
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int = 0
}