package com.darkrockstudios.apps.notional.common.platform

import java.io.File

class DesktopSaveManager : SaveManager() {
    override fun getFile(project: String, path: String): File {
        val userDirPath = System.getProperty("user.home")
        val userDir = File(userDirPath)
        val projectDir = File(userDir, project)

        if (!projectDir.exists()) {
            projectDir.mkdirs()
        }

        val file = File(projectDir, path)
        return file
    }
}