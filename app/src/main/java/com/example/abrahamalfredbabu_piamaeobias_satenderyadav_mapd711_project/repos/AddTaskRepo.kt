package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.repos

import android.content.Context
import android.webkit.WebSettings.RenderPriority
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Tasks
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database_handlers.TaskDatabaseHandler

class AddTaskRepo {

    suspend fun saveTaskToDatabase(taskName: String, assignmentId: Int, priority: String, description: String, context: Context){
        val taskDatabaseHandler = TaskDatabaseHandler(context)
        val task = Tasks(
            title = taskName,
            assignmentId = assignmentId,
            priority = priority,
            description = description,
            completed = false,
        )
        taskDatabaseHandler.addTasks(task)
    }
}