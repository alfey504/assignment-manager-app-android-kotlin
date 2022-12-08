package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.activities.AddTaskActivity
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Assignment
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Tasks
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database_handlers.AssignmentDatabaseHandler
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database_handlers.TaskDatabaseHandler
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clickMeButton.setOnClickListener {
            val newIntent = Intent(this, AddTaskActivity::class.java)
            startActivity(newIntent)
        }

        val assignmentDatabaseHandler = AssignmentDatabaseHandler(this)
        val tasksDatabaseHandler =TaskDatabaseHandler(this)
        val assignment = Assignment(
            title = "Assignment 1",
            subject = "Subject 1",
            dueDate = Date(),
        )
        GlobalScope.launch(Dispatchers.IO) {
            assignmentDatabaseHandler.addAssignmentToTable(assignment)
            val assignments = assignmentDatabaseHandler.getAllAssignmentsFromTable()
            Log.i(TAG, assignments.toString())


            val tasks = tasksDatabaseHandler.getTasksOfAssignment(1)
            Log.i(TAG, tasks.toString())
        }
    }
}