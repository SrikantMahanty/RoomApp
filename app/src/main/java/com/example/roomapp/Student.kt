package com.example.roomapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student_table")
class Student (
    @PrimaryKey(autoGenerate = true) val id :Int?,
    @ColumnInfo(name= "firstname") val firstName:String?,
    @ColumnInfo(name= "lastname") val lastName:String?,
    @ColumnInfo(name= "rollNo") val rollNo:Int?,
)