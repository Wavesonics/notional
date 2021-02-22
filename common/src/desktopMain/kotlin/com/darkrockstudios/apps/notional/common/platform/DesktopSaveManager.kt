package com.darkrockstudios.apps.notional.common.platform

import java.io.File

class DesktopSaveManager : SaveManager() {
    override fun getRootDirectory(): File {
        val userDirPath = System.getProperty("user.home")
        return File(userDirPath)
    }
}