package com.example.einkaufsliste

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {
    private val _toDoList = mutableStateListOf<ToDo>()
    val toDoList: List<ToDo> get() = _toDoList

    fun addToDo(task: String) {
        val newTask = ToDo(
            id = _toDoList.size + 1,
            task = task
        )
        _toDoList.add(newTask)
    }
    fun toggleToDoCompletion(toDo: ToDo) {
        val index = _toDoList.indexOf(toDo)
        if (index >= 0) {
            _toDoList[index] = toDo.copy(isCompleted = !toDo.isCompleted)
        }
    }

    fun removeToDo(toDo: ToDo) {
        _toDoList.remove(toDo)
    }
}