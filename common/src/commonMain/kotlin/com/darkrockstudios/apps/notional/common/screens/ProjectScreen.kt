package com.darkrockstudios.apps.notional.common.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.darkrockstudios.apps.notional.common.Component
import com.darkrockstudios.apps.notional.common.platform.SaveManager


private val testFile = "test_file"

class ProjectComponent(
    componentContext: ComponentContext,
    val saveManager: SaveManager,
    val projectName: String,
    private val onFinished: () -> Unit
) : ComponentContext by componentContext, Component {



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
fun ProjectScreen(component: ProjectComponent) {
    val textState =
        remember { mutableStateOf(TextFieldValue(component.saveManager.readFile(component.projectName, testFile))) }

    Column {
        TopAppBar(title = { Text(text = "Project") })
        Text("Project: ${component.projectName}")

        Column(Modifier.padding(16.dp)) {
            Row {
                Modifier.padding(8.dp)

                Button(onClick = component::onBackClicked) {
                    Modifier.padding(8.dp)
                    Text("go back")
                }

                Button(
                    onClick = {
                        component.saveManager.writeToFile(component.projectName, testFile, textState.value.text)
                    }
                ) {
                    Text("Write file")
                }
            }

            BasicTextField(
                value = textState.value,
                onValueChange = { value -> textState.value = value },
                modifier = Modifier.fillMaxSize()
                    .border(1.dp, Color.Black).padding(top = 8.dp)
            )
        }
    }
}