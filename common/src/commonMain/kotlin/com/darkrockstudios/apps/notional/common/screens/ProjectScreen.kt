package com.darkrockstudios.apps.notional.common.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.darkrockstudios.apps.notional.common.Component
import com.darkrockstudios.apps.notional.common.platform.FileManager


private val testFile = "test_file"

class ProjectComponent(
    componentContext: ComponentContext,
    val fileManager: FileManager,
    val projectName: String,
    private val onFinished: () -> Unit
) : ComponentContext by componentContext, Component {

    fun onBackClicked() {
        onFinished()
    }

    fun onNewSection(sectionName: String): Boolean {
        return fileManager.createSection(projectName, sectionName)
    }

    @Composable
    override fun render() {
        ProjectScreen(this)
    }
}

@Composable
fun ProjectScreen(component: ProjectComponent) {
    val textState =
        remember { mutableStateOf(TextFieldValue(component.fileManager.readFile(component.projectName, testFile))) }

    val newSectionName = remember { mutableStateOf(TextFieldValue()) }

    Column {
        TopAppBar(title = { Text(text = component.projectName) })

        Row {
            Column(modifier = Modifier.weight(0.25f).padding(16.dp)) {

                Button(onClick = component::onBackClicked) {
                    Text("go back")
                }

                Spacer(modifier = Modifier.padding(16.dp))

                Text("New Section:")
                TextField(value = newSectionName.value, onValueChange = { value -> newSectionName.value = value })
                Button(onClick = {
                    val newSectionNameStr = newSectionName.value.text
                    component.onNewSection(newSectionNameStr)
                }) {
                    Text("New Section")
                }

                Spacer(modifier = Modifier.padding(16.dp))

                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(
                        items = component.fileManager.getSections(projectName = component.projectName),
                        itemContent = { projectName ->
                            SectionRow(component, projectName)
                        })
                }
            }
            Column(modifier = Modifier.weight(weight = 0.75f)) {
                Button(
                    onClick = {
                        component.fileManager.writeToFile(component.projectName, testFile, textState.value.text)
                    }
                ) {
                    Text("Write file")
                }

                BasicTextField(
                    value = textState.value,
                    onValueChange = { value -> textState.value = value },
                    modifier = Modifier.fillMaxSize()
                        .border(1.dp, Color.Black)
                        .padding(top = 8.dp)
                        .background(color = Color.Gray)
                )
            }
        }
    }
}

@Composable
fun SectionRow(component: ProjectComponent, sectionName: String) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(8.dp)
        //.clickable { component.selectProject(projectName) }
    ) {
        Row {
            Text(sectionName)
        }
    }
}