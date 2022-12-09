package com.example.abrahamalfredbabu_piamaeobias_satenderyadav_mapd711_project.data_models

import android.icu.text.CaseMap.Title

data class Tasks(
    val id: Int? = null,
    val assignmentId: Int,
    val title: String,
    val description: String,
    var completed: Boolean,
    var expandable: Boolean ?= false,
    val priority: String
)
