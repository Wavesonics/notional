package com.darkrockstudios.apps.nortional.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.rootComponent
import com.darkrockstudios.apps.notional.common.AppRoot
import com.darkrockstudios.apps.notional.common.platform.AndroidSaveManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val saveManager = AndroidSaveManager()

        setContent {
            AndroidTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                        rootComponent { componentContext ->
                            AppRoot(
                                saveManager,
                                componentContext
                            )
                        }.render()
                    }
                }
            }
        }
    }
}