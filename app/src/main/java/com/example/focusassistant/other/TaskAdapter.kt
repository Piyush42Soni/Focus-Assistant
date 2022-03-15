package com.example.focusassistant.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.focusassistant.R
import com.example.focusassistant.content_files.ContentViewModel
import com.example.focusassistant.tasks_db.task
import kotlinx.android.synthetic.main.taskviewmodel.view.*

class TaskAdapter(
    val activity: FragmentActivity?,
    var items: List<task>,
    private val viewModel: ContentViewModel
): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.taskviewmodel, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val curTaskDetails = items[position]
        holder.itemView.textView4.text=curTaskDetails.name
        holder.itemView.Check_Box.setOnClickListener {
            viewModel.delete1(curTaskDetails)
        }
    }
    inner class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}