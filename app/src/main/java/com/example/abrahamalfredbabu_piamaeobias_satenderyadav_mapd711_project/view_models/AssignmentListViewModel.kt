package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.view_models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Assignment
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.repos.AssignmentListRepo
import kotlinx.coroutines.*
import kotlin.collections.ArrayList

class AssignmentListViewModel: ViewModel()
{

    private val repo = AssignmentListRepo()

    private var privateAssignmentList = MutableLiveData<ArrayList<Assignment>>()
    public val publicAssignmentList: LiveData<ArrayList<Assignment>> = privateAssignmentList


    fun addAssignment(assignment: Assignment, context: Context)
    {
        GlobalScope.launch(Dispatchers.IO) {
            repo.saveAssignmentToDatabase(assignment, context)
            getAssignments(context)
        }
    }

    fun getAssignments(context: Context){
        GlobalScope.launch(Dispatchers.IO) {
            val assignments = repo.getAssignmentsFromDatabase(context)
            withContext(Dispatchers.Main){
                privateAssignmentList.value = assignments
            }
        }
    }
}