package com.example.focusassistant.tasks_db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.focusassistant.History_db.history

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert1(item: task)

    @Delete
    suspend fun delete1(item:task)

    @Query("SELECT * FROM task_table")
    fun getAllPersonsInfo1(): LiveData<List<task>>
}