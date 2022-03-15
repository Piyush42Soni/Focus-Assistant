package com.example.focusassistant.History_db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface historyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: history)

    @Query("DELETE FROM history_table")
    suspend fun delete()

    @Query("SELECT * FROM history_table")
    fun getAllPersonsInfo(): LiveData<List<history>>
}