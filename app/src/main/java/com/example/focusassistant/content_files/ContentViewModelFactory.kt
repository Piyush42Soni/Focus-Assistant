package com.example.focusassistant.content_files


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.focusassistant.History_db.repo.history_repo
import com.example.focusassistant.tasks_db.repo.task_repo

@Suppress("UNCHECKED_CAST")
class ContentViewModelFactory(
    private val repository: history_repo,private val repository1: task_repo
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContentViewModel(repository,repository1) as T
    }
}