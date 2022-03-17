package com.example.focusassistant.content_files

import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.sqlite.db.SupportSQLiteCompat.Api16Impl.cancel
import com.example.focusassistant.History_db.history
import com.example.focusassistant.History_db.repo.history_repo
import com.example.focusassistant.databinding.FragmentContentBinding
import com.example.focusassistant.tasks_db.repo.task_repo
import com.example.focusassistant.tasks_db.task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContentViewModel(private val repository: history_repo,private val repository1: task_repo):ViewModel() {
    lateinit var binding: FragmentContentBinding
    fun upsert(item: history) =
        GlobalScope.launch(Dispatchers.IO) {
            repository.upsert(item)
        }

    fun delete() = GlobalScope.launch(Dispatchers.IO) {
        repository.delete()
    }

    fun upsert1(item: task) =
        GlobalScope.launch(Dispatchers.IO) {
            repository1.upsert1(item)
        }

    fun delete1(item: task) = GlobalScope.launch(Dispatchers.IO) {
        repository1.delete1(item)
    }

    var i = 0
    var goal = 6.0
    fun progress(mProgressBar: ProgressBar): CountDownTimer {
        val mCountDownTimer: CountDownTimer =
            object : CountDownTimer((3600000L * goal).toLong(), 1) {
                override fun onTick(millisUntilFinished: Long) {
                    i++
                    mProgressBar.progress = ((i/(3600000L * goal))*100).toInt()
                }

                override fun onFinish() {
                    mProgressBar.progress = 0
                    val data = history(start_time = 0, end_time = i)
                    upsert(data)
                    i = 0
                    binding.materialButton.visibility = View.VISIBLE
                    binding.stophai.visibility = View.INVISIBLE
                    binding.Pause.visibility = View.INVISIBLE
                    binding.edit1.visibility = View.VISIBLE
                }
            }
        return mCountDownTimer
    }
    fun getAllPersonsInfo() = repository.getAllPersonsInfo()
    fun getAllPersonsInfo1() = repository1.getAllPersonsInfo1()

}