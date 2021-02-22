package com.darkrockstudios.apps.notional.common.platform

import java.io.File

class DesktopFileManager : FileManager() {
    override fun getSystemUserDirectory(): File {
        val userDirPath = System.getProperty("user.home")
        return File(userDirPath)
    }
}