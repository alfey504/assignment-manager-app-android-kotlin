package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database_handlers

import android.content.Context
import androidx.core.content.contentValuesOf
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Tasks
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.daos.TasksDao
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.databases.AssignmentDatabase
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entity_model_adapters.TaskAdapters

class TaskDatabaseHandler {

    private val assignmentDatabase : AssignmentDatabase
    private val taskDao : TasksDao
    private val taskAdapter = TaskAdapters()

    constructor(context: Context){
        assignmentDatabase = AssignmentDatabase.getDatabase(context)
        taskDao = assignmentDatabase.tasksDao()
    }

    suspend fun addTasks(task: Tasks){
        val taskEntity = taskAdapter.modelToEntity(task)
        try{
            taskDao.addTask(taskEntity)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    suspend fun getTasksOfAssignment(assignmentId: Int): ArrayList<Tasks>{
        try {
            val tasksEntities = taskDao.getTaskOfAssignment(assignmentId)
            return if(tasksEntities == null){
                arrayListOf()
            }else{
                taskAdapter.listOfEntityToArrayListOfModel(tasksEntities)
            }
        }catch (e: Exception){
            e.printStackTrace()
            return arrayListOf()
        }
    }

    suspend fun changeCompletedOfTasks(taskId: Int, completed: Boolean){
        try {
            taskDao.updateCompletedOfTask(taskId, completed)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    suspend fun deleteTask(taskId: Int){
        try {
            taskDao.deleteTask(taskId)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}