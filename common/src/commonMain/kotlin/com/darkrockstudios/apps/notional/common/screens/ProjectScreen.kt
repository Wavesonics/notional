package com.darkrockstudios.apps.notional.common.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.darkrockstudios.apps.notional.common.Component
import com.darkrockstudios.apps.notional.common.platform.SaveManager


class ProjectComponent(
    componentContext: ComponentContext,
    private val saveManager: SaveManager,
    val projectName: String,
    private val onFinished: () -> Unit
) : ComponentContext by componentContext, Component {

    //private var state by mutableStateOf(Model())

    fun onBackClicked() {
        onFinished()
    }

    private data class Model(
        val projectName: String = ""
    )

    @Composable
    override fun render() {
        ProjectScreen(this)
    }
}

@Composable
fun ProjectScreen(project: ProjectComponent) {
    Column {
        TopAppBar(title = { Text(text = "Project") })
        Text("Project: ${project.projectName}")
        Button(onClick = project::onBackClicked) {
            Text("go back")
        }
    }
}