package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.view_models

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.repos.AddTaskRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTaskViewModel: ViewModel() {

    private val repo = AddTaskRepo()

    suspend fun saveTask(taskName: String, assignmentId: Int, priority: String, description: String, context: Context){
        GlobalScope.launch(Dispatchers.IO) {
            repo.saveTaskToDatabase(taskName, assignmentId, priority, description, context)
        }
    }
}