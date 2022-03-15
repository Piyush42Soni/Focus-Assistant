package com.example.focusassistant.goal_dialogs

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatDialog
import com.example.focusassistant.R
import com.google.android.material.button.MaterialButton

class Add_Goal_Info_dialog(context: Context, var addDialogListener: AddGoalInfoListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_time)

        findViewById<MaterialButton>(R.id.goal_button)!!.setOnClickListener {
            addDialogListener.onAddButtonClicked(findViewById<TextView>(R.id.username)!!.text.toString().toDouble())
            dismiss()
        }
    }
}