package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table", foreignKeys = [ForeignKey(entity = AssignmentEntity::class, parentColumns =  ["id"], childColumns = ["assignment_id"], onDelete = ForeignKey.CASCADE)])
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "assignment_id") val assignmentId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "completed") val completed: Boolean,
    @ColumnInfo(name = "priority") val priority: String
)
