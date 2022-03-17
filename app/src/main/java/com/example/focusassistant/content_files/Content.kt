package com.example.focusassistant.content_files

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.focusassistant.History_db.history
import com.example.focusassistant.History_db.history_database
import com.example.focusassistant.History_db.repo.history_repo
import com.example.focusassistant.R
import com.example.focusassistant.Task_Dialog.AddTaskInfoDialog
import com.example.focusassistant.Task_Dialog.AddTaskInfoListener
import com.example.focusassistant.databinding.FragmentContentBinding
import com.example.focusassistant.goal_dialogs.AddGoalInfoListener
import com.example.focusassistant.goal_dialogs.Add_Goal_Info_dialog
import com.example.focusassistant.other.Adapters
import com.example.focusassistant.other.TaskAdapter
import com.example.focusassistant.tasks_db.repo.task_repo
import com.example.focusassistant.tasks_db.task
import com.example.focusassistant.tasks_db.task_database

class Content : Fragment(){
    private lateinit var binding:FragmentContentBinding
    private var startTime=0
    private var endTime=0
    private var goal=6.0
    override fun onStart() {
        binding.stophai.visibility=View.INVISIBLE
        binding.Pause.visibility=View.INVISIBLE
        super.onStart()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val factory= ContentViewModelFactory(history_repo(history_database.invoke(this.requireActivity().baseContext)),
            task_repo(
            task_database.invoke(this.requireActivity().baseContext))
        )
        val viewModel:ContentViewModel= ViewModelProvider(this, factory)[ContentViewModel::class.java]
        startTime=0
        endTime=0
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false)
        viewModel.binding=binding
        binding.materialButton.setOnClickListener{startTime = System.currentTimeMillis().toInt() }
        val mCountDownTimer=viewModel.progress(binding.progressBar)
        binding.materialButton.setOnClickListener {
            mCountDownTimer.start()
            binding.stophai.visibility=View.VISIBLE
            binding.materialButton.visibility=View.INVISIBLE
            binding.Pause.visibility=View.VISIBLE
            startTime=0
            binding.edit1.visibility=View.INVISIBLE
        }
        binding.Pause.setOnClickListener {
            mCountDownTimer.cancel()
            binding.materialButton.visibility=View.VISIBLE
        }
        binding.stophai.setOnClickListener {
            mCountDownTimer.cancel()
            endTime = viewModel.i
            val data = history(start_time = startTime, end_time = endTime)
            viewModel.upsert(data)
            viewModel.i=0
            binding.progressBar.progress=0
            binding.materialButton.visibility=View.VISIBLE
            binding.stophai.visibility=View.INVISIBLE
            binding.Pause.visibility=View.INVISIBLE
            binding.edit1.visibility=View.VISIBLE
        }
        val adapter = Adapters(this.activity,listOf(), viewModel)
        val adapter2 = TaskAdapter(this.activity,listOf(), viewModel)
        binding.history.layoutManager = LinearLayoutManager(this.activity)
        binding.history.adapter = adapter
        binding.tasks.layoutManager=LinearLayoutManager(this.activity)
        binding.tasks.adapter=adapter2
        this.activity?.let {
            viewModel.getAllPersonsInfo().observe(it, Observer {
                adapter.items = it
                adapter.notifyDataSetChanged()
            })
        }
        this.activity?.let {
            viewModel.getAllPersonsInfo1().observe(it, Observer {
                adapter2.items = it
                adapter2.notifyDataSetChanged()
            })
        }
        binding.clear.setOnClickListener{
            viewModel.delete()
        }
        binding.edit1.setOnClickListener {
            Add_Goal_Info_dialog(
                this.requireActivity(),
                object : AddGoalInfoListener {
                    override fun onAddButtonClicked(item: Double) {
                        goal= item
                        viewModel.goal=goal
                        binding.textView.setText("Today's Goal is $goal Hrs")
                    }
                }).show()
        }
        binding.edit2.setOnClickListener {
            AddTaskInfoDialog(
                this.requireActivity(),
                object : AddTaskInfoListener {
                    override fun onAddButtonClicked(item: String) {
                        val data= task(name = item)
                        viewModel.upsert1(data)
                    }
                }).show()
        }

        return binding.root
    }
}