package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entities.TaskEntity

@Dao
interface TasksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: TaskEntity)

    @Query("SELECT * FROM tasks_table WHERE assignment_id=:assignmentId")
    suspend fun getTaskOfAssignment(assignmentId: Int): List<TaskEntity>

    @Query("UPDATE  tasks_table SET completed=:completed WHERE id = :taskId")
    suspend fun updateCompletedOfTask(taskId: Int, completed: Boolean)

    @Query("DELETE FROM tasks_table WHERE id=:taskId")
    suspend fun deleteTask(taskId: Int)
}