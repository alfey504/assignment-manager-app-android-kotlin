package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.entities.AssignmentEntity

@Dao
interface AssignmentsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAssignment(assignment: AssignmentEntity)

    @Query("SELECT * FROM assignments_table")
    suspend fun fetchAllAssignments(): List<AssignmentEntity>

    @Query("DELETE FROM assignments_table WHERE id=:assignmentId")
    suspend fun deleteAssignment(assignmentId: Int)
}