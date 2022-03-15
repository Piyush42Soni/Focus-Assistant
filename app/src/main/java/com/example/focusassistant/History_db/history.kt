package com.example.focusassistant.History_db
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class history(
    @ColumnInfo(name = "start_time")
    var start_time: Int,
    @ColumnInfo(name = "end_time")
    var end_time: Int,

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}