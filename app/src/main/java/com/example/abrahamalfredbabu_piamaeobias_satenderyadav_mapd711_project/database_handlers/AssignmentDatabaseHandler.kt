package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database_handlers

import android.content.Context
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.daos.AssignmentsDao
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Assignment
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.databases.AssignmentDatabase
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entities.AssignmentEntity
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entity_model_adapters.AssignmentAdapters

class AssignmentDatabaseHandler(context: Context) {
    private var assignmentDatabase : AssignmentDatabase
    private var assignmentDao: AssignmentsDao

    init {
        assignmentDatabase = AssignmentDatabase.getDatabase(context)
        assignmentDao = assignmentDatabase.assignmentsDao()
    }

    suspend fun getAllAssignmentsFromTable(): ArrayList<Assignment>{
        try{
            val assignmentsEntities =  assignmentDao.fetchAllAssignments()
            val adapter = AssignmentAdapters()

            return if(assignmentsEntities == null){
                arrayListOf()
            }else{
                adapter.arrayListOfEntityToModels(assignmentsEntities)
            }

        }catch (e: Exception){
            e.printStackTrace()
            return arrayListOf()
        }
    }

    suspend fun addAssignmentToTable(assignment: Assignment){
        try{
            val adapter = AssignmentAdapters()
            val assignmentEntity = adapter.modelToEntity(assignment)
            assignmentDao.addAssignment(assignmentEntity)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    suspend fun deleteAssignmentFromTable(assignmentId: Int){
        try {
            assignmentDao.deleteAssignment(assignmentId)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}