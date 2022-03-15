package com.example.focusassistant.tasks_db.repo


import com.example.focusassistant.tasks_db.task
import com.example.focusassistant.tasks_db.task_database

class task_repo(
        private val db: task_database
    ) {
        suspend fun upsert1(item: task) = db.gettaskDao().upsert1(item)

        suspend fun delete1(item:task) = db.gettaskDao().delete1(item)

        fun getAllPersonsInfo1() = db.gettaskDao().getAllPersonsInfo1()
    }