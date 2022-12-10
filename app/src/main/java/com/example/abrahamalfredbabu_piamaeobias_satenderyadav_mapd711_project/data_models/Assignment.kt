package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models

import java.util.Date

data class Assignment(
    val id: Int? = null,
    val title: String,
    val description:  String,
    var expandable : Boolean? = false,
    val subject: String? = "",
    val dueDate: Date
)
