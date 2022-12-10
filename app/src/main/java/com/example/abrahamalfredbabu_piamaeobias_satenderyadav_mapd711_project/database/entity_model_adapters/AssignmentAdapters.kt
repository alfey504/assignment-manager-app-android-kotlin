package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entity_model_adapters

import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Assignment
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entities.AssignmentEntity

class AssignmentAdapters {

    fun entityToModel(entity: AssignmentEntity): Assignment{
        return (Assignment(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            subject = entity.subject,
            dueDate = entity.dueDate
        ))
    }

    fun arrayListOfEntityToModels(arrayListOfAssignmentEntity: List<AssignmentEntity>): ArrayList<Assignment> {
        var arrayListOfAssignmentModel = arrayListOf<Assignment>()
        for (assignmentEntity in arrayListOfAssignmentEntity){
            val assignmentModel = entityToModel(assignmentEntity)
            arrayListOfAssignmentModel.add(assignmentModel)
        }
        return arrayListOfAssignmentModel
    }

    fun modelToEntity(assignmentModel: Assignment): AssignmentEntity {
        return (AssignmentEntity(
            id = assignmentModel.id,
            title = assignmentModel.title,
            subject = assignmentModel.subject!!,
            description = assignmentModel.description,
            dueDate = assignmentModel.dueDate
        ))
    }
}