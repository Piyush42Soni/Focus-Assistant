package com.example.focusassistant.History_db.repo

import com.example.focusassistant.History_db.history
import com.example.focusassistant.History_db.history_database

class history_repo(
    private val db: history_database
) {
    suspend fun upsert(item: history) = db.gethistoryDao().upsert(item)

    suspend fun delete() = db.gethistoryDao().delete()

    fun getAllPersonsInfo() = db.gethistoryDao().getAllPersonsInfo()
}