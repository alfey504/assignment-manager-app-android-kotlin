package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.database.type_converters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date

class AssignmentTypeConverters {

    @TypeConverter
    fun dateToString(date: Date): String{
        if(date == null){
            return ""
        }else{
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            return formatter.format(date).toString()
        }
    }

    @TypeConverter
    fun stringToDate(string: String): Date{
        if(string == null){
            return Date()
        }else{
            try{
                val formatter = SimpleDateFormat("yyyy-MM-dd")
                return formatter.parse(string)
            }catch (e: Exception){
                e.printStackTrace()
                return Date()
            }
        }
    }

}