package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.repos

import android.content.Context
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Tasks
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database_handlers.TaskDatabaseHandler

class TaskListRepo {

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

    suspend fun loadTasksFromDatabase(assignmentId: Int, context: Context) : ArrayList<Tasks>{
        val taskDatabaseHandler = TaskDatabaseHandler(context)
        return taskDatabaseHandler.getTasksOfAssignment(assignmentId)
    }

    suspend fun updatedCompletedOnDatabase(taskId: Int, completed: Boolean,context: Context){
        val taskDatabaseHandler = TaskDatabaseHandler(context)
        taskDatabaseHandler.changeCompletedOfTasks(taskId, completed)
    }
}