package com.darkrockstudios.apps.notional.common

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.pop
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.statekeeper.Parcelize
import com.darkrockstudios.apps.notional.common.platform.SaveManager
import com.darkrockstudios.apps.notional.common.screens.ProjectComponent
import com.darkrockstudios.apps.notional.common.screens.ProjectPicker
import com.darkrockstudios.apps.notional.common.screens.ProjectPickerScreen
import com.darkrockstudios.apps.notional.common.screens.ProjectScreen


class AppRoot(
    private val saveManager: SaveManager,
    componentContext: ComponentContext
) : Component, ComponentContext by componentContext {

    private val router =
        router<Config, Content>(
            initialConfiguration = Config.ProjectPicker,
            handleBackButton = true,
            componentFactory = ::createChild
        )

    //val routerState: Value<RouterState<*, Content>> = router.state

    private fun createChild(config: Config, componentContext: ComponentContext): Content =
        when (config) {
            is Config.ProjectPicker -> {
                ProjectPicker(componentContext, onProjectSelected = ::onProjectSelected).asContent { ProjectPickerScreen(it) }
            }
            is Config.Project -> {
                ProjectComponent(
                    componentContext,
                    saveManager = saveManager,
                    projectName = config.projectName,
                    onFinished = { router.pop() }).asContent { ProjectScreen(it) }
            }
        }


    private fun onProjectSelected(projectName: String) {
        router.push(Config.Project(projectName = projectName))
    }

    @Composable
    override fun render() {
        Children(routerState = router.state) { child, _ ->
            child()
        }
    }

    private sealed class Config : Parcelable {
        @Parcelize
        object ProjectPicker : Config()

        @Parcelize
        data class Project(val projectName: String) : Config()
    }
}