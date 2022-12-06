package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Assignment
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Tasks
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database_handlers.AssignmentDatabaseHandler
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database_handlers.TaskDatabaseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {

            val assignment = Assignment(
                title = "Assignment 1",
                subject = "subject 1",
                dueDate = Date()
            )

            val assignmentHandler = AssignmentDatabaseHandler(this@MainActivity)
            assignmentHandler.addAssignmentToTable(assignment)

            val assignments = assignmentHandler.getAllAssignmentsFromTable()
            Log.i(TAG, assignments.toString())

            val taskHandler = TaskDatabaseHandler(this@MainActivity)
            val task = Tasks(
                assignmentId = assignments[0].id!!,
                title = "Task1",
                completed = false,
                priority = "HIGH"
            )
            taskHandler.addTasks(task)

            val tasks = taskHandler.getTasksOfAssignment(assignments[0].id!!)
            Log.i(TAG, tasks.toString())
        }
    }
}