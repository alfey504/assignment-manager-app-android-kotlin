package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import androidx.room.TypeConverters
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.daos.AssignmentsDao
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.daos.TasksDao
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entities.AssignmentEntity
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entities.TaskEntity
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.type_converters.AssignmentTypeConverters

@Database(entities = [AssignmentEntity :: class, TaskEntity::class], version=1)
@TypeConverters(AssignmentTypeConverters::class)
abstract class AssignmentDatabase: RoomDatabase(){

    abstract fun assignmentsDao() : AssignmentsDao
    abstract fun tasksDao() : TasksDao

    companion object {
        @Volatile
        private var INSTANCE : AssignmentDatabase? = null

        fun getDatabase(context: Context): AssignmentDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AssignmentDatabase::class.java,
                    "assignment_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}