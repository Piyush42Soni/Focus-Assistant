package com.example.focusassistant.tasks_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.focusassistant.History_db.history
import com.example.focusassistant.History_db.historyDao

@Database(
    entities = [task::class],
    version = 1
)
abstract class task_database: RoomDatabase() {

    abstract fun gettaskDao(): TaskDao

    companion object {
        @Volatile
        private var instance: task_database? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                task_database::class.java, "taskDB.db").build()
    }
}