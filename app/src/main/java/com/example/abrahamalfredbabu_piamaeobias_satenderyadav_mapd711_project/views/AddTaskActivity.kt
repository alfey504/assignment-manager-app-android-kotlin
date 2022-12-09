package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.R
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.databinding.ActivityAddTaskBinding
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.view_models.AddTaskViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSpinner()

        val viewModel = ViewModelProvider(this)[AddTaskViewModel::class.java]
        title = "Add Task"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.add_task_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val viewModel = ViewModelProvider(this)[AddTaskViewModel::class.java]
        if(item.itemId == R.id.task_page_save_task){
            if(validateFields()){
                val taskName = binding.addTaskTaskName.text.toString()
                val assignmentId = 1
                val priority = binding.addTaskSetPrioritySpinner.selectedItem.toString()
                val description = binding.addTaskDescriptionEditText.text.toString()
                GlobalScope.launch (Dispatchers.IO) {
                    viewModel.saveTask(taskName, assignmentId, priority,description, this@AddTaskActivity)
                }

            }
            val tasksListActivityIntent = Intent(this, TasksListActivity::class.java)
            startActivity(tasksListActivityIntent)
            return true
        }
        return true
    }

    private fun initSpinner(){
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.priority_list,
            R.layout.add_task_priority_spinner_item
        )
        adapter.setDropDownViewResource(R.layout.add_task_priority_dropdown_item)
        binding.addTaskSetPrioritySpinner.adapter = adapter

    }

    private fun validateFields(): Boolean{
        if(binding.addTaskTaskName.text.isEmpty()){
            return false
        }
        if(binding.addTaskSetPrioritySpinner.selectedItem.toString() == "Select Priority"){
            return false
        }
        return true
    }
}