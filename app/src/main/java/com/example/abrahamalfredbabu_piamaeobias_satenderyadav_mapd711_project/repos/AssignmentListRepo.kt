package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.repos

import android.content.Context
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Assignment
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database_handlers.AssignmentDatabaseHandler

class AssignmentListRepo {

    suspend fun saveAssignmentToDatabase(assignment: Assignment, context: Context){
        val assignmentDatabaseHandler = AssignmentDatabaseHandler(context)
        assignmentDatabaseHandler.addAssignmentToTable(assignment)
    }

    suspend fun deleteAssignmentFromDatabase(assignmentId: Int, context: Context){
        val assignmentDatabaseHandler = AssignmentDatabaseHandler(context)
        assignmentDatabaseHandler.deleteAssignmentFromTable(assignmentId)
    }

    suspend fun getAssignmentsFromDatabase(context: Context): ArrayList<Assignment>{
        val assignmentDatabaseHandler = AssignmentDatabaseHandler(context)
        return assignmentDatabaseHandler.getAllAssignmentsFromTable()
    }
}