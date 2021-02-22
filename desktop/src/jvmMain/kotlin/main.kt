import androidx.compose.desktop.Window
import com.arkivanov.decompose.extensions.compose.jetbrains.rootComponent
import com.darkrockstudios.apps.notional.common.AppRoot
import com.darkrockstudios.apps.notional.common.platform.DesktopFileManager
import com.darkrockstudios.apps.notional.desktop.DesktopTheme


fun main() = Window(title = "Notional") {
    DesktopTheme {
        val saveManager = DesktopFileManager()

        val root =
            rootComponent(factory = { componentContext -> AppRoot(saveManager, componentContext) })
        root.render()
    }
}