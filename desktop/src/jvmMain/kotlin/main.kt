import com.darkrockstudios.apps.notional.desktop.DesktopTheme
import androidx.compose.desktop.Window
import com.arkivanov.decompose.extensions.compose.jetbrains.rootComponent
import com.darkrockstudios.apps.common.AppRoot


fun main() = Window(title = "Notional") {
    DesktopTheme {
        val root = rootComponent(factory = ::AppRoot)
        root.render()
    }
}