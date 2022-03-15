package com.example.focusassistant.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.focusassistant.History_db.history
import com.example.focusassistant.R
import com.example.focusassistant.content_files.ContentViewModel
import kotlinx.android.synthetic.main.historyviewmodel.view.*

class Adapters(
    val activity: FragmentActivity?,
    var items: List<history>,
    private val viewModel: ContentViewModel
): RecyclerView.Adapter<Adapters.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.historyviewmodel, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val curhistoryDetails = items[position]

        holder.itemView.start_time_text.text = curhistoryDetails.start_time.toString()
        holder.itemView.end_time_text.text = "${(curhistoryDetails.end_time.div(3600000).toInt().toString())}:${((curhistoryDetails.end_time%(3600000)).div(60000).toString())}:${(((curhistoryDetails.end_time%(3600000))%(60000)).div(1000).toString())}"
    }

    inner class HistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}