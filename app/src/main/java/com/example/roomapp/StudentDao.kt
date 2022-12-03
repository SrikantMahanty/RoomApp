package com.example.roomapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {
    @Query("Select * from Student_table")
    fun getAll():List<Student>

    @Query("Select * from Student_table where rollNo LIKE :roll LIMIT 1")
    suspend fun findByRoll(roll:Int):Student

    @Insert(onConflict =OnConflictStrategy.IGNORE)
    suspend fun insert(Student:Student)

    @Delete
    suspend fun delete(Student: Student)
    @Query("Delete from Student_table")
    suspend fun deleteAll()
    }