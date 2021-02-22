package com.darkrockstudios.apps.notional.common.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.darkrockstudios.apps.notional.common.Component
import com.darkrockstudios.apps.notional.common.platform.FileManager


class ProjectPickerComponent(
    componentContext: ComponentContext,
    val fileManager: FileManager,
    private val onProjectSelected: (projectName: String) -> Unit
) : ComponentContext by componentContext, Component {

    fun selectProject(projectName: String) {
        onProjectSelected(projectName)
    }

    fun createNewProject(projectName: String): Boolean {
        return fileManager.createProject(projectName)
    }

    @Composable
    override fun render() {
        ProjectPickerScreen(this)
    }
}

@Composable
fun ProjectPickerScreen(component: ProjectPickerComponent) {
    val projects = component.fileManager.getProjects()

    val newProjectName = remember { mutableStateOf(TextFieldValue()) }

    Column {
        TopAppBar(title = { Text(text = "Projects") })

        Row {
            Text("New Project:")
            TextField(value = newProjectName.value, onValueChange = { value -> newProjectName.value = value })
            Button(onClick = {
                val newProjectNameStr = newProjectName.value.text
                // If the project is created successfully, open it
                if (component.createNewProject(newProjectNameStr)) {
                    component.selectProject(newProjectNameStr)
                }
            }) {
                Text("Create New Project")
            }
        }

        Text("Projects:")
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = projects, itemContent = { projectName ->
                ProjectRow(component, projectName)
            })
        }
    }
}

@Composable
fun ProjectRow(component: ProjectPickerComponent, projectName: String) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(8.dp)
            .clickable { component.selectProject(projectName) }
    ) {
        Row {
            Text(projectName)
        }
    }
}