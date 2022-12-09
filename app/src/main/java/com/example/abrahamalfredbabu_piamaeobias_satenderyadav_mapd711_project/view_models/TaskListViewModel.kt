package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Tasks
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.repos.TaskListRepo
import kotlinx.coroutines.*

class TaskListViewModel: ViewModel() {

    private val repo = TaskListRepo()

    private var privateTasksList = MutableLiveData<ArrayList<Tasks>>()
    val publicTasksList : LiveData<ArrayList<Tasks>> = privateTasksList

    @OptIn(DelicateCoroutinesApi::class)
    fun saveTask(taskName: String, assignmentId: Int, priority: String, description: String, context: Context){
        GlobalScope.launch(Dispatchers.IO) {
            repo.saveTaskToDatabase(taskName, assignmentId, priority, description, context)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getTask(assignmentId: Int, context: Context){
        GlobalScope.launch(Dispatchers.IO) {
            val tasks = repo.loadTasksFromDatabase(assignmentId, context)
            withContext(Dispatchers.Main){
                privateTasksList.value = tasks
            }
        }
    }

    fun updateCompletedOfTask(taskId: Int, completed: Boolean, context: Context){
        GlobalScope.launch(Dispatchers.IO) {
            repo.updatedCompletedOnDatabase(taskId, completed, context)
        }
    }
}