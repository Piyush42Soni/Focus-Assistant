package com.example.focusassistant.Task_Dialog

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatDialog
import com.example.focusassistant.R
import com.example.focusassistant.goal_dialogs.AddGoalInfoListener

class AddTaskInfoDialog(context: Context, var AddTaskInfoListener: AddTaskInfoListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_task)

        findViewById<Button>(R.id.task_button)!!.setOnClickListener {
            AddTaskInfoListener.onAddButtonClicked(findViewById<EditText>(R.id.task_edit_text)!!.text.toString())
            dismiss()
        }
    }
}