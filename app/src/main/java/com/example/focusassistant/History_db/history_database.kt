package com.example.focusassistant.History_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [history::class],
    version = 1
)
abstract class history_database: RoomDatabase() {

    abstract fun gethistoryDao(): historyDao

    companion object {
        @Volatile
        private var instance: history_database? = null
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
                history_database::class.java, "historyDB.db").build()
    }
}