package com.example.einkaufsliste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.einkaufsliste.ui.theme.EinkaufslisteTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EinkaufslisteTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically ) {
        Spacer(
            modifier = modifier.padding(top = 32.dp)
        )
        Text(
            text = "Einkaufsliste",
            textAlign = TextAlign.Center,
            modifier = modifier.width(300.dp)
        )
        ToDo()
        ToDoList()

    }
}

@Composable
fun ToDo(modifier: Modifier = Modifier) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically ){
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EinkaufslisteTheme {
        Greeting()
    }
}

@Composable
fun ToDoList(viewModel: ToDoViewModel = viewModel()) {
    val toDoList = viewModel.toDoList

    LazyColumn {
      items(toDoList) { toDo ->
          ToDoItem(toDo = toDo, onCheckChanged = {
              viewModel.toggleToDoCompletion(toDo)},
              onDelete = { viewModel.removeToDo(toDo)})
      }
    }
}

@Composable
fun ToDoItem(toDo: ToDo, onCheckChanged: (ToDo) -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onCheckChanged(toDo) },
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(
            checked = toDo.isCompleted,
            onCheckedChange = { onCheckChanged(toDo) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = toDo.task,
            style = MaterialTheme.typography.bodyLarge,
            color = if (toDo.isCompleted) Color.Gray else Color.Black
        )
        IconButton(onClick = { onDelete() }) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete Task")
        }
    }
}

@Composable
fun AddToDoInput(viewModel: ToDoViewModel){
    var task by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        TextField(
            value = task,
            onValueChange = { task = it },
            label = { Text("Add a task") },
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = {
            if(task.isNotEmpty()) {
                viewModel.addToDo(task)
                task = ""
            }
        }) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add Task",
            )
        }
    }
}