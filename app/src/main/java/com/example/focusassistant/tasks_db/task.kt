package com.example.focusassistant.tasks_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class task(
    @ColumnInfo(name = "given_task")
    var name: String,
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}