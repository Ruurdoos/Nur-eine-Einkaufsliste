package com.example.einkaufsliste

data class ToDo(
    val id: Int,
    val task: String,
    var isCompleted: Boolean = false
)
