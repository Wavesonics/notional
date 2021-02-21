package com.darkrockstudios.apps.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext


class ProjectPicker(
    componentContext: ComponentContext,
    private val onProjectSelected: (projectName: String) -> Unit
) : ComponentContext by componentContext, Component {

    fun selectProject(projectName: String) {
        onProjectSelected(projectName)
    }

    @Composable
    override fun render() {
        ProjectPickerScreen(this)
    }
}

@Composable
fun ProjectPickerScreen(projectPicker: ProjectPicker) {
    Column {
        TopAppBar(title = { Text(text = "Projects") })
        Text("Project Picker")
        Button(onClick = {
            projectPicker.selectProject("test_project")
        }) {
            Text("Test Project")
        }
    }
}