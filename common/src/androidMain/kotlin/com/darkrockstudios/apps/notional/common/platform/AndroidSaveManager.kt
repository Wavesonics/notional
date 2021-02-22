package com.darkrockstudios.apps.notional.common.platform

import android.content.Context
import java.io.File


class AndroidSaveManager(private val appContext: Context) : SaveManager() {
    override fun getFile(project: String, path: String): File {
        val userDir = appContext.filesDir
        val projectDir = File(userDir, project)

        if (!projectDir.exists()) {
            projectDir.mkdirs()
        }

        val file = File(projectDir, path)
        return file
    }
}