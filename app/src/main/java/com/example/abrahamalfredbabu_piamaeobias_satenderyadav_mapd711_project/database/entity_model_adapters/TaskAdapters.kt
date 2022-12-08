package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entity_model_adapters

import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models.Tasks
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entities.TaskEntity

class TaskAdapters {

    fun entityToModel(taskEntity: TaskEntity): Tasks{
        return(Tasks(
            id = taskEntity.id,
            assignmentId = taskEntity.assignmentId,
            title = taskEntity.title,
            description = taskEntity.description,
            completed = taskEntity.completed,
            priority = taskEntity.priority
        ))
    }

    fun listOfEntityToArrayListOfModel(listOfEntity: List<TaskEntity>): ArrayList<Tasks>{
        val arrayListOfModel = arrayListOf<Tasks>()
        for (entity in listOfEntity){
            val model = entityToModel(entity)
            arrayListOfModel.add(model)
        }
        return arrayListOfModel
    }

    fun modelToEntity(model: Tasks): TaskEntity{
        return (TaskEntity(
            id = model.id,
            assignmentId = model.assignmentId,
            title = model.title,
            description= model.description,
            completed = model.completed,
            priority = model.priority
        ))
    }
}