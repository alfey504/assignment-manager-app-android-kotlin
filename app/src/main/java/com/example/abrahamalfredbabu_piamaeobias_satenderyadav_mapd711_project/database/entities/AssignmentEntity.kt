package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entities

import android.content.ClipDescription
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "assignments_table")
data class AssignmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "subject") val subject: String,
    @ColumnInfo(name = "due_date") val dueDate: Date
 )
